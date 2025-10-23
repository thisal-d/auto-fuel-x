<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/18/2025
  Time: 5:35 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.autofuelx.model.Customer" %>
<%@ page import="java.util.List" %>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<String> phoneNumbers = (List<String>) session.getAttribute("phone-numbers");

    // initials for avatar
    String initials = "";
    if (customer.getFirstName() != null && !customer.getFirstName().isEmpty() &&
            customer.getLastName() != null && !customer.getLastName().isEmpty()) {
        initials = customer.getFirstName().charAt(0) + "" + customer.getLastName().charAt(0);
    }
%>
<html>
<head>
    <title>My Profile - Auto Fuel X</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/profile.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="profile-container">

    <div class="profile-content">
        <div class="profile-header">
            <h2>My Profile</h2>
        </div>

        <div class="profile-card">
            <div class="profile-info">
                <div class="profile-avatar">
                    <%= initials %>
                </div>

                <div class="profile-details">
                    <div class="profile-name">
                        <%= customer.getFirstName() %> <%= customer.getLastName() %>
                    </div>

                    <div class="profile-fields">
                        <div class="profile-field">
                            <div class="profile-field-label">Email Address</div>
                            <div class="profile-field-value"><%= customer.getEmail() %></div>
                        </div>
                    </div>

                    <div class="address-section">
                        <div class="address-title">Address</div>
                        <div class="address-value">
                            <%= customer.getAddressNo() %>, <%= customer.getAddressLane() %>, <%= customer.getAddressArea() %>
                        </div>
                    </div>

                    <!-- Phone Numbers Section -->
                    <div class="phone-section">
                        <div class="phone-title">Phone Numbers</div>
                        <ul>
                            <%
                                if (phoneNumbers != null && !phoneNumbers.isEmpty()) {
                                    for (String num : phoneNumbers) {
                            %>
                            <li>
                                <%= num %>
                            </li>
                            <%
                                }
                            } else {
                            %>
                            <li>No phone numbers added.</li>
                            <%
                                }
                            %>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="profile-actions">
                <div class="action-buttons">
                    <form method="get" action="<%=request.getContextPath()%>/views/customer/update.jsp" >
                        <input type="submit" value="Edit Profile" class="btn btn-primary">
                    </form>

                    <form method="post" action="<%=request.getContextPath()%>/customer/customer/delete" onClick="return confirm('Are you sure You Want delete your profile ?')">
                        <input type="submit" value="Delete Profile" class="btn btn-primary">
                    </form>
                </div>

                <a href="<%=request.getContextPath()%>/logout" class="logout-link">Logout</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>