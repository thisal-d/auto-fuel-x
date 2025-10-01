<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Auto Fuel X - Vehicle Service and Refuel Center</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/index.css">
</head>
<body>
<div class="home-container">
    <header class="home-header">
        <h1>Auto Fuel X</h1>
        <p class="tagline">Your Premium Vehicle Service and Refuel Center</p>
    </header>

    <nav class="home-nav">
        <a href="views/customer/login.jsp">
            <img class="home-nav-icon" src="./assets/imgs/user.png" alt="user-icon"> Customer Login
        </a>
        <a href="views/customer/register.jsp">
            <img class="home-nav-icon" src="./assets/imgs/user.png" alt="user-icon"> Customer Register
        </a>
        <a href="views/employee/login.jsp">
            <img class="home-nav-icon" src="./assets/imgs/admin.png" alt="admin-icon"> Admin Login
        </a>
    </nav>

    <footer class="home-footer">
        <p>&copy; 2023 Auto Fuel X. All rights reserved.</p>
    </footer>
</div>
</body>
</html>