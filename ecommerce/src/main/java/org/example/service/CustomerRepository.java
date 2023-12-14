package org.example.service;

import java.util.List;
import java.util.UUID;


import org.example.adapter.CustomerDatabase;
import org.example.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerRepository {

    @Autowired
    private CustomerDatabase customerDatabase;
    public CustomerRepository(){
        this.customerDatabase = new CustomerDatabase();
    }
    public Customer createCustomer(Customer customerDetails){
        try{
            if(customerDetails != null) {
                customerDetails.setId(UUID.randomUUID().toString());
                return customerDatabase.addCustomer(customerDetails);
            }
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public Customer updateCustomer(Customer customerDetails) {
        try {
            if (customerDetails != null) {
                return customerDatabase.updateCustomerDetails(customerDetails);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public void deleteCustomer(String customerId){
        if(customerId != null){
            customerDatabase.removeCustomer(customerId);
        }
    }

    public Customer getCustomerDetail(String customerId){
        try{
            if(customerId != null){
                return customerDatabase.getCustomer(customerId);
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public List<Customer> getAllCustomers(){
        return customerDatabase.getAllCustomer();
    }
}
