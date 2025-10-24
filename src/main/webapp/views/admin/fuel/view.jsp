<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.model.Fuel" %>

<%
    List<Fuel> fuels = (List<Fuel>) request.getAttribute("fuel-types");
%>

<html>
<head>
    <title>Fuel Levels & Costs</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/fuel/view.css">
</head>
<body>

<jsp:include page="/views/admin/header.jsp"/>

<div class="page-container">
    <!-- Navigation -->
    <nav class="navbar">
        <div class="navbar-brand">AutoFuelX</div>
        <ul class="navbar-nav">
            <li><a href="#" class="nav-link">Dashboard</a></li>
            <li><a href="#" class="nav-link active">Fuel Management</a></li>
            <li><a href="#" class="nav-link">Suppliers</a></li>
            <li><a href="#" class="nav-link">Vehicles</a></li>
            <li><a href="#" class="nav-link">Service Bookings</a></li>
            <li><a href="#" class="nav-link">Profile</a></li>
            <li><a href="#" class="nav-link">Logout</a></li>
        </ul>
    </nav>

    <div class="page-header">
        <h2>Fuel Management</h2>
    </div>

    <div class="action-bar">
        <a href="<%= request.getContextPath() %>/views/admin/fuel/add.jsp" class="add-button">Add New Fuel Type</a>
    </div>

    <table class="fuel-table">
        <tr>
            <th>ID</th>
            <th>Fuel Type</th>
            <th>Quantity (L)</th>
            <th>Cost per Liter</th>
        </tr>

        <%
            if (fuels != null && !fuels.isEmpty()) {
                for (Fuel f : fuels) {
                    // Determine quantity level class
                    String quantityClass = "quantity-high";
                    if (f.getQuantity() < 1000) {
                        quantityClass = "quantity-low";
                    } else if (f.getQuantity() < 5000) {
                        quantityClass = "quantity-medium";
                    }
        %>
        <tr>
            <td><%= f.getFuelID() %></td>
            <td><%= f.getType() %></td>
            <td><span class="quantity-indicator <%= quantityClass %>"><%= f.getQuantity() %></span></td>
            <td>
                <form action="<%=request.getContextPath()%>/admin/fuel/update" method="post" class="update-form">
                    <input type="hidden" name="fuelID" value="<%= f.getFuelID() %>">
                    <label>
                        <input type="number" name="costPerLiter" value="<%= f.getCostPerLiter() %>">
                    </label>
                    <input type="submit" value="Update" class="update-button">
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="5" class="no-data">No fuel data available.</td>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
</html>