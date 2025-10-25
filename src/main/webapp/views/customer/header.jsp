<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/20/2025
  Time: 1:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Customer Header - Auto Fuel X</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/header.css">
</head>

<header class="customer-header">
    <div class="header-content">
        <div class="header-logo">
            <h1>Auto Fuel X</h1>
        </div>

        <button class="mobile-menu-toggle">☰</button>

        <nav class="header-nav">
            <div class="nav-section">
                <div class="nav-section-title">Account</div>
                <div class="nav-links">
                    <a href="<%=request.getContextPath()%>/customer/dashboard">Dashboard</a>
                    <a href="<%=request.getContextPath()%>/views/customer/profile.jsp">Profile</a>
                </div>
            </div>

            <div class="nav-section">
                <div class="nav-section-title">Vehicle Service</div>
                <div class="nav-links">
                    <a href="<%=request.getContextPath()%>/customer/service-booking/form">Book Service</a>
                    <a href="<%=request.getContextPath()%>/customer/service-booking/list?status=active">Active
                        Bookings</a>
                </div>
            </div>

            <div class="nav-section">
                <div class="nav-section-title">My Vehicles</div>
                <div class="nav-links">
                    <a href="<%=request.getContextPath()%>/customer/vehicle/list">View</a>
                    <a href="<%=request.getContextPath()%>/views/customer/vehicle/add.jsp">Add New</a>
                </div>
            </div>

            <div class="nav-section">
                <div class="nav-section-title">Usage History</div>
                <div class="nav-links">
                    <a href="<%=request.getContextPath()%>/customer/service-booking/history">Service Booking</a>
                    <a href="<%=request.getContextPath()%>/customer/fuel/history">Fuel Usage</a>
                </div>
            </div>

            <div class="nav-section">
                <div class="nav-section-title">Complaints</div>
                <div class="nav-links">
                    <a href="<%=request.getContextPath()%>/views/customer/complaint/form.jsp">Create Complaint</a>
                    <a href="<%=request.getContextPath()%>/customer/complaint/list">My Complaints</a>
                </div>
            </div>

            <!-- <div class="nav-section">
                <div class="nav-section-title">Feedbacks</div>
                <div class="nav-links">
                    <a href="<%=request.getContextPath()%>/views/customer/complaint/form.jsp">Give Feedbacks</a>
                    <a href="<%=request.getContextPath()%>/customer/complaint/list">My Feedbacks</a>
                </div>
            </div> -->

            <div class="nav-section nav-section-logout">
                <div class="nav-section-title">Account</div>
                <div class="nav-links">
                    <a href="<%=request.getContextPath()%>/user/logout"
                       onClick="return confirm('Are you sure You Want to Logout ?')" class="logout-link">Logout</a>
                </div>
            </div>
        </nav>
    </div>
</header>

<script>
    // Simple script to toggle mobile menu
    document.querySelector('.mobile-menu-toggle').addEventListener('click', function () {
        document.querySelector('.header-nav').classList.toggle('active');
    });
</script>