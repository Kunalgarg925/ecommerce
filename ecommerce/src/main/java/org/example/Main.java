package org.example;

import org.example.adapter.OrderDatabase;
import org.example.model.*;
import org.example.service.CustomerRepository;
import org.example.service.OrderService;
import org.example.service.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        //rename this class to a better name, like ProductRepository.
        ProductRepository productRepository = new ProductRepository();

        List<Product> productList = getProductList();
        Catalogue mensCatalogue = new Catalogue("Mens",productList);
        Catalogue femaleCatalogue = new Catalogue("Female",productList);
        productRepository.addCatalogue(mensCatalogue);
        productRepository.addCatalogue(femaleCatalogue);

        // rename this to CustomerRepository
        CustomerRepository customerRepository = new CustomerRepository();

        Customer kunalInput = new Customer();
        kunalInput.setName("Kunal");
        kunalInput.setCreditCard(new CreditCard(9876543214528521L,744));
        kunalInput.setEmailId("kunalgarg925@gmail.com");
        kunalInput.setPhoneNumber(9416287031L);

        Customer tusharInput = new Customer();
        tusharInput.setName("Tushar");
        tusharInput.setCreditCard(new CreditCard(963288741256325L,325));
        tusharInput.setEmailId("willingTushar@gmail.com");
        tusharInput.setPhoneNumber(9872563142L);

        Customer tushar = customerRepository.createCustomer(tusharInput);
        Customer kunal = customerRepository.createCustomer(kunalInput);

        System.out.println(kunal);
//        System.out.println(customerRepository.getCustomerDetail("e7087e42-67ac-4061-8419-a3d9e4fb4f1d"));
        System.out.println(customerRepository.getCustomerDetail(tushar.getId()));

        // handling order

        OrderService orderService = new OrderService(productRepository);
        orderService.takeOrder(kunalInput.getId());
        Boolean confirmOrder = orderService.confirmOrder(kunalInput.getId());
        System.out.println("confirm order : " + confirmOrder);
        if(confirmOrder){
            System.out.println("true");
            String orderId = orderService.createOrder(kunal);
            orderService.deleteCart(kunalInput.getId());
            System.out.println("Order Details :- " + orderService.getOrderDetails(kunalInput.getId(),orderId));
        }else{
            System.out.println("false");
            System.out.println("Do you want add/remove items (true,false)?");
            Scanner scan = new Scanner(System.in);
            Boolean changesInOrder = scan.hasNext();
            if(changesInOrder){
                orderService.takeOrder(kunalInput.getId());
                String orderId = orderService.createOrder(kunal);
                orderService.deleteCart(kunalInput.getId());
                System.out.println("Order Details :- " + orderService.getOrderDetails(kunalInput.getId(),orderId));
            }else{
                System.out.println("No Order Placed");
            }
        }
//        System.out.println(" Total Cost Of Order -----> " + orderService.(kunalInput.getId()));
//        System.out.println(" Order Details :- " + orderService.getOrderDetail(kunalInput.getId()));
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