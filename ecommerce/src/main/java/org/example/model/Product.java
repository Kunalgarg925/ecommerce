package org.example.model;

import lombok.Data;

@Data
public class Product {
    private String productId;
    private String productName;
    private String productCode;
    private Category productCategory;

    private Integer productPrice;

    public Product(String productName, String productCode, Category productCategory, Integer productPrice) {
        this.productName = productName;
        this.productCode = productCode;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productCategory=" + productCategory +
                ", productPrice=" + productPrice +
                '}';
    }
}
