<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/18/2025
  Time: 8:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add Vehicle - Auto Fuel X</title>
    <link rel="stylesheet" href="index.css">
    <link rel="stylesheet" href="css/add-vehicle.css">
</head>
<body>

<jsp:include page="/views/customer/header.jsp" />

<div class="add-vehicle-container">
    <!-- Include customer header -->
    <div class="add-vehicle-content">
        <div class="page-header">
            <h2 class="page-title">Add Vehicle</h2>

            <div class="breadcrumb">
                <div class="breadcrumb-item">
                    <a href="<%=request.getContextPath()%>/views/customer/customer-dashboard.jsp">Dashboard</a>
                </div>
                <div class="breadcrumb-item">
                    <a href="<%=request.getContextPath()%>/views/customer/vehicles/list.jsp">My Vehicles</a>
                </div>
                <div class="breadcrumb-item">Add Vehicle</div>
            </div>
        </div>

        <div class="form-card">
            <div class="form-header">
                <h3>Vehicle Information</h3>
            </div>

            <form action="<%=request.getContextPath()%>/customer/vehicle/add" method="post" class="form-body">

                <div class="form-grid">
                    <div class="form-group">
                        <label for="plateNumber" class="form-label">Plate Number:</label>
                        <input type="text" id="plateNumber" name="plateNumber" class="form-input" required placeholder="e.g., ABC-1234">
                        <div class="help-text">Enter your vehicle's license plate number</div>
                    </div>

                    <div class="form-group">
                        <label for="type" class="form-label">Type:</label>
                        <input type="text" id="type" name="type" class="form-input" required placeholder="e.g., Car, Motorcycle, SUV">
                        <div class="help-text">Enter the type of your vehicle</div>
                    </div>

                    <div class="form-group">
                        <label for="model" class="form-label">Model:</label>
                        <input type="text" id="model" name="model" class="form-input" required placeholder="e.g., Toyota Camry">
                        <div class="help-text">Enter the make and model of your vehicle</div>
                    </div>

                    <div class="form-group">
                        <label for="color" class="form-label">Color:</label>
                        <input type="text" id="color" name="color" class="form-input" required placeholder="e.g., Black, Silver, Red">
                        <div class="help-text">Enter the color of your vehicle</div>
                    </div>

                    <div class="form-group">
                        <label for="registrationDate" class="form-label">Registration Date:</label>
                        <input type="date" id="registrationDate" name="registrationDate" class="form-input" required>
                        <div class="help-text">Enter the date your vehicle was registered</div>
                    </div>
                </div>

                <div class="form-actions">
                    <a href="<%=request.getContextPath()%>/views/customer/vehicle/list.jsp" class="cancel-link">Cancel</a>

                    <div class="action-buttons">
                        <button type="submit" class="btn btn-primary">Add Vehicle</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>