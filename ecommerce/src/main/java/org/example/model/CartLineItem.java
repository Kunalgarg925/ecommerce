package org.example.model;

import lombok.Data;

@Data
public class CartLineItem {
    private Product productDetail;
    private Integer quantity;

    public CartLineItem(Product productDetail, Integer quantity) {
        this.productDetail = productDetail;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartLineItem{" +
                "productDetail=" + productDetail +
                ", quantity=" + quantity +
                '}';
    }
}
