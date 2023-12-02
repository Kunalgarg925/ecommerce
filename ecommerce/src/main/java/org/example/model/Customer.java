package org.example.model;

import lombok.Data;

@Data
public class Customer {
    private String Id;
    private String name;
    private long phoneNumber;
    private String emailId;
    private CreditCard creditCard;

    @Override
    public String toString() {
        return "Customer{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", emailId='" + emailId + '\'' +
                ", creditCard=" + creditCard +
                '}';
    }
}
