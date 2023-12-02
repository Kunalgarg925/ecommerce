package org.example;

import org.example.model.*;
import org.example.service.CustomerManagement;
import org.example.service.OrderService;
import org.example.service.ProductManagement;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        ProductManagement productManagement = new ProductManagement();
        List<Product> productList = getProductList();
        Catalogue mensCatalogue = new Catalogue("Mens",productList);
        Catalogue femaleCatalogue = new Catalogue("Female",productList);
        productManagement.addCatalogue(mensCatalogue);
        productManagement.addCatalogue(femaleCatalogue);

        CustomerManagement customerHandler = new CustomerManagement();

        Customer kunal = new Customer();
        kunal.setName("Kunal");
        kunal.setCreditCard(new CreditCard(9876543214528521L,744));
        kunal.setEmailId("kunalgarg925@gmail.com");
        kunal.setPhoneNumber(9416287031L);

        Customer tushar = new Customer();
        tushar.setName("Tushar");
        tushar.setCreditCard(new CreditCard(963288741256325L,325));
        tushar.setEmailId("willingTushar@gmail.com");
        tushar.setPhoneNumber(9872563142L);
        Customer customerDetail = customerHandler.createCustomer(tushar);

        System.out.println(customerHandler.createCustomer(kunal));
//        System.out.println(customerHandler.getCustomerDetail("e7087e42-67ac-4061-8419-a3d9e4fb4f1d"));
        System.out.println(customerHandler.getCustomerDetail(customerDetail.getId()));

        // handling order

        OrderService orderHandler = new OrderService(productManagement);
        orderHandler.takeOrder(kunal.getId());
        System.out.println(" Total Cost Of Order -----> " + orderHandler.getTotalCost(kunal.getId()));
        System.out.println(" Order Details :- " + orderHandler.getOrderDetail(kunal.getId()));
    }

    private static List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product("Black Berry Shirt","1102",Category.Shirts,1200);
        Product product2 = new Product("Grape Wine Kurta", "2105", Category.Kurta,2500);
        Product product3 = new Product("Denim Blue TShirt", "1305", Category.Tshirt,800);
        Product product4 = new Product("Blue Line Shirt", "1002", Category.Shirts,1050);
        Product product5 = new Product("Chikankari Kurta", "1523", Category.Kurta,3100);
        Product product6 = new Product("Solid Black SweatShirt", "4562", Category.SweatShirt,1600);
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        productList.add(product6);
        return productList;
    }


}