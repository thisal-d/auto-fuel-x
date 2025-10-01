<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/header.css">
    <title>Admin Dashboard</title>
</head>

<header class="customer-header">
    <div class="header-content">
        <!-- Logo / Title -->
        <div class="header-logo">
            <h1>Admin Dashboard</h1>
        </div>

        <!-- Main Navigation -->
        <nav class="header-nav">
            <!-- Staff Section -->
            <div class="nav-section">
                <div class="nav-section-title">Staff 👨‍🔧</div>
                <div class="nav-links">
                    <a href="<%= request.getContextPath() %>/admin/employee/list">View Staff</a>
                    <a href="<%= request.getContextPath() %>/views/admin/employee/add.jsp">Add New Staff</a>
                </div>
            </div>

            <!-- Services Section -->
            <div class="nav-section">
                <div class="nav-section-title">Services 🔧</div>
                <div class="nav-links">
                    <a href="<%= request.getContextPath() %>/admin/service-booking/dashboard">Service Booking Dashboard</a>
                    <a href="<%= request.getContextPath() %>/admin/service-booking/view?status=awaiting-confirmation">Manage Requests</a>
                </div>
            </div>

            <!-- Fuel Section -->
            <div class="nav-section">
                <div class="nav-section-title">Fuel ⛽</div>
                <div class="nav-links">
                    <a href="<%= request.getContextPath() %>/admin/fuel/levels">View Fuel Levels</a>
                    <a href="<%= request.getContextPath() %>/admin/fuel/add-type">Add New Fuel Type</a>
                </div>
            </div>

            <!-- Customers Section -->
            <div class="nav-section">
                <div class="nav-section-title">Customers 🧑‍💻</div>
                <div class="nav-links">
                    <a href="<%= request.getContextPath() %>/admin/customers/view">View Customers</a>
                </div>
            </div>

            <!-- Fuel Update Section -->
            <div class="nav-section">
                <div class="nav-section-title">Fuel Update 📊</div>
                <div class="nav-links">
                    <a href="<%= request.getContextPath() %>/admin/fuel/update">Update Fuel Levels</a>
                </div>
            </div>

            <!-- Logout Section -->
            <div class="nav-section nav-section-logout">
                <a href="<%= request.getContextPath() %>/logout" class="logout-link">Logout</a>
            </div>
        </nav>
    </div>
</header>
