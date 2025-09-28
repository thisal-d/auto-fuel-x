<%@ page import="com.example.autofuelx.model.Fuel" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/19/2025
  Time: 9:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.autofuelx.model.Fuel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Refuel Form</title></head>
<body>
<h2>Refuel Vehicle</h2>

<%
    List<Fuel> fuelTypes = (List<Fuel>) request.getAttribute("fuel-types");
%>

<form action="<%=request.getContextPath()%>/refuel-cashier/fuel/purchase" method="post">
    <label>Plate Number:</label>
    <input type="text" name="plateNumber" required><br><br>

    <label>Fuel Type (with available stock):</label><br>
    <%
        if (fuelTypes != null) {
            for (Fuel fuel : fuelTypes) {
    %>
    <input type="radio" name="fuelID" value="<%=fuel.getFuelID()%>" required>
    <%=fuel.getType()%>
    <%
        }
    } else {
    %>
    <p>No fuel types available.</p>
    <%
        }
    %>

    <br>
    <label>Quantity (Liters):</label>
    <input type="number" step="0.01" name="quantity" required><br><br>

    <input type="submit" value="Submit">
</form>

<%
    String fuelPurchaseStatus = (String) request.getParameter("purchase-status");
    if ("success".equals(fuelPurchaseStatus)) {
%>
<p style="color:green;">Refuel Successful.</p>
<%
} else if ("failed".equals(fuelPurchaseStatus)) {
%>
<p style="color:red;">Refuel Failed.</p>
<%
    }
%>

</body>
</html>
