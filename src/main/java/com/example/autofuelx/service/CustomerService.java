package com.example.autofuelx.service;


import com.example.autofuelx.dao.CustomerDAO;
import com.example.autofuelx.model.Customer;

import java.util.List;

public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public boolean registerCustomer(Customer customer) {
        return customerDAO.registerCustomer(customer);
    }

    public Customer loginCustomer(String email, String password) {
        return customerDAO.loginCustomer(email, password);
    }

    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }

    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }

    public void deleteCustomer(int customerID) {
        boolean success = customerDAO.deleteCustomer(customerID);
        if (success) {
            System.out.println("Customer ID " + customerID + " Delete status: " + success);
        } else {
            System.err.println("Failed to Delete Customer ID " + customerID);
        }
    }

    public void updateCustomer(Customer customer) {
        boolean success = customerDAO.updateCustomer(customer);
        if (success) {
            System.out.println("Customer ID " + customer.getCustomerID() + " Delete status: " + success);
        } else {
            System.err.println("Failed to Delete Customer ID " + customer.getCustomerID());
        }
    }

    public Customer getCustomerByEmail(String email) {
        return customerDAO.getCustomerByEmail(email);
    }

}
