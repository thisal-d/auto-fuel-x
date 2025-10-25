<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 10/14/2025
  Time: 5:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.autofuelx.model.FuelSupplier" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Fuel Supplier</title>
</head>


<%
    // Retrieve the fuelSupplier object from the request attribute
    FuelSupplier fuelSupplier = (FuelSupplier) request.getAttribute("fuelSupplier");
%>

<body>

<jsp:include page="/views/admin/header.jsp"/>

<div class="form-container">
    <div class="form-header">
        <h1>Update Fuel Supplier</h1>
    </div>

    <div class="form-card">
        <%-- The form action points to your servlet's URL pattern --%>
        <form action="<%= request.getContextPath() %>/admin/fuel-supplier/update" method="post">
            <div class="form-group">
                <label for="name">Supplier Name:</label>
                <%-- The 'name' attribute must match the parameter name in your servlet --%>
                <input type="text" id="name" name="name" required placeholder="Enter supplier name"
                       value="<%= fuelSupplier.getName() %>">
            </div>

            <div class="form-group">
                <label for="phone-no">Phone Number:</label>
                <%-- The 'name' attribute must match the parameter name in your servlet --%>
                <input type="text" id="phone-no" name="phone-no" required placeholder="Enter phone number"
                       value="<%= fuelSupplier.getPhoneNumber() %>">
            </div>

            <input type="hidden" name="fuel-supplier-id" value="<%= fuelSupplier.getSupplierID() %>">

            <div class="form-actions">
                <button type="submit" class="form-button">Update Supplier Details</button>
                <a href="<%= request.getContextPath() %>/admin/fuel-supplier/list" class="cancel-link">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>