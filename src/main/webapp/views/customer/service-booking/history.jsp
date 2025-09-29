<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/26/2025
  Time: 8:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.dto.ServiceBookingDTO" %>
<%@ page import="com.example.autofuelx.model.Vehicle" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Service Booking History</title>
</head>
<body>
<jsp:include page="/views/customer/header.jsp"/>
<%
    List<String> vehicleTypes = (List<String>) request.getAttribute("vehicleTypes");
    List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles");
    List<ServiceBookingDTO> ServiceBookings =  (List<ServiceBookingDTO>) request.getAttribute("bookings");
    List<String> statuses = (List<String>) request.getAttribute("statuses");

    String selectedVehicleType = (String) request.getAttribute("vehicle-type");
    String selectedEndDate = (String) request.getAttribute("end-date");
    String selectedStartDate = (String) request.getAttribute("start-date");
    String selectedVehicleStr = (String) request.getAttribute("vehicle");
    int selectedVehicle = 0;
    if (selectedVehicleStr != null) selectedVehicle = Integer.parseInt(selectedVehicleStr);
    String selectedStatus = (String) request.getAttribute("status");
    String selectedMinCost =  (String) request.getAttribute("min-cost");
    String selectedMaxCost = (String) request.getAttribute("max-cost");
    String selectedKeyWord = (String) request.getAttribute("keyword");

%>
<h1>Service Booking History</h1>

<form method="get" action="<%= request.getContextPath() %>/customer/service-booking/history">
    <label for="start-date">From:</label>
    <input type="date" id="start-date" name="start-date" value="<%= selectedStartDate != null ? selectedStartDate : "" %>">

    <label for="end-date">To:</label>
    <input type="date" id="end-date" name="end-date" value="<%= selectedEndDate != null ? selectedEndDate : "" %>">

    <label for="vehicle-type">Vehicle Type:</label>
    <select name="vehicle-type" id="vehicle-type">
        <option value="All">All</option>
        <% for (String type : vehicleTypes) { %>
        <option value="<%= type %>" <%= type.equals(selectedVehicleType) ? "selected" : "" %>><%= type %></option>
        <% } %>
    </select>

    <label for="vehicle">Vehicle:</label>
    <select name="vehicle" id="vehicle">
        <option value="All">All</option>
        <% for (Vehicle vehicle : vehicles) { %>
        <option value="<%= vehicle.getVehicleID() %>" <%= (vehicle.getVehicleID()==selectedVehicle) ? "selected" : "" %>><%= vehicle.getModel() %> - <%= vehicle.getPlateNumber() %></option>
        <% } %>
    </select>

    <label for="status">Status:</label>
    <select name="status" id="status">
        <% for (String status : statuses) { %>
        <option value="<%=status %>" <%= status.equals(selectedStatus) ? "selected" : "" %>><%= status %></option>
        <% } %>
    </select>

    <label for="min-cost">Min Cost:</label>
    <input type="number" id="min-cost" name="min-cost" step="0.01" value="<%= selectedMinCost != null ? selectedMinCost : "" %>">

    <label for="max-cost">Max Cost:</label>
    <input type="number" id="max-cost" name="max-cost" step="0.01" value="<%= selectedMaxCost != null ? selectedMaxCost : "" %>">

    <label for="keyword">Keyword:</label>
    <input type="text" id="keyword" name="keyword" value="<%= selectedKeyWord != null ? selectedKeyWord : "" %>">

    <button type="submit">Apply Filters</button>
</form>

<% if (ServiceBookings != null && !ServiceBookings.isEmpty()) { %>
<table border="1">
    <tr>
        <th>Booking ID</th>
        <th>Date</th>
        <th>Time</th>
        <th>Vehicle</th>
        <th>Service Type</th>
        <th>Description</th>
        <th>Status</th>
        <th>Total Cost</th>
    </tr>
    <% for (ServiceBookingDTO booking : ServiceBookings) { %>
    <tr>
        <td><%= booking.getBookingID() %></td>
        <td><%= booking.getBookingDate() %></td>
        <td><%= booking.getBookingTime() %></td>
        <td><%= booking.getVehiclePlate() %> - <%= booking.getVehicleModel() %></td>
        <td><%= booking.getServiceType() %></td>
        <td><%= booking.getServiceDescription() %></td>
        <td><%= booking.getStatus() %></td>
        <td>Rs. <%= booking.getTotalCost() %></td>
    </tr>
    <% } %>
</table>
<% } else { %>
<p>No booking records found.</p>
<% } %>
</body>
</html>