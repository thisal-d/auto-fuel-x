<%@ page import="com.example.autofuelx.model.Complaint" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/23/2025
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EDIT Complaint</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customer/complaint/update.css">
</head>
<body>

<jsp:include page="/views/customer/header.jsp"/>

<%
    Complaint complaint = (Complaint) request.getAttribute("complaint");

    // Format date and time
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

    String createdDate = dateFormat.format(complaint.getCreatedDate());
    String createdTime = timeFormat.format(complaint.getCreatedDate());
%>

<div class="complaint-edit-container">
    <div class="form-header">
        <h2>Update Complaint</h2>
    </div>

    <div class="form-container">
        <div class="complaint-info">
            <div class="complaint-info-item">
            </div>

            <div class="complaint-info-item">
                <span class="complaint-info-label">Status</span>
                <span class="complaint-info-value"><%=complaint.getStatus()%></span>
            </div>

            <div class="complaint-datetime">
                <div class="datetime-item">
                    <span class="datetime-icon">📅</span>
                    <span><%=createdDate%></span>
                </div>

                <div class="datetime-item">
                    <span class="datetime-icon">🕒</span>
                    <span><%=createdTime%></span>
                </div>
            </div>
        </div>

        <form action="<%=request.getContextPath()%>/customer/complaint/update" method="post">
            <input type="hidden" name="complaintID" value="<%=complaint.getComplaintID()%>"/>

            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" class="form-control" value="<%=complaint.getTitle()%>"
                       required>
            </div>

            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" class="form-control form-textarea"
                          required><%=complaint.getDescription()%></textarea>
            </div>

            <button type="submit" class="btn-submit">Update Complaint</button>
        </form>
    </div>
</div>

</body>
</html>