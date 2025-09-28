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
    <title>Fuel Payment History</title>
</head>
<body>
<h1>Fuel Payment History</h1>

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


<form method="get" action="<%= request.getContextPath() %>/customer/fuel/history">
    <label for="duration-date-start-filter">From:</label>
    <input type="date" id="duration-date-start-filter" name="duration-date-start-filter" value="<%=durationDateStartFilterSelected%>">
    <label for="duration-date-end-filter">To:</label>
    <input type="date" id="duration-date-end-filter" name="duration-date-end-filter" value="<%=durationDateEndFilterSelected%>">

    <label for="vehicle-type-filter">Vehicle Type:</label>
    <select name="vehicle-type-filter" id="vehicle-type-filter">
        <option value="all" <%=("all".equals(vehicleTypeFilterSelected))? "selected" : ""%>>All</option>
        <%for (String vehicleType: vehicleTypes){%>
            <option value="<%= vehicleType %>" <%=(vehicleType.equals(vehicleTypeFilterSelected))? "selected" : ""%> > <%= vehicleType %></option>
        <%}%>
    </select>

    <label for="vehicle-filter">Vehicle :</label>
    <select name="vehicle-filter" id="vehicle-filter">
        <option value="all" <%=("all".equals(vehicleFilterSelectedStr))? "selected" : ""%>>All</option>
        <%for (Vehicle vehicle: vehicles){%>
            <option value="<%= vehicle.getVehicleID()%>" <%=(vehicle.getVehicleID() == vehicleFilterSelected)? "selected" : ""%> ><%= vehicle.getModel() %> - <%= vehicle.getPlateNumber() %></option>
        <%}%>
    </select>

    <label for="fuel-type-filter">Fuel Type :</label>
    <select name="fuel-type-filter" id="fuel-type-filter">
        <option value="all" <%=("all".equals(fuelTypeFilterSelectedStr))? "selected" : ""%>>All</option>
        <%for (Fuel fuel: fuels){%>
        <option value="<%= fuel.getFuelID() %>" <%=(fuel.getFuelID() == fuelTypeFilterSelected)? "selected" : ""%> ><%= fuel.getType() %></option>
        <%}%>
    </select>

    <button type="submit">Apply</button>
</form>


<%
    if (fuelUsageDetails != null && !fuelUsageDetails.isEmpty()) {
%>
<table border="1">
    <tr>
        <th>Transaction ID</th>
        <th>Date & Time</th>
        <th>Vehicle</th>
        <th>Fuel Type</th>
        <th>Quantity (Liters)</th>
        <th>Total Cost ($)</th>
    </tr>
    <%
        for (FuelPurchaseDetailDTO detail : fuelUsageDetails) {
            // Check if purchaseDateTime is null before formatting

    %>
    <tr>
        <td><%= detail.getFuelPurchaseID() %></td>
        <td><%= detail.getPurchaseDate() %></td>
        <td><%= detail.getPurchaseTime() %></td>
        <td><%= detail.getVehiclePlate() != null ? detail.getVehiclePlate() : "" %> - <%= detail.getVehicleModel() != null ? detail.getVehicleModel() : "" %></td>
        <td><%= detail.getFuelType() != null ? detail.getFuelType() : "" %></td>
        <td><%= detail.getQuantity() %> L</td>
        <td>Rs. <%= detail.getTotalCost() %></td>
    </tr>
    <%
        }
    %>
</table>
<%
} else {
%>
<p>No fuel purchase records found.</p>
<%
    }
%>
</body>
</html>