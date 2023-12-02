package org.example.model;
import java.util.List;
import lombok.Data;

@Data
public class Catalogue {
    private String catalogueId;
    private String catalogueName;
    private List<Product> productList;

    public Catalogue(String catalogueName, List<Product> productList) {
        this.catalogueName = catalogueName;
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Catalogue{" +
                "catalogueId='" + catalogueId + '\'' +
                ", catalogueName='" + catalogueName + '\'' +
                ", productList=" + productList +
                '}';
    }
}
