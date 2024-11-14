package com.selfcheckout.dto.enumeration;

import lombok.Getter;

@Getter
public enum Department {
    MEAT("Meat"), // Fresh meat cuts
    DELI("Deli"), // Deli meats, cheeses, and prepared foods
    DAIRY("Dairy"), // Milk, yogurt, cheese, and butter
    BAKERY("Bakery"), // Bread, pastries, and cakes
    FROZEN("Frozen"), // Frozen foods
    GROCERY("Grocery"), // Canned goods, dry goods, and packaged foods
    PRODUCE("Produce"), // Fresh fruits and vegetables
    BEVERAGES("Beverages"), // Beverages like water, soda, and juice
    HOUSEHOLD("Household"), // Household cleaning supplies and paper products
    PERSONAL_CARE("Personal Care"); // Personal care products like shampoo, soap, and cosmetics

    private final String type;

    Department(String type) {
        this.type = type;
    }

}
