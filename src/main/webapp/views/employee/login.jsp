<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/30/2025
  Time: 3:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Employee Login - Auto Fuel X</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/employee/login.css">
</head>

<body>

<div class="login-container">
  <div class="login-left">
    <div class="branding">
      <div class="logo">AFX</div>
      <h1>Auto Fuel X</h1>
      <p>Employee Portal - Premium Vehicle Service & Refueling Experience</p>
    </div>
  </div>

  <div class="login-right">
    <div class="login-card">
      <div class="login-header">
        <h2>Employee Login</h2>
      </div>

      <form action="<%= request.getContextPath() %>/employee/login" method="post" class="login-form">
        <div class="form-group">
          <label for="email">Email:</label>
          <input type="email" id="email" name="email" required placeholder="Enter your email">
        </div>

        <div class="form-group">
          <label for="password">Password:</label>
          <input type="password" id="password" name="password" required placeholder="Enter your password">
        </div>

        <div class="form-group">
          <label for="type">Employee Type:</label>
          <select id="type" name="type" required>
            <option value="">--Select Type--</option>
            <option value="Admin">Admin</option>
            <option value="Service Center Staff">Service Center Staff</option>
            <option value="Customer Care Officer">Customer Care Officer</option>
            <option value="Refuel Cashier">Refuel Cashier</option>
          </select>
        </div>

        <button type="submit" class="login-button">Login</button>
      </form>

      <div class="login-links">
        <a href="<%=request.getContextPath()%>/index.jsp">Back to Home</a>
      </div>

      <%
        String errorMessage = (String) request.getAttribute("error-message");
        if (errorMessage != null) {
      %>
      <div class="error-message">
        <%= errorMessage %>
      </div>
      <% } %>
    </div>
  </div>
</div>
</body>
</html>