package com.example.autofuelx.dao;

import com.example.autofuelx.model.Employee;
import com.example.autofuelx.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employee";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Employee employee = extractEmployeeFromResultSet(rs);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public Employee getEmployeeById(int id) {
        String query = "SELECT * FROM Employee WHERE EmployeeID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractEmployeeFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean addEmployee(Employee employee) {
        String query = "INSERT INTO Employee (FirstName, LastName, DateOfBirth, Salary, Status, " +
                "HireDate, AddressNo, AddressLane, AddressArea, Email, SkillSet, " +
                "Role, Shift, Type, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'default123')";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setDate(3, employee.getDateOfBirth());
            pstmt.setDouble(4, employee.getSalary());
            pstmt.setString(5, employee.getStatus());
            pstmt.setDate(6, employee.getHireDate());
            pstmt.setString(7, employee.getAddressNo());
            pstmt.setString(8, employee.getAddressLane());
            pstmt.setString(9, employee.getAddressArea());
            pstmt.setString(10, employee.getEmail());
            pstmt.setString(11, employee.getSkillSet());
            pstmt.setString(12, employee.getRole());
            pstmt.setString(13, employee.getShift());
            pstmt.setString(14, employee.getType());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEmployee(Employee employee) {
        String query = "UPDATE Employee SET FirstName = ?, LastName = ?, DateOfBirth = ?, " +
                "Salary = ?, Status = ?, HireDate = ?, AddressNo = ?, AddressLane = ?, " +
                "AddressArea = ?, Email = ?, SkillSet = ?, Role = ?, Shift = ?, Type = ? " +
                "WHERE EmployeeID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setDate(3, employee.getDateOfBirth());
            pstmt.setDouble(4, employee.getSalary());
            pstmt.setString(5, employee.getStatus());
            pstmt.setDate(6, employee.getHireDate());
            pstmt.setString(7, employee.getAddressNo());
            pstmt.setString(8, employee.getAddressLane());
            pstmt.setString(9, employee.getAddressArea());
            pstmt.setString(10, employee.getEmail());
            pstmt.setString(11, employee.getSkillSet());
            pstmt.setString(12, employee.getRole());
            pstmt.setString(13, employee.getShift());
            pstmt.setString(14, employee.getType());
            pstmt.setInt(15, employee.getEmployeeID());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEmployee(int id) {
        String query = "DELETE FROM Employee WHERE EmployeeID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Employee> getEmployeesByType(String type) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employee WHERE Type = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, type);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Employee employee = extractEmployeeFromResultSet(rs);
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public Employee validateAdmin(String email, String password) {
        Employee admin = null;
        String query = "SELECT * FROM Employee WHERE Email = ? AND Password = ? AND Type = 'Admin'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                admin = extractEmployeeFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeID(rs.getInt("EmployeeID"));
        employee.setFirstName(rs.getString("FirstName"));
        employee.setLastName(rs.getString("LastName"));
        employee.setDateOfBirth(rs.getDate("DateOfBirth"));
        employee.setSalary(rs.getDouble("Salary"));
        employee.setStatus(rs.getString("Status"));
        employee.setHireDate(rs.getDate("HireDate"));
        employee.setAddressNo(rs.getString("AddressNo"));
        employee.setAddressLane(rs.getString("AddressLane"));
        employee.setAddressArea(rs.getString("AddressArea"));
        employee.setEmail(rs.getString("Email"));
        employee.setSkillSet(rs.getString("SkillSet"));
        employee.setRole(rs.getString("Role"));
        employee.setShift(rs.getString("Shift"));
        employee.setType(rs.getString("Type"));
        return employee;
    }

}