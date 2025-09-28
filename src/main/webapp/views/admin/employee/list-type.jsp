<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/19/2025
  Time: 4:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.model.Employee" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= request.getAttribute("employeeType") %> Employees</title>
</head>
<body>
<h1><%= request.getAttribute("employeeType") %> Employees</h1>

<p>
    <a href="<%= request.getContextPath() %>/employees/list">Back to All Employees</a> |
    <a href="<%= request.getContextPath() %>/employees/add-form">Add New Employee</a>
</p>

<table border="1">
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <%
            String employeeType = (String) request.getAttribute("employeeType");
            if("Service Center Staff".equals(employeeType)) {
        %>
        <th>Skill Set</th>
        <% } %>
        <% if("Admin".equals(employeeType)) { %>
        <th>Role</th>
        <% } %>
        <% if("Refuel Cashier".equals(employeeType)) { %>
        <th>Shift</th>
        <% } %>
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
        <% if("Service Center Staff".equals(employeeType)) { %>
        <td><%= employee.getSkillSet() %></td>
        <% } %>
        <% if("Admin".equals(employeeType)) { %>
        <td><%= employee.getRole() %></td>
        <% } %>
        <% if("Refuel Cashier".equals(employeeType)) { %>
        <td><%= employee.getShift() %></td>
        <% } %>
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