<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.model.Complaint" %>
<%@ page import="com.example.autofuelx.model.Complaint" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Complaint List</title>
</head>
<body>

<jsp:include page="/views/customer/header.jsp" />

<h1>Customer Complaint</h1>

<%
    List<Complaint> complaints = (List<Complaint>) request.getAttribute("complaints");
    if (complaints != null && !complaints.isEmpty()) {
        for (Complaint complaint : complaints) {
%>

<a href="<%=request.getContextPath()%>/customer/complaint/view?id=<%=complaint.getComplaintID()%>">
    <div>
        <h3>Rating: <%= complaint.getTitle() %> / 5</h3>
        <p><strong>Message:</strong> <%= complaint.getDescription() %></p>
        <p><em>Created At:</em> <%= complaint.getCreatedAt() %></p>
        <p><em>Customer ID:</em> <%= complaint.getStatus() %></p>
    </div>
</a>
<%
    }
} else {
%>
<p>No feedback available.</p>
<%
    }
%>
</body>
</html>
