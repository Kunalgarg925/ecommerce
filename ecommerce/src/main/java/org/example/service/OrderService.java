package org.example.service;

import lombok.var;
import org.example.adapter.ShoppingCartDatabase;
import org.example.model.CartLineItem;
import org.example.model.Catalogue;
import org.example.model.Product;
import org.example.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderService {

    private final ShoppingCartDatabase shoppingCartdatabase;
    private final ProductRepository productRepository;
    public OrderService(ProductRepository productRepository){
        this.shoppingCartdatabase = new ShoppingCartDatabase();
        this.productRepository = productRepository;
    }
    public void takeOrder(String customerId) throws Exception {
        // 1. take order
        // 2. update cart

        ShoppingCart cart = shoppingCartdatabase.getShoppingCart(customerId);
        if (cart == null) {
            cart = createCart(customerId);
        }


//        ShoppingCart myCart = new ShoppingCart();
//        if(shoppingCartdatabase.getShoppingCart(customerId) != null){
//            myCart = shoppingCartdatabase.getShoppingCart(customerId);
//        }else{
//            myCart.setCustomerId(customerId);
//        }

//        System.out.println(" ======== Cart Item List ========== ");
//        if(myCart.getItemsList() != null){
//            System.out.println(myCart.getItemsList());
//        }else{
//            System.out.println("No item in your cart");
//        }

        // you can replace customer action with an enum as the number of actions are predefined.
        int customerAction = takeCustomerAction();
        while(customerAction != 3) {
            if(customerAction == 1) {
                String catalogueName = chooseCatalogueName();
                addProduct(catalogueName,cart);
            } else if (customerAction == 2) {
                removeProduct(cart);
            }
            customerAction = takeCustomerAction();
        }

        shoppingCartdatabase.addShoppingCart(customerId, cart);
    }

    private ShoppingCart createCart(String customerId) {
        ShoppingCart newCart =  new ShoppingCart();
        newCart.setCustomerId(customerId);

        shoppingCartdatabase.addShoppingCart(newCart);
        return newCart;
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

    private void removeProduct(ShoppingCart myCart) throws Exception {
        System.out.println(" ====== Cart Item List ====== ");
        List<CartLineItem> cartItems = myCart.getItemsList();
        System.out.println(cartItems);

        System.out.println("Choose product for remove");

        String removeMoreItem = "yes";
        while(removeMoreItem.equals("yes")){
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter Product Name : ");
            String productName = scan.nextLine();

            for(CartLineItem iterateCartLineItem : cartItems){
                if(iterateCartLineItem.getProductDetail().getProductName().equalsIgnoreCase(productName.trim())){
                    System.out.print("Enter Quantity : ");
                    int quantity = scan.nextInt();
                    if(quantity <= 0){
                        quantity = 1;
                    }
                    myCart.removeProduct(iterateCartLineItem,quantity);
                    break;
                }
            }
            System.out.print("Do you want to remove more items (yes/no) : ");
            removeMoreItem = scan.next().trim().toLowerCase();
        }
    }

    private void addProduct(String catalogueName, ShoppingCart cart) throws Exception {
        // 1. show all products under the given catalogue
        // 2. take customer input
        // 3. update cart

        List<Product> products = productRepository.getProductListOfCatalogue(catalogueName);
        presentProductsToCustomer(products);

        System.out.println("Choose product from the above product list ");

        String addMoreItem = "yes";
        while(addMoreItem.equals("yes")){
            Scanner scan = new Scanner(System.in);

            System.out.print("Enter Product Name : ");
            String productName = scan.nextLine().trim().toLowerCase();

            var product = productRepository.getProduct(catalogueName,productName);
            if(product != null){
                System.out.print("Enter Quantity : ");
                int quantity = scan.nextInt();
                if(quantity <= 0){
                    quantity = 1;
                }

                CartLineItem cartItem = new CartLineItem(product,quantity);
                cart.addProduct(cartItem);
            }

            System.out.println("Do you want to buy more products (yes/no) : ");
            addMoreItem = scan.next().trim().toLowerCase();
        }
    }

    private void presentProductsToCustomer(List<Product> products) {
        // might wanna show only product names
        System.out.println(products);
    }


    private String chooseCatalogueName() throws Exception {
        List<String> catalogueNames = new ArrayList<>();
        for(Catalogue iterateCatalogue : productRepository.getCatalogueList()){
            catalogueNames.add(iterateCatalogue.getCatalogueName());
        }
        System.out.println(catalogueNames);
        System.out.println("Choose gender from the above catalogues :");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    private Integer takeCustomerAction(){
        Scanner scan = new Scanner(System.in);
        System.out.println(" ===== Choose Number ======");
        System.out.println(" ===== 1. Add Item ====== ");
        System.out.println(" ===== 2. Remove Item ====== ");
        System.out.println(" ===== 3. Submit ====== ");
        return scan.nextInt();
    }
}
