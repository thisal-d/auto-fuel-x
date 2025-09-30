<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/18/2025
  Time: 4:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Registration - Auto Fuel X</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/register.css">
</head>
<body>
<div class="register-container">
    <div class="register-left">
        <div class="branding">
            <div class="logo">AFX</div>
            <h1>Auto Fuel X</h1>
            <p>Join our premium vehicle service and refueling platform today</p>
        </div>
    </div>

    <div class="register-right">
        <div class="register-card">
            <div class="register-header">
                <h2>Customer Registration</h2>
            </div>

            <form action="<%=request.getContextPath()%>/customer/register" method="post" class="register-form">
                <div class="form-row">
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" required placeholder="Enter your email">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="firstName">First Name:</label>
                        <input type="text" id="firstName" name="firstName" required placeholder="First name">
                    </div>

                    <div class="form-group">
                        <label for="lastName">Last Name:</label>
                        <input type="text" id="lastName" name="lastName" required placeholder="Last name">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" required placeholder="Create a password">
                    </div>
                </div>

                <div class="address-section">
                    <div class="address-title">Address Information</div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="addressNo">Address No:</label>
                            <input type="text" id="addressNo" name="addressNo" required placeholder="House/Building number">
                        </div>

                        <div class="form-group">
                            <label for="addressLane">Address Lane:</label>
                            <input type="text" id="addressLane" name="addressLane" required placeholder="Street/Lane name">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="addressArea">Address Area:</label>
                            <input type="text" id="addressArea" name="addressArea" required placeholder="Area/City">
                        </div>
                    </div>
                </div>

                <button type="submit" class="register-button">Register</button>
            </form>

            <%
                String error = (String) request.getAttribute("register-error-message");
                if (error != null) {
            %>
            <div class="error-message">
                <%= error %>
            </div>
            <% } %>

            <div class="login-link">
                Already have an account? <a href="<%=request.getContextPath()%>/views/customer/login.jsp">Login Here</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>