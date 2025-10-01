<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/30/2025
  Time: 7:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.autofuelx.dto.ComplaintReplyDTO" %>
<%
    ComplaintReplyDTO complaintDTO = (ComplaintReplyDTO) request.getAttribute("complaintReplyDTO");
%>
<html>
<head>
    <title>View Complaint</title>
</head>
<body>

<!-- Customer Complaint Details -->
<h2>Customer Complaint</h2>
<p><strong>Title:</strong> <%= complaintDTO.getTitle() %></p>
<p><strong>Description:</strong> <%= complaintDTO.getDescription() %></p>
<p><strong>Status:</strong> <%= complaintDTO.getStatus() %></p>
<p><strong>Created Date:</strong> <%= complaintDTO.getCreatedDate() %> <%= complaintDTO.getCreatedTime() %></p>
<p><strong>Updated Date:</strong> <%= complaintDTO.getUpdatedDate() %> <%= complaintDTO.getUpdateTime() %></p>

<!-- Reply Form -->
<h2>Reply to Complaint</h2>
<%
    boolean hasReply = complaintDTO.getReplyComplaintID() != null;
%>

<form action="<%=request.getContextPath()%>/customer-care/complaint/<%=hasReply ? "update" : "create"%>" method="post">
    <input type="hidden" name="complaintID" value="<%= complaintDTO.getComplaintID() %>"/>
    <input type="hidden" name="replyComplaintID" value="<%= complaintDTO.getReplyComplaintID() %>"/>

    <label for="replyTitle">Title:</label>
    <input type="text" id="replyTitle" name="replyTitle"
           value="<%= hasReply ? complaintDTO.getReplyTitle() : "" %>" required/>
    <br/><br/>

    <label for="replyDescription">Description:</label>
    <textarea id="replyDescription" name="replyDescription" rows="5" required><%= hasReply ? complaintDTO.getReplyDescription() : "" %></textarea>
    <br/><br/>

    <br/><br/>

    <button type="submit"><%= hasReply ? "Update Reply" : "Submit Reply" %></button>
</form>

</body>
</html>
