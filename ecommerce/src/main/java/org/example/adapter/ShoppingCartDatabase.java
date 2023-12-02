package org.example.adapter;

import org.example.model.ShoppingCart;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartDatabase {
    private final Map<String,ShoppingCart> shoppingCartList = new HashMap<>();

    // Add a shopping cart to the database
    public void addShoppingCart(String customerId, ShoppingCart shoppingCart) {
        shoppingCartList.put(customerId, shoppingCart);
    }

    // Retrieve a shopping cart from the database
    public ShoppingCart getShoppingCart(String customerId) {
        return shoppingCartList.get(customerId);
    }

    // Remove a shopping cart from the database
    public void removeShoppingCart(String customerId) {
        shoppingCartList.remove(customerId);
    }


}
