<%@ page import="com.example.autofuelx.model.Fuel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Refuel Form</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/refuel-cashier/fuel/refuel-form.css">
</head>
<body>

<jsp:include page="/views/refuel-cashier/header.jsp"/>

<div class="container refuel-container">
    <%
        String fuelPurchaseStatus = request.getParameter("purchase-status");
        if ("success".equals(fuelPurchaseStatus) || "failed".equals(fuelPurchaseStatus)) {
    %>
    <div class="status-message <%= "success".equals(fuelPurchaseStatus) ? "status-success" : "status-error" %>">
        <%= "success".equals(fuelPurchaseStatus) ? "Refuel Successful." : "Refuel Failed." %>
    </div>
    <%
        }
    %>

    <div class="dashboard">
        <div class="refuel-form">
            <h2 class="form-title">
                <div class="form-icon">R</div>
                Refuel Vehicle
            </h2>

            <%
                List<Fuel> fuelTypes = (List<Fuel>) request.getAttribute("fuel-types");
            %>

            <form action="<%=request.getContextPath()%>/refuel-cashier/fuel/purchase" method="post">
                <div class="form-group">
                    <label for="plateNumber">Plate Number:</label>
                    <input type="text" id="plateNumber" name="plateNumber" required>
                </div>

                <div class="form-group">
                    <label>Fuel Type (with available stock):</label>
                    <div class="fuel-options">
                        <%
                            if (fuelTypes != null) {
                                for (Fuel fuel : fuelTypes) {
                        %>
                        <div class="fuel-option">
                            <input type="radio" id="fuel<%=fuel.getFuelID()%>" name="fuelID"
                                   value="<%=fuel.getFuelID()%>" required>
                            <div class="fuel-info">
                                <div class="fuel-name"><%=fuel.getType()%>
                                </div>
                                <div class="fuel-stock"><%=fuel.getQuantity()%> Liters available</div>
                            </div>
                        </div>
                        <%
                            }
                        } else {
                        %>
                        <p>No fuel types available.</p>
                        <%
                            }
                        %>
                    </div>
                </div>

                <div class="form-group">
                    <label for="quantity">Quantity (Liters):</label>
                    <input type="number" id="quantity" step="0.01" name="quantity" required>
                </div>

                <button type="submit" class="btn btn-primary submit-btn">Submit Refuel Request</button>

            </form>
        </div>

        <div class="fuel-details">
            <h3 class="details-title">
                <div class="details-icon">F</div>
                Fuel Details
            </h3>
            <div class="fuel-list">
                <%
                    if (fuelTypes != null) {
                        for (Fuel fuel : fuelTypes) {
                            // Calculate fuel level percentage (assuming max 10000 liters for visualization)
                            int fuelLevel = (int) (fuel.getQuantity() / 10000.0 * 100);
                            if (fuelLevel > 100) fuelLevel = 100;
                %>
                <div class="fuel-item">
                    <div class="fuel-icon">F</div>
                    <div class="fuel-info">
                        <div class="fuel-name"><%=fuel.getType()%>
                        </div>
                        <div class="fuel-quantity"><%=fuel.getQuantity()%> Liters</div>
                        <div class="fuel-bar">
                            <div class="fuel-level" style="width: <%=fuelLevel%>%"></div>
                        </div>
                    </div>
                </div>
                <%
                    }
                } else {
                %>
                <p>No fuel details available.</p>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</div>
</body>
</html>