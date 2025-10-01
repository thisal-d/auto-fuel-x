<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/19/2025
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.autofuelx.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Employee> employees = (List<Employee>) request.getAttribute("employees");
  String type = (String) request.getAttribute("type");
  String status = (String) request.getAttribute("status");
  String hireDateFrom = (String) request.getAttribute("hireDateFrom");
  String hireDateTo = (String) request.getAttribute("hireDateTo");
  String minSalary = (String) request.getAttribute("minSalary");
  String maxSalary = (String) request.getAttribute("maxSalary");
  String name = (String) request.getAttribute("name");
  String ageGroup = (String) request.getAttribute("ageGroup");

  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
%>
<html>
<head>
  <title>Employee List</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<h1>Admin List</h1>

<h2>Filter Employees</h2>
<form action="<%=request.getContextPath()%>/admin/employee/list-by-type" method="get">
  <table border="1" cellpadding="5">
    <input type="hidden" name="type" value="admin">
    <tr>
      <td>Status:</td>
      <td>
        <select name="status">
          <option value="">All Statuses</option>
          <option value="Active" <% if ("Active".equals(status)) { %>selected<% } %>>Active</option>
          <option value="Inactive" <% if ("Inactive".equals(status)) { %>selected<% } %>>Inactive</option>
        </select>
      </td>
    </tr>
    <tr>
      <td>Age Group:</td>
      <td>
        <select name="ageGroup">
          <option value="">All Ages</option>
          <option value="<30" <% if ("<30".equals(ageGroup)) { %>selected<% } %>>Under 30</option>
          <option value="30-50" <% if ("30-50".equals(ageGroup)) { %>selected<% } %>>30-50</option>
          <option value=">50" <% if (">50".equals(ageGroup)) { %>selected<% } %>>Over 50</option>
        </select>
      </td>
    </tr>
    <tr>
      <td>Hire Date From:</td>
      <td><input type="date" name="hireDateFrom" value="<%= hireDateFrom != null ? hireDateFrom : "" %>"></td>
    </tr>
    <tr>
      <td>Hire Date To:</td>
      <td><input type="date" name="hireDateTo" value="<%= hireDateTo != null ? hireDateTo : "" %>"></td>
    </tr>
    <tr>
      <td>Min Salary:</td>
      <td><input type="number" name="minSalary" step="0.01" value="<%= minSalary != null ? minSalary : "" %>"></td>
    </tr>
    <tr>
      <td>Max Salary:</td>
      <td><input type="number" name="maxSalary" step="0.01" value="<%= maxSalary != null ? maxSalary : "" %>"></td>
    </tr>
    <tr>
      <td>Name:</td>
      <td><input type="text" name="name" placeholder="Search by name" value="<%= name != null ? name : "" %>"></td>
    </tr>
    <tr>
      <td colspan="2"><input type="submit" value="Apply Filters"></td>
    </tr>
  </table>
</form>

<h2>Employee List</h2>
<table border="1" cellpadding="5">
  <thead>
  <tr>
    <th>Name</th>
    <th>Email</th>
    <th>Type</th>
    <th>Status</th>
    <th>Age</th>
    <th>Salary</th>
    <th>Hire Date</th>
    <th>Address</th>
    <th>Role</th>
    <th>Action</th>
  </tr>
  </thead>
  <tbody>
  <% if (employees != null && !employees.isEmpty()) {
    for (Employee emp : employees) { %>
  <tr>
    <td><%= emp.getFirstName() + " " + emp.getLastName() %></td>
    <td><%= emp.getEmail() %></td>
    <td><%= emp.getType() %></td>
    <td><%= emp.getStatus() %></td>
    <td><%= calculateAge(emp.getDateOfBirth()) %></td>
    <td><%= emp.getSalary() %></td>
    <td><%= dateFormat.format(emp.getHireDate()) %></td>
    <td><%= emp.getFullyAddress()%></td>
    <td><%= emp.getRole()%></td>
    <td>
      <form action="<%=request.getContextPath()%>/admin/employee/status/update/" method="post">
        <input type="hidden" name="redirect-url" value="/admin/employee/list-by-type?type=admin">
        <input type="hidden" name="employee-ID" value="<%= emp.getEmployeeID() %>">
        <input type="hidden" name="status" value="<%=emp.getStatus().equals("Active")? "Inactive": "Active"%>">>
        <input type="submit" name="" value="<%=emp.getStatus().equals("Active")? "Set Inactive": "Set Active"%>">
      </form>

      <form action="<%=request.getContextPath()%>/admin/employee/edit-form/" method="get">
        <input type="hidden" name="redirect-url"  value="/admin/employee/list-by-type?type=admin">
        <input type="hidden" name="employee-ID" value="<%= emp.getEmployeeID() %>">
        <input type="submit" value="Edit">
      </form>

      <form action="<%=request.getContextPath()%>/admin/employee/delete/" method="post">
        <input type="hidden" name="redirect-url"  value="/admin/employee/list-by-type?type=admin">
        <input type="hidden" name="employee-ID" value="<%= emp.getEmployeeID() %>">
        <input type="submit" value="Delete">
      </form>
    </td>
  </tr>
  <%   }
  } else { %>
  <tr>
    <td colspan="8">No employees found with the selected filters.</td>
  </tr>
  <% } %>
  </tbody>
</table>

<%!
  private int calculateAge(java.sql.Date dob) {
    if (dob == null) {
      return 0;
    }
    java.util.Date today = new java.util.Date();
    long diffInMillies = Math.abs(today.getTime() - dob.getTime());
    return (int) (diffInMillies / (1000L * 60 * 60 * 24 * 365));
  }
%>
</body>
</html>