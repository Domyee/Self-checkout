package com.selfcheckout.service.receipt.impl;

import com.selfcheckout.dto.enumeration.Department;
import com.selfcheckout.dto.request.receipt.CreateReceiptReq;
import com.selfcheckout.dto.request.receipt.UpdateReceiptReq;
import com.selfcheckout.dto.response.receipt.CreateReceiptResponse;
import com.selfcheckout.dto.response.receipt.UpdateReceiptResponse;
import com.selfcheckout.factory.receipt.ReceiptFactory;
import com.selfcheckout.model.product.Product;
import com.selfcheckout.model.receipt.Receipt;
import com.selfcheckout.model.receipt.ReceiptItem;
import com.selfcheckout.model.receipt.ReceiptTmp;
import com.selfcheckout.model.receipt.ReceiptTmpItem;
import com.selfcheckout.repository.product.ProductRepository;
import com.selfcheckout.repository.receipt.ReceiptRepository;
import com.selfcheckout.repository.receipt.ReceiptTmpRepository;
import com.selfcheckout.service.receipt.ReceiptService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private ReceiptTmpRepository receiptTmpRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReceiptFactory receiptFactory;

    @Override
    public CreateReceiptResponse createReceipt(CreateReceiptReq request) {

        Receipt result = receiptRepository.save(
                receiptFactory.mapReceiptEntity(request));

        return receiptFactory.mapCreateReceiptResponse(result);
    }

    @Override
    @Transactional
    public CreateReceiptResponse createReceipt(Long receiptTmpId) {

        ReceiptTmp receiptTmp = receiptTmpRepository.getReferenceById(receiptTmpId);
        BigDecimal total = BigDecimal.valueOf(0);

        for(ReceiptTmpItem ri : receiptTmp.getItems()){
            total = total.add(ri.getPrice());
        }

        Receipt receipt = receiptFactory.mapReceiptEntity(receiptTmp);
        receipt.setTotal(total);

        // save the actual receipt
        Receipt result = receiptRepository.save(receipt);

        // Delete the temporary receipt and its items
        receiptTmpRepository.deleteById(receiptTmpId);

        return receiptFactory.mapCreateReceiptResponse(result);
    }

    @Override
    @Transactional
    public UpdateReceiptResponse updateReceipt(UpdateReceiptReq request) {

        // Retrieve the product from barcode
        Product product = Optional.of(
                productRepository.findByBarcode(request.getBarcode()))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Product with barcode %s not found", request.getBarcode())));


        ReceiptTmp receiptTmp = new ReceiptTmp();

        // Check if a Temporary receipt with the request id is present
        if(request.getReceiptTmpId() != null) {
            Optional<ReceiptTmp> optionalReceiptTmp =
                    Optional.of(
                            receiptTmpRepository.findById(request.getReceiptTmpId())
                            .orElseThrow(() -> new EntityNotFoundException("Temporary Receipt with id %d not found: " + request.getReceiptTmpId())));

            receiptTmp = optionalReceiptTmp.get();
        }

        List<ReceiptTmpItem> items = receiptTmp.getItems();

        boolean isNewItem = true;

        // Check if the same item is present (by name)
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getProduct().equals(product)){
                ReceiptTmpItem item = items.get(i);
                item.setQuantity(item.getQuantity() + 1);
                items.set(i, item);
                isNewItem = false;
                break;
            }
        }

        // Add item if it's new
        if(isNewItem)
            items.add(createNewReceiptTmpItem(product, receiptTmp));

        receiptTmp.setItems(items);

        return receiptFactory.mapUpdateReceiptResponse(
                receiptTmpRepository.save(receiptTmp));
    }

    @Override
    public BigDecimal retrieveDayTurnover(LocalDate day) {

        LocalDateTime startOfDay = day.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        List<Receipt> dayReceipts = receiptRepository.findByCreateTmsBetween(startOfDay, endOfDay);

        BigDecimal dayTurnover = dayReceipts.stream()
                .map(Receipt::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return dayTurnover;
    }

    @Override
    public Map<Department, BigDecimal> retrieveDepartmentDayTurnover(LocalDate day) {

        LocalDateTime startOfDay = day.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        List<Receipt> dayReceipts = receiptRepository.findByCreateTmsBetween(startOfDay, endOfDay);

        Map<Department, BigDecimal> departmentTurnover = new TreeMap<>();
        for (Department dept : Department.values()) {
            departmentTurnover.put(dept, BigDecimal.ZERO);
        }

        for(Receipt r : dayReceipts){
            for(ReceiptItem ri : r.getItems()){
                BigDecimal price = ri.getPrice();
                BigDecimal quantity = BigDecimal.valueOf(ri.getQuantity());
                BigDecimal currentItemTotal = price.multiply(quantity);
                Department department = ri.getDepartment();

                BigDecimal oldTotal = departmentTurnover.getOrDefault(department, BigDecimal.ZERO);
                departmentTurnover.put(department, oldTotal.add(currentItemTotal));
            }
        }

//        departmentTurnover = dayReceipts.stream()
//                .flatMap(receipt -> receipt.getItems().stream())
//                .collect(Collectors.groupingBy(ReceiptItem::getDepartment,
//                        Collectors.reducing(BigDecimal.ZERO,
//                                item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())),
//                                BigDecimal::add)));

        return departmentTurnover;
    }

    private ReceiptTmpItem createNewReceiptTmpItem(Product product, ReceiptTmp receiptTmp){
        ReceiptTmpItem item = new ReceiptTmpItem();

        item.setName(product.getName());
        item.setQuantity(1L);
        item.setPrice(product.getPrice());
        item.setDepartment(product.getDepartment());
        item.setReceiptTmp(receiptTmp);
        item.setProduct(product);

        return item;
    }
}
