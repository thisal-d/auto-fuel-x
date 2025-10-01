<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.autofuelx.model.Employee" %>
<%@ page import="java.util.List" %>

<%
    Employee employee = (Employee) request.getAttribute("employee");
    List<String> phoneNumbers = (List<String>) request.getAttribute("employee-phone-numbers");
%>

<html>
<head>
    <title>Update Employee</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<h2>Update Employee</h2>

<form action="<%=request.getContextPath()%>/admin/employee/update" method="post">
    <input type="hidden" name="employeeID" value="<%= employee.getEmployeeID() %>">

    First Name: <input type="text" name="firstName" value="<%= employee.getFirstName() %>"><br>
    Last Name: <input type="text" name="lastName" value="<%= employee.getLastName() %>"><br>
    Date of Birth: <input type="date" name="dateOfBirth" value="<%= employee.getDateOfBirth() %>"><br>
    Salary: <input type="text" name="salary" value="<%= employee.getSalary() %>"><br>
    Status: <input type="text" name="status" value="<%= employee.getStatus() %>"><br>
    Hire Date: <input type="date" name="hireDate" value="<%= employee.getHireDate() %>"><br>
    Address No: <input type="text" name="addressNo" value="<%= employee.getAddressNo() %>"><br>
    Address Lane: <input type="text" name="addressLane" value="<%= employee.getAddressLane() %>"><br>
    Address Area: <input type="text" name="addressArea" value="<%= employee.getAddressArea() %>"><br>
    Email: <input type="text" name="email" value="<%= employee.getEmail() %>"><br>
    Type: <input type="text" name="type" readonly value="<%= employee.getType() %>"><br>

    <% if ("Refuel Cashier".equals(employee.getType())) { %>
    Shift: <input type="text" name="shift" value="<%= employee.getShift() != null ? employee.getShift() : "" %>"><br>
    <% } else if ("Admin".equals(employee.getType())) { %>
    Role: <input type="text" name="role" value="<%= employee.getRole() != null ? employee.getRole() : "" %>"><br>
    <% } else if ("Service Center Staff".equals(employee.getType())) { %>
    Skill Set: <input type="text" name="skillSet" value="<%= employee.getSkillSet() != null ? employee.getSkillSet() : "" %>"><br>
    <% } %>

    <input type="submit" value="Update Employee">
</form>

<hr>

<h3>Manage Phone Numbers</h3>

<ul>
    <% for (String phone : phoneNumbers) { %>
    <li>
        <%= phone %>
        <form action="<%=request.getContextPath()%>/admin/employee/phone-number/delete" method="post" style="display:inline;">
            <input type="hidden" name="employee-ID" value="<%= employee.getEmployeeID() %>">
            <input type="hidden" name="phone-number" value="<%= phone %>">
            <input type="submit" value="Delete">
        </form>
    </li>
    <% } %>
</ul>

<form action="<%=request.getContextPath()%>/admin/employee/phone-number/add" method="post">
    <input type="hidden" name="employee-ID" value="<%= employee.getEmployeeID() %>">
    <input type="text" name="phone-number" placeholder="New Phone Number">
    <input type="submit" value="Add Phone Number">
</form>

<br>
<a href="<%=request.getContextPath()%>/admin/employee/list">Back to Employee List</a>

</body>
</html>
