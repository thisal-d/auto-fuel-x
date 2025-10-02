<%@ page import="com.example.autofuelx.model.Fuel" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 10/2/2025
  Time: 7:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fuel Details</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/fuel/view.css">
</head>
<body>
<div class="container">
    <div class="navbar">
        <div class="navbar-brand">AutoFuelX</div>
        <ul class="navbar-nav">
            <li><a href="#" class="nav-link">Dashboard</a></li>
            <li><a href="#" class="nav-link active">Fuel Details</a></li>
            <li><a href="#" class="nav-link">Service Booking</a></li>
            <li><a href="#" class="nav-link">Reports</a></li>
        </ul>
    </div>

    <div class="page-header">
        <h1>Fuel Details</h1>
        <p>View and manage fuel types, quantities, and pricing</p>
    </div>

    <div class="filter-section">
        <div class="search-box">
            <span class="search-icon">🔍</span>
            <input type="text" placeholder="Search fuel types...">
        </div>
        <div class="filter-group">
            <label>Sort By</label>
            <select>
                <option>Name (A-Z)</option>
                <option>Name (Z-A)</option>
                <option>Price (Low-High)</option>
                <option>Price (High-Low)</option>
                <option>Quantity (High-Low)</option>
            </select>
        </div>
        <div class="filter-group">
            <label>Filter</label>
            <select>
                <option>All Fuel Types</option>
                <option>Gasoline</option>
                <option>Diesel</option>
                <option>Electric</option>
            </select>
        </div>
        <button class="btn btn-primary">Add New Fuel Type</button>
    </div>

    <div class="fuel-container">
        <%
            List<Fuel> fuelTypes = (List<Fuel>) request.getAttribute("fuel-types");
            if (fuelTypes != null && !fuelTypes.isEmpty()) {
                for(Fuel fuel : fuelTypes) {
        %>
        <div class="fuel-card">
            <div class="fuel-icon">
                <img src="<%=request.getContextPath()%>/assets/imgs/fuel.png" alt="icon of a fuel pump with black background and red highlight">
            </div>
            <div class="fuel-info">
                <h3><%= fuel.getType() %></h3>
                <div class="fuel-details">
                    <div class="detail-item">
                        <span class="detail-label">Available Quantity:</span>
                        <span class="detail-value"><%= fuel.getQuantity() %> liters</span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">Cost Per Liter:</span>
                        <span class="detail-value price">$<%= fuel.getCostPerLiter() %></span>
                    </div>
                </div>
            </div>
            <div class="fuel-actions">
                <button class="btn btn-info btn-sm">Edit</button>
                <button class="btn btn-danger btn-sm">Delete</button>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <div class="no-data">
            <h3>No fuel data available</h3>
            <p>Please add fuel types to get started</p>
            <button class="btn btn-primary">Add First Fuel Type</button>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>