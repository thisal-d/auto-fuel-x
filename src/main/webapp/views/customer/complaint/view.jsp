<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/24/2025
  Time: 4:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/24/2025
  Time: 4:45 PM
--%>
<%@ page import="com.example.autofuelx.model.Complaint" %>
<%@ page import="com.example.autofuelx.model.ReplyComplaint" %>
<%@ page import="com.example.autofuelx.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Complaint Details</title>
</head>
<body>

<jsp:include page="/views/customer/header.jsp" />

<h2>Complaint Details</h2>
<%
    Complaint complaint = (Complaint) request.getAttribute("complaint");
    ReplyComplaint replyComplaint = (ReplyComplaint) request.getAttribute("reply-complaint");
    Employee repliedEmployee = (Employee) request.getAttribute("replied-employee");

    if (complaint != null) {
%>
<table border="1">
    <tr>
        <th>Complaint ID</th>
        <td><%= complaint.getComplaintID() %></td>
    </tr>
    <tr>
        <th>Customer ID</th>
        <td><%= complaint.getCustomerID() %></td>
    </tr>
    <tr>
        <th>Title</th>
        <td><%= complaint.getTitle() %></td>
    </tr>
    <tr>
        <th>Description</th>
        <td><%= complaint.getDescription() %></td>
    </tr>
    <tr>
        <th>Status</th>
        <td><%= complaint.getStatus() %></td>
    </tr>
    <tr>
        <th>Created Date</th>
        <td><%= complaint.getCreatedDate() %></td>
    </tr>
    <tr>
        <th>Created Time</th>
        <td><%= complaint.getCreatedTime() %></td>
    </tr>
    <tr>
        <th>Updated Date</th>
        <td><%= complaint.getUpdatedDate() != null ? complaint.getUpdatedDate() : "N/A" %></td>
    </tr>
    <tr>
        <th>Updated Time</th>
        <td><%= complaint.getUpdateTime() != null ? complaint.getUpdateTime() : "N/A" %></td>
    </tr>
</table>
<%
} else {
%>
<p>No complaint details found.</p>
<%
    }

    if (replyComplaint != null) {
%>
<h2>Reply Details</h2>
<table border="1">
    <tr>
        <th>Reply ID</th>
        <td><%= replyComplaint.getReplyComplaintID() %></td>
    </tr>
    <tr>
        <th>Staff ID</th>
        <td><%= replyComplaint.getStaffID() %></td>
    </tr>
    <tr>
        <th>Complaint ID</th>
        <td><%= replyComplaint.getComplaintID() %></td>
    </tr>
    <tr>
        <th>Title</th>
        <td><%= replyComplaint.getTitle() %></td>
    </tr>
    <tr>
        <th>Description</th>
        <td><%= replyComplaint.getDescription() %></td>
    </tr>
    <tr>
        <th>Created Date</th>
        <td><%= replyComplaint.getCreatedDate() %></td>
    </tr>
    <tr>
        <th>Created Time</th>
        <td><%= replyComplaint.getCreatedTime() %></td>
    </tr>
    <tr>
        <th>Updated Date</th>
        <td><%= replyComplaint.getUpdatedDate() != null ? replyComplaint.getUpdatedDate() : "N/A" %></td>
    </tr>
    <tr>
        <th>Updated Time</th>
        <td><%= replyComplaint.getUpdateTime() != null ? replyComplaint.getUpdateTime() : "N/A" %></td>
    </tr>

    <tr>
        <th>Author</th>
        <td><%= repliedEmployee.getFirstName() + " " + repliedEmployee.getLastName()%></td>
    </tr>
</table>
<%
} else {
%>
<p>No reply details available yet.</p>
<%
    }
%>
</body>
</html>
