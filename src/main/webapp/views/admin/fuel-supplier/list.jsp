<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 10/14/2025
  Time: 5:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.model.FuelSupplier" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fuel Supplier Management</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/fuel-supplier/list.css">
</head>
<body>

<jsp:include page="/views/admin/header.jsp"/>

<div class="page-container">

    <div class="page-header">
        <h1>Fuel Supplier Management</h1>
    </div>

    <div class="action-bar">
        <a href="<%= request.getContextPath() %>/views/admin/fuel-supplier/add.jsp" class="add-button">Add New Supplier</a>
    </div>

    <table class="supplier-table">
        <thead>
        <tr>
            <th>Supplier ID</th>
            <th>Name</th>
            <th>Phone Number</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            // Retrieve the list of suppliers from the request attribute
            List<FuelSupplier> fuelSuppliers = (List<FuelSupplier>) request.getAttribute("fuelSuppliers");

            // Check if the list exists and is not empty
            if (fuelSuppliers != null && !fuelSuppliers.isEmpty()) {
                // Loop through the list to display each supplier
                for (FuelSupplier supplier : fuelSuppliers) {
        %>
        <tr>
            <td><%= supplier.getSupplierID() %></td>
            <td><%= supplier.getName() %></td>
            <td><%= supplier.getPhoneNumber() %></td>
            <td>
                <div class="action-buttons">
                    <!-- Update Button: Links to an edit page with the supplier's ID -->
                    <a href="<%= request.getContextPath() %>/admin/fuel-supplier/edit-form?fuel-supplier-id=<%= supplier.getSupplierID() %>" class="update-button">Update</a>

                    <!-- Delete Button: Submits a form to a delete servlet -->
                    <form action="<%= request.getContextPath() %>/admin/fuel-supplier/delete" method="post" class="delete-form" onsubmit="return confirm('Are you sure you want to delete this supplier?');">
                        <input type="hidden" name="supplierId" value="<%= supplier.getSupplierID() %>">
                        <input type="submit" value="Delete" class="delete-button">
                    </form>
                </div>
            </td>
        </tr>
        <%
            } // end for loop
        } else {
        %>
        <tr>
            <td colspan="4" class="no-data">No fuel suppliers found.</td>
        </tr>
        <%
            } // end if-else
        %>
        </tbody>
    </table>
</div>
</body>
</html>