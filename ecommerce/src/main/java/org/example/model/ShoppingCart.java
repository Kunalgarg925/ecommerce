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
        for(CartLineItem iterateItem : this.itemsList){
            String productName = iterateItem.getProductDetail().getProductName();
            if(cartItem.getProductDetail().getProductName().equalsIgnoreCase(productName)){
                int quantity = iterateItem.getQuantity();
                int newItemQuantity = cartItem.getQuantity();
                cartItem.setQuantity(quantity + newItemQuantity);
                this.totalQuantity -= quantity;
                this.totalAmount -= iterateItem.getProductDetail().getProductPrice() * quantity;
                this.itemsList.remove(iterateItem);
                break;
            }
        }
        this.itemsList.add(cartItem);
        this.totalQuantity += cartItem.getQuantity();
        this.totalAmount += cartItem.getProductDetail().getProductPrice() * cartItem.getQuantity();
    }

    public void removeProduct(CartLineItem cartItem, Integer quantity) throws Exception {
        if(this.itemsList.contains(cartItem)){
            Integer index = this.itemsList.indexOf(cartItem);
            if(cartItem.getQuantity() > quantity){
                cartItem.setQuantity(cartItem.getQuantity() - quantity);
                this.itemsList.set(index,cartItem);
            }else if(cartItem.getQuantity().equals(quantity)){
                this.itemsList.remove(cartItem);
            }else{
                throw new Exception("removed quanity is too high");
            }
            this.totalQuantity -= quantity;
            this.totalAmount -= quantity * cartItem.getProductDetail().getProductPrice();
        }else{
            System.out.println("Item is not found please enter right product name");
        }

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
