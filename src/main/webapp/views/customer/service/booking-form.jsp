<%@ page import="com.example.autofuelx.model.Vehicle" %>
<%@ page import="com.example.autofuelx.model.Service" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/28/2025
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Service Booking Form</title>
</head>
<body>
<h1>Book a Service</h1>

<%
    List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles");
    List<Service> services = (List<Service>) request.getAttribute("services");
%>

<form action="<%= request.getContextPath() %>/customer/service/booking" method="post">
    <div>
        <label for="vehicle">Select Vehicle:</label>
        <select id="vehicle" name="vehicleId" required>
            <option value="">-- Select Vehicle --</option>
            <% for (Vehicle vehicle : vehicles) { %>
            <option value="<%= vehicle.getVehicleID() %>">
                <%= vehicle.getPlateNumber() %> - <%= vehicle.getModel() %> (<%= vehicle.getType() %>)
            </option>
            <% } %>
        </select>
    </div>

    <div>
        <label for="service">Select Service:</label>
        <select id="service" name="serviceId" required>
            <option value="">-- Select Service --</option>
            <% for (Service service : services) { %>
            <option value="<%= service.getServiceID() %>">
                <%= service.getType() %> - $<%= service.getCost() %>
            </option>
            <% } %>
        </select>
    </div>

    <div>
        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required>
    </div>

    <div>
        <label for="time">Time:</label>
        <input type="time" id="time" name="time" required>
    </div>

    <div>
        <button type="submit">Book Service</button>
    </div>
</form>
</body>
</html>