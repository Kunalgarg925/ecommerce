package org.example.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private String orderId;
    private Customer customerDetails;
    private List<CartLineItem> itemsList;
    private Integer totalQuantity;
    private float totalAmount;
//    public String getId(){
//        return this.orderId;
//    }
//    public Integer getTotalQuantity(){
//        return this.totalQuantity;
//    }
//    public float getTotalAmount(){
//        return this.totalAmount;
//    }
//    public List<CartLineItem> getItemsList(){
//        return this.itemsList;
//    }



    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerDetails=" + customerDetails +
                ", itemsList=" + itemsList +
                ", totalQuantity=" + totalQuantity +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
