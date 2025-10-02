package com.example.autofuelx.service;


import com.example.autofuelx.dao.CustomerDAO;
import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.Customer;
import com.example.autofuelx.model.Customer;

import java.util.List;

public class CustomerService {

    private CustomerDAO customerDAO;

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

    public boolean deleteCustomer(int id) {
        return customerDAO.deleteCustomer(id);
    }

    public boolean updateCustomer(Customer customer) {
        return customerDAO.updateCustomer(customer);
    }

    public Customer getCustomerByEmail(String email) {
        return customerDAO.getCustomerByEmail(email);
    }

}
