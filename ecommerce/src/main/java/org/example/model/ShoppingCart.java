package org.example.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private String customerId;
    private List<CartLineItem> itemsList = new ArrayList<>();
    private Integer totalQuantity = 0;
    private float totalAmount = 0;

    public void addProduct(CartLineItem cartItem){
        this.itemsList.add(cartItem);
        this.itemsList.add(cartItem);
        this.totalQuantity += cartItem.getQuantity();
        this.totalAmount += cartItem.getProductDetail().getProductPrice() * cartItem.getQuantity();
    }

    public void removeProduct(CartLineItem cartItem, Integer quantity) throws Exception {
        if(this.itemsList.contains(cartItem)){
            Integer index = this.itemsList.indexOf(cartItem);
            if(cartItem.getQuantity() > quantity){
                cartItem.setQuantity(cartItem.getQuantity() - quantity);
                this.totalQuantity -= quantity;
                this.totalAmount -= quantity * cartItem.getProductDetail().getProductPrice();
                this.itemsList.add(index,cartItem);
            }else if(cartItem.getQuantity()  == quantity){
                this.itemsList.remove(cartItem);
                this.totalQuantity -= quantity;
                this.totalAmount -= quantity * cartItem.getProductDetail().getProductPrice();
            }else{
                throw new Exception("removed quanity is too high");
            }
        }
        this.totalAmount -= cartItem.getQuantity() * cartItem.getProductDetail().getProductPrice();
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "customerId='" + customerId + '\'' +
                ", itemsList=" + itemsList +
                ", totalQuantity=" + totalQuantity +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
