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
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractEmployeeFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateEmployeeStatus(int employeeID, String status) {
        String query = "UPDATE Employee SET Status = ? WHERE EmployeeID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, employeeID);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addEmployee(Employee employee) {
        String query = "INSERT INTO Employee (FirstName, LastName, DateOfBirth, Salary, Status, " +
                "HireDate, AddressNo, AddressLane, AddressArea, Email, SkillSet, " +
                "Role, Shift, Type, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setDate(3, employee.getDateOfBirth());
            stmt.setDouble(4, employee.getSalary());
            stmt.setString(5, employee.getStatus());
            stmt.setDate(6, employee.getHireDate());
            stmt.setString(7, employee.getAddressNo());
            stmt.setString(8, employee.getAddressLane());
            stmt.setString(9, employee.getAddressArea());
            stmt.setString(10, employee.getEmail());
            stmt.setString(11, employee.getSkillSet());
            stmt.setString(12, employee.getRole());
            stmt.setString(13, employee.getShift());
            stmt.setString(14, employee.getType());
            stmt.setString(15, employee.getPassword());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEmployee(Employee employee) {
        String query = "UPDATE Employee SET FirstName = ?, LastName = ?, DateOfBirth = ?, " +
                "Salary = ?, Status = ?, HireDate = ?, AddressNo = ?, AddressLane = ?, " +
                "AddressArea = ?, Email = ?, Password = ?, SkillSet = ?, Role = ?, Shift = ?, Type = ? " +
                "WHERE EmployeeID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setDate(3, employee.getDateOfBirth());
            stmt.setDouble(4, employee.getSalary());
            stmt.setString(5, employee.getStatus());
            stmt.setDate(6, employee.getHireDate());
            stmt.setString(7, employee.getAddressNo());
            stmt.setString(8, employee.getAddressLane());
            stmt.setString(9, employee.getAddressArea());
            stmt.setString(10, employee.getEmail());
            stmt.setString(11, employee.getPassword());
            stmt.setString(12, employee.getSkillSet());
            stmt.setString(13, employee.getRole());
            stmt.setString(14, employee.getShift());
            stmt.setString(15, employee.getType());
            stmt.setInt(16, employee.getEmployeeID());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEmployee(int id) {
        String query = "DELETE FROM Employee WHERE EmployeeID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
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
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, type);

            try (ResultSet rs = stmt.executeQuery()) {
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

    public List<Employee> getEmployeesByType(String type, String status) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employee WHERE Type = ? AND Status = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, type);
            stmt.setString(1, status);

            try (ResultSet rs = stmt.executeQuery()) {
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

    public List<Employee> getEmployeesByTypeStatus(String type, String status) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employee WHERE Type = ? AND Status = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, type);
            stmt.setString(2, status);

            try (ResultSet rs = stmt.executeQuery()) {
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

    public List<Employee> getFilteredEmployees(String type, String status,
                                               Date hireDateFrom, Date hireDateTo,
                                               Double minSalary, Double maxSalary,
                                               String name, String ageGroup) {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE 1=1";
        List<Object> params = new ArrayList<>();

        // Add type filter
        if (type != null && !type.isEmpty()) {
            sql += " AND Type = ?";
            params.add(type);
        }

        // Add status filter
        if (status != null && !status.isEmpty()) {
            sql += " AND Status = ?";
            params.add(status);
        }

        // Add hire date range filter
        if (hireDateFrom != null) {
            sql += " AND HireDate >= ?";
            params.add(hireDateFrom);
        }
        if (hireDateTo != null) {
            sql += " AND HireDate <= ?";
            params.add(hireDateTo);
        }

        // Add salary range filter
        if (minSalary != null) {
            sql += " AND Salary >= ?";
            params.add(minSalary);
        }
        if (maxSalary != null) {
            sql += " AND Salary <= ?";
            params.add(maxSalary);
        }

        // Add name search filter (first or last name)
        if (name != null && !name.isEmpty()) {
            sql += " AND (FirstName LIKE ? OR LastName LIKE ?)";
            String namePattern = "%" + name + "%";
            params.add(namePattern);
            params.add(namePattern);
        }

        // Add age group filter
        if (ageGroup != null && !ageGroup.isEmpty()) {
            switch (ageGroup) {
                case "<30":
                    sql += " AND DATEDIFF(YEAR, DateOfBirth, GETDATE()) < 30";
                    break;
                case "30-50":
                    sql += " AND DATEDIFF(YEAR, DateOfBirth, GETDATE()) BETWEEN 30 AND 50";
                    break;
                case ">50":
                    sql += " AND DATEDIFF(YEAR, DateOfBirth, GETDATE()) > 50";
                    break;
            }
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters
            int i = 1;
            for (Object param : params) {
                if (param instanceof String) {
                    stmt.setString(i++, (String) param);
                } else if (param instanceof Date) {
                    stmt.setDate(i++, (Date) param);
                } else if (param instanceof Double) {
                    stmt.setDouble(i++, (Double) param);
                }
            }

            // Execute query
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    employees.add(extractEmployeeFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    // ... existing methods ...

    public Employee validateEmployee(String email, String password, String role) {
        Employee admin = null;
        String query = "SELECT * FROM Employee WHERE Email = ? AND Password = ? AND Type = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, role);

            ResultSet rs = stmt.executeQuery();
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
        employee.setPassword(rs.getString("Password"));
        employee.setEmail(rs.getString("Email"));
        employee.setSkillSet(rs.getString("SkillSet"));
        employee.setRole(rs.getString("Role"));
        employee.setShift(rs.getString("Shift"));
        employee.setType(rs.getString("Type"));
        return employee;
    }

}