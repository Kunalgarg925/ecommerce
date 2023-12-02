package org.example.adapter;

import org.example.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDatabase {
    private final Map<String, Customer> customerList = new HashMap<>();

    public Customer addCustomer(Customer customerDetails) throws Exception {
        if (!customerList.containsKey(customerDetails.getId())) {
            customerList.put(customerDetails.getId(), customerDetails);
            return customerDetails;
        }
        throw new Exception("User Already Exist");
    }

    public Customer getCustomer(String customerId) throws Exception {
        if(customerList.containsKey(customerId)){
            return customerList.get(customerId);
        }
        throw new Exception("No Customer Found");
    }

    public void removeCustomer(String customerId) {
        customerList.remove(customerId);
    }

    public Customer updateCustomerDetails(Customer customerDetails){
        customerList.put(customerDetails.getId(), customerDetails);
        return customerDetails;
    }

    public List<Customer> getAllCustomer() {
        return new ArrayList<>(customerList.values());
    }
}
