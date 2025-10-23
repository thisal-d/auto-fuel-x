package com.example.autofuelx.service;


import com.example.autofuelx.dao.EmployeePhoneNumberDAO;
import com.example.autofuelx.model.EmployeePhoneNumber;

import java.util.List;

public class EmployeePhoneNumberService {

    private final EmployeePhoneNumberDAO phoneDAO;

    public EmployeePhoneNumberService() {
        this.phoneDAO = new EmployeePhoneNumberDAO();
    }

    // add a phone number
    public boolean addPhoneNumber(int employeeID, String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }
        EmployeePhoneNumber phone = new EmployeePhoneNumber(employeeID, phoneNumber.trim());
        return phoneDAO.addPhoneNumber(phone);
    }

    // del a phone number
    public boolean deletePhoneNumber(int employeeID, String phoneNumber) {
        return phoneDAO.deletePhoneNumber(employeeID, phoneNumber);
    }

    // get all phone numbers for a employee
    public List<String> getPhoneNumbersByEmployee(int employeeID) {
        return phoneDAO.getPhoneNumbersByEmployee(employeeID);
    }
}
