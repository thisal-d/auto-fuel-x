<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/18/2025
  Time: 5:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Login - Auto Fuel X</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/login.css">
</head>

<body>

<div class="login-container">
    <div class="login-card">
        <div class="login-header">
            <div class="logo">AFX</div>
            <h2>Customer Login</h2>
        </div>

        <form action="<%=request.getContextPath()%>/customer/login" method="post" class="login-form">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required placeholder="Enter your email">
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required placeholder="Enter your password">
            </div>

            <button type="submit" class="login-button">Login</button>
        </form>

        <div class="login-links">
            <a href="<%=request.getContextPath()%>/index.jsp">Back to Home</a>
            <a href="<%=request.getContextPath()%>/views/customer/register.jsp">Create Account</a>
        </div>

        <%
            String error = (String) request.getAttribute("login-error-message");
            if (error != null) {
        %>
        <div class="error-message">
            <%= error %>
        </div>
        <% } %>
    </div>
</div>
</body>
</html>