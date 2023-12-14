package org.example.service;

import org.example.adapter.ProductDatabase;
import org.example.model.Catalogue;
import org.example.model.Product;

import java.util.List;
import java.util.UUID;

public class ProductRepository {
    private ProductDatabase productDatabase;
    public ProductRepository(){
        this.productDatabase = new ProductDatabase();
    }

    public void addProduct(String catalogueName, List<Product> productList) throws Exception {
        try{
            if(catalogueName!= null){
                for(Product iterateProduct : productList){
                    iterateProduct.setProductId(UUID.randomUUID().toString());
                }
                productDatabase.addProduct(catalogueName,productList);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void removeProduct(String catalogueName, List<Product> productList) throws Exception {
        try{
            if(catalogueName!= null){
                productDatabase.removeProduct(catalogueName,productList);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addCatalogue(Catalogue catalogue) throws Exception {
        if(catalogue!= null) {
            catalogue.setCatalogueId(UUID.randomUUID().toString());
            List<Product> productList = catalogue.getProductList();
            for(Product iterateProduct : productList){
                iterateProduct.setProductId(UUID.randomUUID().toString());
            }
            catalogue.setProductList(productList);
            productDatabase.addCatalogue(catalogue);
        }else{
            throw new Exception("Catalogue not added successfully");
        }
    }

    public void removeCatalogue(String catalogueName) throws Exception {
        if(catalogueName!= null) {
            productDatabase.removeCatalogue(catalogueName);
        }else{
            throw new Exception("Catalogue not removed successfully");
        }
    }
    public List<Catalogue> getCatalogueList() throws Exception {
        try {
            return productDatabase.getCatalogueList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Product> getProductListOfCatalogue(String catalogueName) throws Exception {
        if(catalogueName != null) {
            return productDatabase.getProductListOfCatalogue(catalogueName);
        }
        return null;
    }
    public Catalogue getCatalogue(String catalogueName) throws Exception {
        if(catalogueName != null) {
            return productDatabase.getCatalogue(catalogueName);
        }
        return null;
    }

    public Product getProduct(String catalogueName, String productName) {
        System.out.println("catalogue name ---> " + catalogueName + " ---- product name ----> " + productName);
        if (catalogueName != null && productName != null) {
            System.out.println("return kar rha hu");
            return productDatabase.getProduct(catalogueName,productName);
        }
        return null;
    }
}
