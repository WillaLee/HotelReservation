package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private static Map<String, Customer> allCustomers = new HashMap<>();

    public static void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        allCustomers.put(email, customer);
    }

    public static Customer getCustomer(String customerEmail){
        return allCustomers.get(customerEmail);
    }

    public static Collection<Customer> getAllCustomers(){
        return allCustomers.values();
    }
}
