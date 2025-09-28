<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.model.Employee" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Employees</title>
</head>
<body>
<h1>All Employees</h1>

<p>
    <a href="<%= request.getContextPath() %>/employees/add-form">Add New Employee</a> |
    <a href="<%= request.getContextPath() %>/employees/by-type?type=Admin">Admins</a> |
    <a href="<%= request.getContextPath() %>/employees/by-type?type=Refuel Cashier">Refuel Cashiers</a> |
    <a href="<%= request.getContextPath() %>/employees/by-type?type=Service Center Staff">Service Center Staff</a> |
    <a href="<%= request.getContextPath() %>/employees/by-type?type=Customer Care Officer">Customer Care Officers</a>
</p>

<table border="1">
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Type</th>
        <th>Actions</th>
    </tr>
    <%
        List<Employee> employees = (List<Employee>) request.getAttribute("employees");
        if(employees != null) {
            for(Employee employee : employees) {
    %>
    <tr>
        <td><%= employee.getEmployeeID() %></td>
        <td><%= employee.getFirstName() %></td>
        <td><%= employee.getLastName() %></td>
        <td><%= employee.getEmail() %></td>
        <td><%= employee.getType() %></td>
        <td>
            <a href="<%= request.getContextPath() %>/employees/view?id=<%= employee.getEmployeeID() %>">View</a>
            <a href="<%= request.getContextPath() %>/employees/edit-form?id=<%= employee.getEmployeeID() %>">Edit</a>
            <a href="<%= request.getContextPath() %>/employees/delete?id=<%= employee.getEmployeeID() %>"
               onclick="return confirm('Are you sure you want to delete this employee?')">Delete</a>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>