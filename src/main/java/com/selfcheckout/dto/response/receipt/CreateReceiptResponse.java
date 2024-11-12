package com.selfcheckout.dto.response.receipt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReceiptResponse {

    private LocalDateTime createTms;
    private List<ReceiptItemResponse> items;
}
