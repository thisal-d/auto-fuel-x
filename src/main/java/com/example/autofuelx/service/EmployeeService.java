package com.example.autofuelx.service;

import com.example.autofuelx.dao.EmployeeDAO;
import com.example.autofuelx.model.Employee;
import com.example.autofuelx.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class EmployeeService {
    private EmployeeDAO employeeDAO;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public Employee getEmployeeById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    public List<Employee> getFilteredEmployees(String type, String status,
                                               Date hireDateFrom, Date hireDateTo,
                                               Double minSalary, Double maxSalary,
                                               String name, String ageGroup) {
        return employeeDAO.getFilteredEmployees(type, status, hireDateFrom, hireDateTo,
                minSalary, maxSalary, name, ageGroup);
    }

    public boolean addEmployee(Employee employee) {
        return employeeDAO.addEmployee(employee);
    }

    public boolean updateEmployee(Employee employee) {
        return employeeDAO.updateEmployee(employee);
    }

    public boolean deleteEmployee(int id) {
        return employeeDAO.deleteEmployee(id);
    }

    public List<Employee> getEmployeesByType(String type) {
        return employeeDAO.getEmployeesByType(type);
    }

    public List<Employee> getEmployeesByTypeStatus(String type, String status) {
        return employeeDAO.getEmployeesByType(type);
    }

    public Employee validateAdmin(String email, String password) {
        return employeeDAO.validateAdmin(email, password);
    }
}