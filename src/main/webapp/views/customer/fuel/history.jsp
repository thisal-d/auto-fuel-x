<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/26/2025
  Time: 8:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.dto.FuelPurchaseDetailDTO" %>
<%@ page import="com.example.autofuelx.model.Fuel" %>
<%@ page import="com.example.autofuelx.model.Vehicle" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fuel Payment History - Vehicle Service & Refuel Center</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/fuel/history.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/fuel/fuelPaymentHistory-simple-style.css">
</head>
<body>

<jsp:include page="/views/customer/header.jsp"/>

<%
    List<FuelPurchaseDetailDTO> fuelUsageDetails = (List<FuelPurchaseDetailDTO>) request.getAttribute("fuel-usage-details");
    List<String> vehicleTypes = (List<String>) request.getAttribute("vehicle-types");
    List<Fuel> fuels = (List<Fuel>) request.getAttribute("fuels");
    List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles");

    String durationDateStartFilterSelected = request.getParameter("duration-date-end-filter");
    String durationDateEndFilterSelected = request.getParameter("duration-date-end-filter");
    String vehicleTypeFilterSelected = request.getParameter("vehicle-type-filter");
    String vehicleFilterSelectedStr = request.getParameter("vehicle-filter");
    int vehicleFilterSelected;
    if (!(vehicleFilterSelectedStr == null || "all".equals(vehicleFilterSelectedStr))) vehicleFilterSelected = Integer.parseInt(vehicleFilterSelectedStr);
    else vehicleFilterSelected = 0;
    String fuelTypeFilterSelectedStr = request.getParameter("fuel-type-filter");
    int fuelTypeFilterSelected;
    if (!(fuelTypeFilterSelectedStr == null || "all".equals(fuelTypeFilterSelectedStr))) fuelTypeFilterSelected = Integer.parseInt(fuelTypeFilterSelectedStr);
    else fuelTypeFilterSelected = 0;
%>
<div class="history-container">
    <div class="page-header">
        <h1 class="page-title">Fuel Payment History</h1>
    </div>

    <div class="filter-section">
        <form method="get" action="<%= request.getContextPath() %>/customer/fuel/history" class="filter-form">
            <div class="filter-group">
                <label for="duration-date-start-filter">From Date:</label>
                <input type="date" id="duration-date-start-filter" name="duration-date-start-filter" value="<%= request.getParameter("duration-date-start-filter") != null ? request.getParameter("duration-date-start-filter") : "" %>">
            </div>

            <div class="filter-group">
                <label for="duration-date-end-filter">To Date:</label>
                <input type="date" id="duration-date-end-filter" name="duration-date-end-filter" value="<%= request.getParameter("duration-date-end-filter") != null ? request.getParameter("duration-date-end-filter") : "" %>">
            </div>

            <div class="filter-group">
                <label for="vehicle-type-filter">Vehicle Type:</label>
                <select name="vehicle-type-filter" id="vehicle-type-filter">
                    <option value="all">All</option>
                    <%for (String vehicleType: vehicleTypes){%>
                    <option value="<%= vehicleType %>" <%= vehicleType.equals(request.getParameter("vehicle-type-filter")) ? "selected" : "" %>><%= vehicleType %></option>
                    <%}%>
                </select>
            </div>

            <div class="filter-group">
                <label for="vehicle-filter">Vehicle:</label>
                <select name="vehicle-filter" id="vehicle-filter">
                    <option value="all">All</option>
                    <%for (Vehicle vehicle: vehicles){%>
                    <option value="<%= vehicle.getVehicleID()%>" <%= String.valueOf(vehicle.getVehicleID()).equals(request.getParameter("vehicle-filter")) ? "selected" : "" %>><%= vehicle.getModel() %> - <%= vehicle.getPlateNumber() %></option>
                    <%}%>
                </select>
            </div>

            <div class="filter-group">
                <label for="fuel-type-filter">Fuel Type:</label>
                <select name="fuel-type-filter" id="fuel-type-filter">
                    <option value="all">All</option>
                    <%for (Fuel fuel: fuels){%>
                    <option value="<%= fuel.getFuelID() %>" <%= String.valueOf(fuel.getFuelID()).equals(request.getParameter("fuel-type-filter")) ? "selected" : "" %>><%= fuel.getType() %></option>
                    <%}%>
                </select>
            </div>

            <div class="filter-actions">
                <button type="submit" class="btn-filter">Apply</button>
                <button type="reset" class="btn-clear">Clear</button>
            </div>
        </form>
    </div>

    <div class="results-section">
        <div class="results-header">
            <h2 class="results-title">Fuel Purchase Records</h2>
            <div class="results-count">
                <% if (fuelUsageDetails != null && !fuelUsageDetails.isEmpty()) { %>
                <%= fuelUsageDetails.size() %> records found
                <% } %>
            </div>
        </div>

        <% if (fuelUsageDetails != null && !fuelUsageDetails.isEmpty()) { %>
        <div class="table-container">
            <table class="history-table">
                <thead>
                <tr>
                    <th>Transaction ID</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Vehicle</th>
                    <th>Fuel Type</th>
                    <th>Quantity (L)</th>
                    <th>Total Cost</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (FuelPurchaseDetailDTO detail : fuelUsageDetails) {
                %>
                <tr>
                    <td><%= detail.getFuelPurchaseID() %></td>
                    <td><%= detail.getPurchaseDate() %></td>
                    <td><%= detail.getPurchaseTime() %></td>
                    <td><%= detail.getVehiclePlate() != null ? detail.getVehiclePlate() : "" %> - <%= detail.getVehicleModel() != null ? detail.getVehicleModel() : "" %></td>
                    <td><%= detail.getFuelType() != null ? detail.getFuelType() : "" %></td>
                    <td><%= detail.getQuantity() %></td>
                    <td class="cost-display">Rs. <%= detail.getTotalCost() %></td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
        <% } else { %>
        <div class="no-results">
            <div class="no-results-icon">⛽</div>
            <p>No fuel purchase records found.</p>
        </div>
        <% } %>
    </div>
</div>

<script>
    // Clear button functionality
    document.querySelector('.btn-clear').addEventListener('click', function() {
        // Reset all form fields
        document.getElementById('duration-date-start-filter').value = '';
        document.getElementById('duration-date-end-filter').value = '';
        document.getElementById('vehicle-type-filter').value = 'all';
        document.getElementById('vehicle-filter').value = 'all';
        document.getElementById('fuel-type-filter').value = 'all';

        // Submit the form to reset the filters
        document.querySelector('.filter-form').submit();
    });
</script>
</body>
</html>