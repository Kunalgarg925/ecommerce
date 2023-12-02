package org.example.service;

import org.example.adapter.ShoppingCartDatabase;
import org.example.model.CartLineItem;
import org.example.model.Catalogue;
import org.example.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderService {

    private final ShoppingCartDatabase shoppingCartdatabase;
    private final ProductManagement productManagement;
    public OrderService(ProductManagement productManagement){
        this.shoppingCartdatabase = new ShoppingCartDatabase();
        this.productManagement = productManagement;
    }
    public void takeOrder(String customerId) throws Exception {
        ShoppingCart myCart = new ShoppingCart();
        if(shoppingCartdatabase.getShoppingCart(customerId) != null){
            myCart = shoppingCartdatabase.getShoppingCart(customerId);
        }else{
            myCart.setCustomerId(customerId);
        }
        System.out.println(" ======== Cart Item List ========== ");
        if(myCart.getItemsList() != null){
            System.out.println(myCart.getItemsList());
        }else{
            System.out.println("No item in your cart");
        }
        Integer chooseOptionNumber = showOptions();
        while(chooseOptionNumber < 3){
            if(chooseOptionNumber == 1){
                String catalogueName = chooseCatalogueName();
                myCart = addProduct(catalogueName,myCart);
            } else if (chooseOptionNumber == 2) {
                myCart = removeProduct(myCart);
            }
            chooseOptionNumber = showOptions();
        }
        if(chooseOptionNumber == 3){
            shoppingCartdatabase.addShoppingCart(customerId,myCart);
        }

    }

    public float getTotalCost(String customerId){
        if(customerId != null){
            return shoppingCartdatabase.getShoppingCart(customerId).getTotalAmount();
        }
        return 0;
    }

    public ShoppingCart getOrderDetail(String customerId){
        if(customerId != null){
            return shoppingCartdatabase.getShoppingCart(customerId);
        }
        return null;
    }

    private ShoppingCart removeProduct(ShoppingCart myCart) throws Exception {
        System.out.println(" ====== Cart Item List ====== ");
        List<CartLineItem> cartItems = myCart.getItemsList();
        System.out.println(cartItems);
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose product for remove");
        String removeMoreItem = "yes";
        while(!removeMoreItem.equals("no")){
            System.out.print("Enter Product Name : ");
            String productName = scan.nextLine();
            for(CartLineItem iterateCartLineItem : cartItems){
                if(iterateCartLineItem.getProductDetail().getProductName().toLowerCase().equals(productName)){
                    System.out.print("Enter Quantity : ");
                    Integer quantity = scan.nextInt();
                    if(quantity <= 0){
                        quantity = 1;
                    }
                    myCart.removeProduct(iterateCartLineItem,quantity);
                }
            }
            System.out.print("Do you want to buy more items (yes/no) : ");
            removeMoreItem = scan.nextLine().toLowerCase();
        }
        return myCart;
    }

    private ShoppingCart addProduct(String catalogueName,ShoppingCart myCart) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println(productManagement.getProductListOfCatalogue(catalogueName));
        System.out.println("Choose product from the above product list ");
        String addMoreItem = "yes";
        System.out.println("bhar hu");
        while(!addMoreItem.equals("no")){
            System.out.println("andar hu");
            System.out.print("Enter Product Name : ");
            String productName = scan.nextLine();
            if(productManagement.getProduct(catalogueName,productName).getProductName().toLowerCase().equals(productName.toLowerCase())){
                System.out.print("Enter Quantity : ");
                Integer quantity = scan.nextInt();
                if(quantity <= 0){
                    quantity = 1;
                }
                CartLineItem cartItem = new CartLineItem(productManagement.getProduct(catalogueName,productName),quantity);
                myCart.addProduct(cartItem);
            }
            System.out.println("Do you want to buy more items (yes/no) : ");
            addMoreItem = scan.next();
            System.out.println("add more item --- value ---" + addMoreItem);
        }
        System.out.println("loop end");
        return myCart;
    }


    private String chooseCatalogueName() throws Exception {
        List<String> catalogueNames = new ArrayList<>();
        for(Catalogue iterateCatalogue : productManagement.getCatalogueList()){
            catalogueNames.add(iterateCatalogue.getCatalogueName());
        }
        System.out.println(catalogueNames);
        System.out.println("Choose gender from the above catalogues :");
        Scanner scan = new Scanner(System.in);
        String catalogueName = scan.nextLine();
        return catalogueName;
    }

    private Integer showOptions(){
        Scanner scan = new Scanner(System.in);
        System.out.println(" ===== Choose Number ======");
        System.out.println(" ===== 1. Add Item ====== ");
        System.out.println(" ===== 2. Remove Item ====== ");
        System.out.println(" ===== 3. Submit ====== ");
        Integer chooseOptionNumber = scan.nextInt();
        return chooseOptionNumber;
    }
}
