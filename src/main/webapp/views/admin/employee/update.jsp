<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/19/2025
  Time: 4:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.autofuelx.model.Employee" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Employee</title>
</head>
<body>
<h1>Edit Employee</h1>

<p>
    <a href="<%= request.getContextPath() %>/employees/list">Back to List</a>
</p>

<form action="<%= request.getContextPath() %>/employees/update" method="post">
    <%
        Employee employee = (Employee) request.getAttribute("employee-update");
        if(employee != null) {
    %>
    <input type="hidden" name="employeeID" value="<%= employee.getEmployeeID() %>">

    <table border="1">
        <tr>
            <td>First Name</td>
            <td><input type="text" name="firstName" value="<%= employee.getFirstName() %>" required></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" name="lastName" value="<%= employee.getLastName() %>" required></td>
        </tr>
        <tr>
            <td>Date of Birth</td>
            <td><input type="date" name="dateOfBirth" value="<%= employee.getDateOfBirth() %>" required></td>
        </tr>
        <tr>
            <td>Salary</td>
            <td><input type="number" step="0.01" name="salary" value="<%= employee.getSalary() %>" required></td>
        </tr>
        <tr>
            <td>Status</td>
            <td>
                <select name="status">
                    <option value="Active" <%= "Active".equals(employee.getStatus()) ? "selected" : "" %>>Active</option>
                    <option value="Inactive" <%= "Inactive".equals(employee.getStatus()) ? "selected" : "" %>>Inactive</option>
                    <option value="On Leave" <%= "On Leave".equals(employee.getStatus()) ? "selected" : "" %>>On Leave</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Hire Date</td>
            <td><input type="date" name="hireDate" value="<%= employee.getHireDate() %>" required></td>
        </tr>
        <tr>
            <td>Address No</td>
            <td><input type="text" name="addressNo" value="<%= employee.getAddressNo() %>"></td>
        </tr>
        <tr>
            <td>Address Lane</td>
            <td><input type="text" name="addressLane" value="<%= employee.getAddressLane() %>"></td>
        </tr>
        <tr>
            <td>Address Area</td>
            <td><input type="text" name="addressArea" value="<%= employee.getAddressArea() %>"></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="email" name="email" value="<%= employee.getEmail() %>" required></td>
        </tr>
        <tr>
            <td>Type</td>
            <td>
                <select name="type" id="employeeType" onchange="toggleFields()" required>
                    <option value="">Select Type</option>
                    <option value="Admin" <%= "Admin".equals(employee.getType()) ? "selected" : "" %>>Admin</option>
                    <option value="Refuel Cashier" <%= "Refuel Cashier".equals(employee.getType()) ? "selected" : "" %>>Refuel Cashier</option>
                    <option value="Service Center Staff" <%= "Service Center Staff".equals(employee.getType()) ? "selected" : "" %>>Service Center Staff</option>
                    <option value="Customer Care Officer" <%= "Customer Care Officer".equals(employee.getType()) ? "selected" : "" %>>Customer Care Officer</option>
                </select>
            </td>
        </tr>
        <tr id="skillSetRow" style="<%= "Service Center Staff".equals(employee.getType()) ? "" : "display:none;" %>">
            <td>Skill Set</td>
            <td><input type="text" name="skillSet" value="<%= employee.getSkillSet() %>"></td>
        </tr>
        <tr id="roleRow" style="<%= "Admin".equals(employee.getType()) ? "" : "display:none;" %>">
            <td>Role</td>
            <td><input type="text" name="role" value="<%= employee.getRole() %>"></td>
        </tr>
        <tr id="shiftRow" style="<%= "Refuel Cashier".equals(employee.getType()) ? "" : "display:none;" %>">
            <td>Shift</td>
            <td>
                <select name="shift">
                    <option value="Morning" <%= "Morning".equals(employee.getShift()) ? "selected" : "" %>>Morning</option>
                    <option value="Afternoon" <%= "Afternoon".equals(employee.getShift()) ? "selected" : "" %>>Afternoon</option>
                    <option value="Night" <%= "Night".equals(employee.getShift()) ? "selected" : "" %>>Night</option>
                </select>
            </td>
        </tr>
    </table>

    <input type="submit" value="Update Employee">
    <% } %>
</form>

<script>
    function toggleFields() {
        var type = document.getElementById("employeeType").value;
        document.getElementById("skillSetRow").style.display = type === "Service Center Staff" ? "" : "none";
        document.getElementById("roleRow").style.display = type === "Admin" ? "" : "none";
        document.getElementById("shiftRow").style.display = type === "Refuel Cashier" ? "" : "none";
    }
</script>
</body>
</html>