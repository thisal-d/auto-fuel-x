<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/24/2025
  Time: 4:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.autofuelx.model.Complaint" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Complaint Details</title>
</head>
<body>

<jsp:include page="/views/customer/header.jsp" />

<%
    Complaint complaint = (Complaint) request.getAttribute("complaint-view");
    if (complaint != null) {
%>
<h2>Complaint Details</h2>
<p><strong>ID:</strong> <%= complaint.getComplaintID() %></p>
<p><strong>Title:</strong> <%= complaint.getTitle() %></p>
<p><strong>Description:</strong> <%= complaint.getDescription() %></p>
<p><strong>Status:</strong> <%= complaint.getStatus() %></p>
<p><strong>Created At:</strong> <%= complaint.getCreatedAt() %></p>
<p><strong>Customer ID:</strong> <%= complaint.getCustomerID() %></p>
<p><strong>Staff ID:</strong> <%= complaint.getStaffID() != null ? complaint.getStaffID() : "Not Assigned" %></p>
<p><strong>Reply:</strong> <%= complaint.getReplyText() != null ? complaint.getReplyText() : "No reply yet" %></p>
<p><strong>Replied At:</strong> <%= complaint.getRepliedDateTime() != null ? complaint.getRepliedDateTime() : "Pending" %></p>
<%
} else {
%>
<p>No complaint details found.</p>
<%
    }
%>
</body>
</html>
