<%@ page import="com.example.autofuelx.model.Fuel" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.model.FuelSupplier" %><%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 10/2/2025
  Time: 7:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Fuel> fuelTypes = (List<Fuel>) request.getAttribute("fuel-types");
  List<FuelSupplier> fuelSuppliers = (List<FuelSupplier>) request.getAttribute("fuel-suppliers");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Add Fuel Quantity</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/fuel/insert.css">
</head>
<body>
<jsp:include page="/views/admin/header.jsp"/>

<div class="fuel-container">
  <div class="fuel-header">
    <h1 class="fuel-title">Add Fuel Quantity</h1>
  </div>

  <form action="<%= request.getContextPath() %>/admin/fuel/insert" method="post" class="fuel-form">

    <div class="form-group">
      <label for="fuelSupplierID" class="form-label">Fuel Supplier:</label>
      <select name="fuelSupplierID" id="fuelSupplierID" class="form-control" required>
        <option value="">Select Fuel Supplier</option>
        <% for(FuelSupplier fuelSupplier : fuelSuppliers) { %>
        <option value="<%= fuelSupplier.getSupplierID() %>"><%= fuelSupplier.getName() %></option>
        <% } %>
      </select>
    </div>

    <div class="form-group">
      <label for="fuelID" class="form-label">Fuel Type:</label>
      <select name="fuelID" id="fuelID" class="form-control" required>
        <option value="">Select Fuel Type</option>
        <% for(Fuel fuel : fuelTypes) { %>
        <option value="<%= fuel.getFuelID() %>"><%= fuel.getType() %></option>
        <% } %>
      </select>
    </div>

    <div class="form-group">
      <label for="quantity" class="form-label">Quantity to Add (Liters):</label>
      <input type="number" step="0.01" name="quantity" id="quantity" class="form-control" required>
    </div>

    <div class="form-actions">
      <button type="submit" class="btn btn-primary">Add Fuel</button>
      <a href="<%= request.getContextPath() %>/admin/dashboard" class="btn btn-secondary">Cancel</a>
    </div>
  </form>

  <div class="fuel-inventory">
    <h2 class="inventory-title">Current Fuel Inventory</h2>
    <div class="fuel-list">
      <% for(Fuel fuel : fuelTypes) { %>
      <div class="fuel-item">
        <div class="fuel-type"><%= fuel.getType() %></div>
        <div class="fuel-quantity"><%= fuel.getQuantity() %> <span class="fuel-unit">liters</span></div>
      </div>
      <% } %>
    </div>
  </div>
</div>
</body>
</html>