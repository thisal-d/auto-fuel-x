<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/19/2025
  Time: 4:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.autofuelx.model.Employee" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Details</title>
</head>
<body>

<%

%>

<h1>Employee Details</h1>

<p>
    <a href="<%= request.getContextPath() %>/employees/list">Back to List</a>
</p>

<table border="1">
    <%
        Employee employee = (Employee) request.getAttribute("employee");
        if(employee != null) {
    %>
    <tr>
        <td>First Name</td>
        <td><%= employee.getFirstName() %></td>
    </tr>
    <tr>
        <td>Last Name</td>
        <td><%= employee.getLastName() %></td>
    </tr>
    <tr>
        <td>Date of Birth</td>
        <td><%= employee.getDateOfBirth() %></td>
    </tr>
    <tr>
        <td>Salary</td>
        <td><%= employee.getSalary() %></td>
    </tr>
    <tr>
        <td>Status</td>
        <td><%= employee.getStatus() %></td>
    </tr>
    <tr>
        <td>Hire Date</td>
        <td><%= employee.getHireDate() %></td>
    </tr>
    <tr>
        <td>Address No</td>
        <td><%= employee.getAddressNo() %></td>
    </tr>
    <tr>
        <td>Address Lane</td>
        <td><%= employee.getAddressLane() %></td>
    </tr>
    <tr>
        <td>Address Area</td>
        <td><%= employee.getAddressArea() %></td>
    </tr>
    <tr>
        <td>Email</td>
        <td><%= employee.getEmail() %></td>
    </tr>
    <% if("Service Center Staff".equals(employee.getType())) { %>
    <tr>
        <td>Skill Set</td>
        <td><%= employee.getSkillSet() %></td>
    </tr>
    <% } %>
    <% if("Admin".equals(employee.getType())) { %>
    <tr>
        <td>Role</td>
        <td><%= employee.getRole() %></td>
    </tr>
    <% } %>
    <% if("Refuel Cashier".equals(employee.getType())) { %>
    <tr>
        <td>Shift</td>
        <td><%= employee.getShift() %></td>
    </tr>
    <% } %>
    <tr>
        <td>Type</td>
        <td><%= employee.getType() %></td>
    </tr>
    <% } %>
</table>

<p>
    <a href="<%= request.getContextPath() %>/admin/employee/edit-form?id=<%= employee.getEmployeeID() %>">Edit</a>
    <form action="<%= request.getContextPath() %>/admin/employee/delete" method="post">
        input type="hidden" name="id" value="<%=employee.getEmployeeID()%>">
       <input type="submit" onclick="return confirm('Are you sure you want to delete this employee?')" value="Delete">
    </form>
</p>

</body>
</html>