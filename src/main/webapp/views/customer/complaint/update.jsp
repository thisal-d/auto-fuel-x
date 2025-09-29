<%@ page import="com.example.autofuelx.model.Complaint" %><%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/23/2025
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Submit Complaint</title></head>
<body>

<jsp:include page="/views/customer/header.jsp" />

<%
    Complaint complaint = (Complaint) request.getAttribute("complaint");
%>


<h2>Submit a Complaint</h2>
<form action="<%=request.getContextPath()%>/customer/complaint/update" method="post">

    <input type="hidden" name="complaintID" value="<%=complaint.getComplaintID()%>" />
    <label>Title:</label><br>
    <input type="text" name="title" value="<%=complaint.getTitle()%>" required><br><br>

    <label>Description:</label><br>
    <textarea name="description" rows="5" cols="40"  value="<%=complaint.getDescription()%>" required></textarea><br><br>

    <input type="submit" value="Update Complaint">
</form>
</body>
</html>
