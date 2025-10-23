package com.example.autofuelx.service;

import com.example.autofuelx.dao.EmployeeDAO;
import com.example.autofuelx.model.Employee;

import java.sql.Date;
import java.util.List;

public class EmployeeService {
    private final EmployeeDAO employeeDAO;

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

    public void addEmployee(Employee employee) {
        boolean success =  employeeDAO.addEmployee(employee);
        System.out.println("Employee " + employee.getEmployeeID() + " Adding : " + success);
    }

    public void updateEmployee(Employee employee) {
        boolean success = employeeDAO.updateEmployee(employee);
        System.out.println("Employee " + employee.getEmployeeID() + " Updating : " + success);
    }

    public void deleteEmployee(int employeeID) {
        boolean success = employeeDAO.deleteEmployee(employeeID);
        System.out.println("Employee " + employeeID + " Deleting Status : " + success);
    }

    public List<Employee> getEmployeesByType(String type) {
        return employeeDAO.getEmployeesByType(type);
    }

    public void updateEmployeeStatus(int employeeID, String status) {
        boolean success = employeeDAO.updateEmployeeStatus(employeeID, status);
        System.out.println("Employee " + employeeID + " Updating Status : " + success);
    }

    public List<Employee> getEmployeesByTypeStatus(String type, String status) {
        return employeeDAO.getEmployeesByType(type, status);
    }

    public Employee validateEmployee(String email, String password, String role) {
        return employeeDAO.validateEmployee(email, password, role);
    }
}