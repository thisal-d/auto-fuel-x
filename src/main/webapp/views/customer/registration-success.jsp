<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/20/2025
  Time: 12:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Successful - Auto Fuel X</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/registration-success.css">
</head>
<body>
<div class="success-container">
    <div class="success-card">
        <div class="success-icon">✓</div>

        <div class="success-message">
            <h2>Registration Complete!</h2>
            <p>Congratulations! Your account has been successfully created.</p>
        </div>

        <div class="success-details">
            <h3>What's Next?</h3>
            <ul>
                <li>Log in with your new credentials</li>
                <li>Complete your profile information</li>
                <li>Book your first vehicle service</li>
                <li>Explore our fuel station locations</li>
            </ul>
        </div>

        <div class="success-actions">
            <a href="<%=request.getContextPath()%>/views/customer/login.jsp" class="btn-primary">Go to Login</a>
            <a href="<%=request.getContextPath()%>/index.jsp" class="btn-secondary">Back to Home</a>
        </div>
    </div>
</div>
</body>
</html>