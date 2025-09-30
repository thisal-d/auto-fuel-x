<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/19/2025
  Time: 8:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.autofuelx.model.Customer" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Customer - Auto Fuel X</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/base.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/customer/update.css">
</head>
<body>

<jsp:include page="/views/customer/header.jsp" />

<%
    Customer customer = (Customer) session.getAttribute("customer");
    // If customer not logged in return login.jsp
    if(customer == null){
        response.sendRedirect(request.getContextPath()+ "/views/customer/login.jsp");
        return;
    }
    List<String> phoneNumbers = (List<String>) session.getAttribute("phone-numbers");
%>

<div class="edit-customer-container">
    <div class="edit-header">
        <h1>Edit Customer Information</h1>
    </div>

    <div class="edit-form-card">
        <div class="edit-form-header">
            <h3>Personal Details</h3>
        </div>

        <form action="<%= request.getContextPath() %>/customer/customer/update" method="post" class="edit-form-body">
            <input type="hidden" name="customerID" value="<%= customer.getCustomerID() %>">

            <table class="edit-table">
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstName" value="<%= customer.getFirstName() %>" class="edit-input" required></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lastName" value="<%= customer.getLastName() %>" class="edit-input" required></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="email" name="email" value="<%= customer.getEmail() %>" class="edit-input" required></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" value="<%= customer.getPassword() %>" class="edit-input" required></td>
                </tr>
                <tr>
                    <td>Address No</td>
                    <td><input type="text" name="addressNo" value="<%= customer.getAddressNo() %>" class="edit-input"></td>
                </tr>
                <tr>
                    <td>Address Lane</td>
                    <td><input type="text" name="addressLane" value="<%= customer.getAddressLane() %>" class="edit-input"></td>
                </tr>
                <tr>
                    <td>Address Area</td>
                    <td><input type="text" name="addressArea" value="<%= customer.getAddressArea() %>" class="edit-input"></td>
                </tr>
            </table>

            <div class="edit-form-actions">
                <a href="<%= request.getContextPath() %>/views/customer/profile.jsp" class="edit-cancel-link">Cancel</a>
                <div class="edit-action-buttons">
                    <button type="submit" class="btn btn-primary">Update Customer</button>
                </div>
            </div>
        </form>

        <div class="phone-numbers-section">
            <div class="phone-numbers-title">Phone Numbers</div>

            <%for (String phoneNumber: phoneNumbers) {%>
            <form action="<%=request.getContextPath()%>/customer/phone-number/delete" method="post" class="phone-form">
                <input type="hidden" name="customerID" value="<%= customer.getCustomerID() %>">
                <input type="text" name="phoneNumber" value="<%=phoneNumber%>" class="phone-input" readonly>
                <button type="submit" class="btn btn-danger">Delete</button>
            </form>
            <%}%>

            <!-- Form to add a new number -->
            <form action="<%=request.getContextPath()%>/customer/phone-number/add" method="post" class="add-phone-form">
                <input type="hidden" name="customerID" value="<%= customer.getCustomerID() %>">
                <input type="text" name="phoneNumber" placeholder="Enter new phone number" class="phone-input" required>
                <button type="submit" class="btn btn-primary">Add</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>