package org.example.adapter;

import org.example.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDatabase {
    private final Map<String, List<Order>> customerOrderList = new HashMap<>();

    public void createOrder(String customerId, Order customerOrder){
        if(customerOrderList.containsKey(customerId)){
            customerOrderList.get(customerId).add(customerOrder);
        }else{
            List<Order> orderList = new ArrayList<>();
            orderList.add(customerOrder);
            customerOrderList.put(customerId,orderList);
        }
    }

    public List<Order> getCustomerAllOrder(String customerId){
        if(customerOrderList.containsKey(customerId)){
            return customerOrderList.get(customerId);
        }else{
            throw new RuntimeException("No Order Found");
        }
    }

    public Order getOrderDetails(String customerId, String orderId){
        if(customerOrderList.containsKey(customerId)){
            for(Order iterateOrder : customerOrderList.get(customerId)){
                if(iterateOrder.getOrderId().equalsIgnoreCase(orderId)){
                    return iterateOrder;
                }
            }
        }
        throw new RuntimeException("Not Found");
    }

}
