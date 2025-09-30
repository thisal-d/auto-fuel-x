package com.example.autofuelx.service;


import com.example.autofuelx.dao.CustomerPhoneNumberDAO;
import com.example.autofuelx.model.CustomerPhoneNumber;

import java.util.List;

public class CustomerPhoneNumberService {

    private final CustomerPhoneNumberDAO phoneDAO;

    public CustomerPhoneNumberService() {
        this.phoneDAO = new CustomerPhoneNumberDAO();
    }

    // Add a phone number
    public boolean addPhoneNumber(int customerID, String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false; // validation: don't allow empty numbers
        }
        CustomerPhoneNumber phone = new CustomerPhoneNumber(customerID, phoneNumber.trim());
        return phoneDAO.addPhoneNumber(phone);
    }

    // Delete a phone number
    public boolean deletePhoneNumber(int customerID, String phoneNumber) {
        return phoneDAO.deletePhoneNumber(customerID, phoneNumber);
    }

    // Get all phone numbers for a customer
    public List<String> getPhoneNumbersByCustomer(int customerID) {
        return phoneDAO.getPhoneNumbersByCustomer(customerID);
    }
}
