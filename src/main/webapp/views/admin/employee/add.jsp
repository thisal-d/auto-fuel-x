<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/19/2025
  Time: 1:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Employee</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addEmployee-style1.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">
    <div class="form-container">
        <div class="form-header">
            <h1>Add New Employee</h1>
        </div>

        <a href="<%= request.getContextPath() %>/admin/employees/list" class="back-link">Back to List</a>

        <form action="<%= request.getContextPath() %>/admin/employee/add" method="post">
            <table class="form-table">
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstName" required></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lastName" required></td>
                </tr>
                <tr>
                    <td>Date of Birth</td>
                    <td><input type="date" name="dateOfBirth" required></td>
                </tr>
                <tr>
                    <td>Salary</td>
                    <td><input type="number" step="0.01" name="salary" required></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td>
                        <select name="status">
                            <option value="Active">Active</option>
                            <option value="Inactive">Inactive</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Hire Date</td>
                    <td><input type="date" name="hireDate" required></td>
                </tr>
                <tr>
                    <td>Address No</td>
                    <td><input type="text" name="addressNo"></td>
                </tr>
                <tr>
                    <td>Address Lane</td>
                    <td><input type="text" name="addressLane"></td>
                </tr>
                <tr>
                    <td>Address Area</td>
                    <td><input type="text" name="addressArea"></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="email" name="email" required></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" required></td>
                </tr>
                <tr>
                    <td>Type</td>
                    <td>
                        <select name="type" id="employeeType" onchange="toggleFields()" required>
                            <option value="">Select Type</option>
                            <option value="Admin">Admin</option>
                            <option value="Refuel Cashier">Refuel Cashier</option>
                            <option value="Service Center Staff">Service Center Staff</option>
                            <option value="Customer Care Officer">Customer Care Officer</option>
                        </select>
                    </td>
                </tr>
                <tr id="skillSetRow" class="conditional-field" style="display:none;">
                    <td>Skill Set</td>
                    <td><input type="text" name="skillSet"></td>
                </tr>
                <tr id="roleRow" class="conditional-field" style="display:none;">
                    <td>Role</td>
                    <td><input type="text" name="role"></td>
                </tr>
                <tr id="shiftRow" class="conditional-field" style="display:none;">
                    <td>Shift</td>
                    <td>
                        <select name="shift">
                            <option value="Morning">Morning</option>
                            <option value="Afternoon">Afternoon</option>
                            <option value="Night">Night</option>
                        </select>
                    </td>
                </tr>
            </table>

            <button type="submit" class="submit-btn">Add Employee</button>
        </form>
    </div>
</div>

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