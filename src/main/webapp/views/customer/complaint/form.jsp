<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/23/2025
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.autofuelx.model.Complaint" %>
<%@ page import="com.example.autofuelx.model.Customer" %>
<html>
<head>
    <title>Submit Complaint - Auto Fuel X</title>
    <link rel="stylesheet" href="index.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/complaint/form.css">
</head>
<body>

<jsp:include page="/views/customer/header.jsp" />

<div class="submit-complaint-container">
    <div class="complaint-header">
        <h2>Submit a Complaint</h2>
        <p>We value your feedback and are committed to resolving any issues you may have encountered.</p>
    </div>

    <div class="complaint-form-card">
        <div class="complaint-form-header">
            <h3>Complaint Details</h3>
        </div>

        <form action="<%=request.getContextPath()%>/customer/complaint/create" method="post" class="complaint-form-body">
            <%
                Customer customer = (Customer) session.getAttribute("customer");
            %>

            <div class="complaint-info-box">
                <p>Your complaint will be reviewed by our customer service team within 24-48 hours. Please provide as much detail as possible to help us resolve your issue quickly.</p>
            </div>

            <div class="complaint-form-group">
                <label for="title" class="complaint-label">Title:</label>
                <input type="text" id="title" name="title" class="complaint-input" required placeholder="Brief description of your complaint">
                <div class="complaint-help-text">Provide a concise title for your complaint</div>
            </div>

            <div class="complaint-form-group">
                <label for="description" class="complaint-label">Description:</label>
                <textarea id="description" name="description" class="complaint-textarea" required placeholder="Please provide detailed information about your complaint..."></textarea>
                <div class="complaint-help-text">Include all relevant details to help us understand and address your concern</div>
            </div>

            <div class="complaint-form-actions">
                <a href="<%=request.getContextPath()%>/views/customer/dashboard.jsp" class="complaint-cancel-link">Cancel</a>
                <div class="complaint-action-buttons">
                    <button type="submit" class="btn btn-primary">Submit Complaint</button>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>