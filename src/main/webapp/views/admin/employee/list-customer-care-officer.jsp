<%--
  Created by IntelliJ IDEA.
  User: Saachika
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
  <title>Customer Care Officer List</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/employee/list.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="employee-container">
  <div class="employee-header">
    <h1 class="employee-title">Customer Care Officer List</h1>
  </div>

  <div class="filter-section">
    <h2 class="filter-title">Filter Employees</h2>
    <form action="<%=request.getContextPath()%>/admin/employee/list-by-type" method="get" class="filter-form">
      <input type="hidden" name="type" value="customer-care-officer">

      <div class="filter-group">
        <label class="filter-label">Status:</label>
        <select name="status" class="form-control">
          <option value="">All Statuses</option>
          <option value="Active" <% if ("Active".equals(status)) { %>selected<% } %>>Active</option>
          <option value="Inactive" <% if ("Inactive".equals(status)) { %>selected<% } %>>Inactive</option>
        </select>
      </div>

      <div class="filter-group">
        <label class="filter-label">Age Group:</label>
        <select name="ageGroup" class="form-control">
          <option value="">All Ages</option>
          <option value="<30" <% if ("<30".equals(ageGroup)) { %>selected<% } %>>Under 30</option>
          <option value="30-50" <% if ("30-50".equals(ageGroup)) { %>selected<% } %>>30-50</option>
          <option value=">50" <% if (">50".equals(ageGroup)) { %>selected<% } %>>Over 50</option>
        </select>
      </div>

      <div class="filter-group">
        <label class="filter-label">Hire Date From:</label>
        <input type="date" name="hireDateFrom" value="<%= hireDateFrom != null ? hireDateFrom : "" %>" class="form-control">
      </div>

      <div class="filter-group">
        <label class="filter-label">Hire Date To:</label>
        <input type="date" name="hireDateTo" value="<%= hireDateTo != null ? hireDateTo : "" %>" class="form-control">
      </div>

      <div class="filter-group">
        <label class="filter-label">Min Salary:</label>
        <input type="number" name="minSalary" step="0.01" value="<%= minSalary != null ? minSalary : "" %>" class="form-control">
      </div>

      <div class="filter-group">
        <label class="filter-label">Max Salary:</label>
        <input type="number" name="maxSalary" step="0.01" value="<%= maxSalary != null ? maxSalary : "" %>" class="form-control">
      </div>

      <div class="filter-group">
        <label class="filter-label">Name:</label>
        <input type="text" name="name" placeholder="Search by name" value="<%= name != null ? name : "" %>" class="form-control">
      </div>

      <div class="filter-actions">
        <button type="submit" class="btn btn-primary">Apply Filters</button>
      </div>
    </form>
  </div>

  <div class="table-section">
    <h2 class="table-title">Employee List</h2>
    <div class="table-container">
      <table class="employee-table">
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
          <td>
            <% if ("Active".equals(emp.getStatus())) { %>
            <span class="status-active">Active</span>
            <% } else { %>
            <span class="status-inactive">Inactive</span>
            <% } %>
          </td>
          <td><%= calculateAge(emp.getDateOfBirth()) %></td>
          <td><%= emp.getSalary() %></td>
          <td><%= dateFormat.format(emp.getHireDate()) %></td>
          <td><%= emp.getFullyAddress()%></td>
          <td>
            <div class="action-buttons">
              <form action="<%=request.getContextPath()%>/admin/employee/status/update" method="post" class="action-form">
                <input type="hidden" name="redirect-url" value="/admin/employee/list-by-type?type=customer-care-officer">
                <input type="hidden" name="employee-ID" value="<%= emp.getEmployeeID() %>">
                <input type="hidden" name="status" value="<%=emp.getStatus().equals("Active")? "Inactive": "Active"%>">
                <button type="submit" class="btn <% if ("Active".equals(emp.getStatus())) { %>btn-warning<% } else { %>btn-success<% } %>">
                  <%=emp.getStatus().equals("Active")? "Set Inactive": "Set Active"%>
                </button>
              </form>

              <form action="<%=request.getContextPath()%>/admin/employee/edit-form" method="get" class="action-form">
                <input type="hidden" name="redirect-url" value="/admin/employee/list-by-type?type=customer-care-officer">
                <input type="hidden" name="employee-ID" value="<%= emp.getEmployeeID() %>">
                <button type="submit" class="btn btn-info">Edit</button>
              </form>

              <form action="<%=request.getContextPath()%>/admin/employee/delete" method="post" class="action-form">
                <input type="hidden" name="redirect-url" value="/admin/employee/list-by-type?type=customer-care-officer">
                <input type="hidden" name="employee-ID" value="<%= emp.getEmployeeID() %>">
                <button type="submit" class="btn btn-danger">Delete</button>
              </form>
            </div>
          </td>
        </tr>
        <%   }
        } else { %>
        <tr>
          <td colspan="9" class="no-results">No employees found with the selected filters.</td>
        </tr>
        <% } %>
        </tbody>
      </table>
    </div>
  </div>
</div>

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