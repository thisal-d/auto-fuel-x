<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/18/2025
  Time: 8:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="com.example.autofuelx.model.Vehicle"%>
<%
    List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles");
%>

<html>
<head>
    <title>My Vehicles - Auto Fuel X</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/vehicle/list.css">
</head>
<body>

<jsp:include page="/views/customer/header.jsp" />

<div class="vehicles-container">
    <!-- Include customer header -->

    <div class="vehicles-content">
        <div class="page-header">
            <h2 class="page-title">My Vehicles</h2>
            <a href="<%=request.getContextPath()%>/views/customer/vehicle/add.jsp" class="add-vehicle-btn">Add New Vehicle</a>
        </div>

        <% if (vehicles == null || vehicles.isEmpty()) { %>
        <div class="empty-state">
            <div class="empty-state-icon">🚗</div>
            <h3>No Vehicles Found</h3>
            <p>You haven't added any vehicles to your account yet. Add your first vehicle to get started with booking services.</p>
            <a href="<%=request.getContextPath()%>/views/customer/vehicle/add.jsp" class="add-vehicle-btn">Add Your First Vehicle</a>
        </div>
        <% } else { %>
        <table class="vehicles-table">
            <thead>
            <tr>
                <th>Plate Number</th>
                <th>Type</th>
                <th>Model</th>
                <th>Color</th>
                <th>Registration Date</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Vehicle v : vehicles) {
            %>
            <tr>
                <td><strong><%=v.getPlateNumber()%></strong></td>
                <td><span class="vehicle-type"><%=v.getType()%></span></td>
                <td><%=v.getModel()%></td>
                <td><%=v.getColor()%></td>
                <td><%=v.getRegistrationDate()%></td>
                <td>
                    <div class="action-buttons">
                        <form action="<%=request.getContextPath()%>/customer/vehicle/update-form" method="post" class="action-form">
                            <input type="hidden" name="vehicleID" value="<%=v.getVehicleID()%>">
                            <button type="submit" class="action-btn edit-btn">Edit</button>
                        </form>
                        <form action="<%=request.getContextPath()%>/customer/vehicle/delete" method="post" class="action-form">
                            <input type="hidden" name="vehicleID" value="<%=v.getVehicleID()%>">
                            <button type="submit" class="action-btn delete-btn">Remove</button>
                        </form>
                    </div>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <% } %>
    </div>
</div>
</body>
</html>