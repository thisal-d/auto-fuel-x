<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/18/2025
  Time: 8:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.autofuelx.model.Vehicle" %>
<%@ page import="com.example.autofuelx.service.VehicleService" %>
<%@ page import="com.example.autofuelx.model.Customer" %>
<%
    // Check session
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/views/user/login.jsp");
        return;
    }

    Vehicle vehicle = (Vehicle) request.getAttribute("vehicle-update");

    // Check if vehicle belongs to this customer
    if (vehicle == null || vehicle.getCustomerID() != customer.getCustomerID()) {
        response.sendRedirect(request.getContextPath() + "/views/user/vehicles/list-all.jsp");
        return;
    }
%>

<html>
<head>
    <title>Edit Vehicle - Auto Fuel X</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/vehicles/update.css">
</head>
<body>

<jsp:include page="/views/customer/header.jsp" />

<div class="edit-vehicle-container">
    <!-- Include customer header -->

    <div class="edit-vehicle-content">
        <div class="page-header">
            <h2 class="page-title">Edit Vehicle</h2>
        </div>

        <div class="form-card">
            <div class="form-header">
                <h3>Vehicle Information</h3>
            </div>

            <form action="<%=request.getContextPath()%>/customer/vehicle/update" method="post" class="form-body">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="vehicleID" value="<%= vehicle.getVehicleID() %>">

                <div class="form-grid">
                    <div class="form-group">
                        <label for="plateNumber" class="form-label">Plate Number:</label>
                        <input type="text" id="plateNumber" name="plateNumber" class="form-input" value="<%= vehicle.getPlateNumber() %>" required>
                    </div>

                    <div class="form-group">
                        <label for="type" class="form-label">Type:</label>
                        <input type="text" id="type" name="type" class="form-input" value="<%= vehicle.getType() %>" required>
                    </div>

                    <div class="form-group">
                        <label for="model" class="form-label">Model:</label>
                        <input type="text" id="model" name="model" class="form-input" value="<%= vehicle.getModel() %>" required>
                    </div>

                    <div class="form-group">
                        <label for="color" class="form-label">Color:</label>
                        <input type="text" id="color" name="color" class="form-input" value="<%= vehicle.getColor() %>" required>
                    </div>

                    <div class="form-group">
                        <label for="registrationDate" class="form-label">Registration Date:</label>
                        <input type="date" id="registrationDate" name="registrationDate" class="form-input" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(vehicle.getRegistrationDate()) %>" required>
                    </div>
                </div>

                <div class="form-actions">
                    <a href="<%=request.getContextPath()%>/views/customer/vehicle/list.jsp" class="cancel-link">Cancel</a>

                    <div class="action-buttons">
                        <button type="submit" class="btn btn-primary">Update Vehicle</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>