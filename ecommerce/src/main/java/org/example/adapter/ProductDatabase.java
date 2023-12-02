package org.example.adapter;

import org.example.model.Catalogue;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDatabase {
    private final Map<String,Catalogue> catalogueList = new HashMap<>();
    public void addCatalogue(Catalogue catalogue) {
        catalogueList.put(catalogue.getCatalogueName(),catalogue);
    }

    public void removeCatalogue(String catalogueName){
        if(catalogueList.containsKey(catalogueName)){
            catalogueList.remove(catalogueName);
        }
    }

    public List<Catalogue> getCatalogueList() {
        return new ArrayList<>(catalogueList.values());
    }

    public List<Product> getProductListOfCatalogue(String catalogueName) throws Exception{
        if(catalogueList.containsKey(catalogueName)){
            return catalogueList.get(catalogueName).getProductList();
        }
        throw new Exception("No Catalogue Available");

//        for(Catalogue iterateCatalogue : catalogueList){
//            if(iterateCatalogue.getCatalogueName().equals(catalogueName)){
//                return iterateCatalogue.getProductList();
//            }
//        }
    }

    public Catalogue getCatalogue(String catalogueName) throws Exception{
        if(catalogueList.containsKey(catalogueName)){
            return catalogueList.get(catalogueName);
        }
//        for(Catalogue iterateCatalogue : catalogueList){
//            if(iterateCatalogue.getCatalogueName().equals(catalogueName)){
//                return iterateCatalogue;
//            }
//        }
        throw new Exception("Catalogue is not Found");
    }

    public void addProduct(String catalogueName,List<Product> product) throws Exception {
        if(catalogueList.containsKey(catalogueName)){
            Catalogue catalogue = catalogueList.get(catalogueName);
            List<Product> productList = catalogue.getProductList();
            productList.addAll(product);
            catalogue.setProductList(productList);
            catalogueList.put(catalogueName,catalogue);
        }else{
            throw new Exception("Catalogue is not found");
        }
    }
    public void removeProduct(String catalogueName, List<Product> product) throws Exception {
        if(catalogueList.containsKey(catalogueName)){
            Catalogue catalogue = catalogueList.get(catalogueName);
            List<Product> productList = catalogue.getProductList();
            if(productList.contains(product)){
                productList.removeAll(product);
            }else{
                throw new Exception("Product is not found in this category");
            }
            catalogue.setProductList(productList);
            catalogueList.put(catalogueName,catalogue);
        }else{
            throw new Exception("Catalogue is not found");
        }
    }
    public Product getProduct(String catalogueName, String productName){
        System.out.println("catalogue list ---> " + catalogueList);
        if(catalogueList.containsKey(catalogueName)){
            List<Product> productList = catalogueList.get(catalogueName).getProductList();
            System.out.println("productList ----->" + productList);
            for(Product iterateProduct : productList){
                if(iterateProduct.getProductName().toLowerCase().equals(productName.toLowerCase())){
                    return iterateProduct;
                }
            }
        }

        return null;
    }
}
