package com.selfcheckout.dto.enumeration;

import lombok.Getter;

@Getter
public enum Department {
    PRODUCE("Produce"), // Fresh fruits and vegetables
    MEAT("Meat"), // Fresh meat cuts
    BAKERY("Bakery"), // Bread, pastries, and cakes
    DAIRY("Dairy"), // Milk, yogurt, cheese, and butter
    FROZEN("Frozen"), // Frozen foods
    GROCERY("Grocery"), // Canned goods, dry goods, and packaged foods
    DELI("Deli"), // Deli meats, cheeses, and prepared foods
    BEVERAGES("Beverages"), // Beverages like water, soda, and juice
    HOUSEHOLD("Household"), // Household cleaning supplies and paper products
    PERSONAL_CARE("Personal Care"); // Personal care products like shampoo, soap, and cosmetics

    private final String type;

    Department(String type) {
        this.type = type;
    }

}
