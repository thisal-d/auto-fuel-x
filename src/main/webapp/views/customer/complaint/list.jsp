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

<a href="<%=request.getContextPath()%>/customer/complaint/view?complaintID=<%=complaint.getComplaintID()%>">
    <div>
        <h3>Title: <%= complaint.getTitle() %> </h3>
        <p><strong>Description:</strong> <%= complaint.getDescription() %></p>
        <p><em>Created Date:</em> <%= complaint.getCreatedDate() %></p>
        <p><em>Created Time :</em> <%= complaint.getCreatedTime() %></p>
        <p><em>Status :</em> <%= complaint.getStatus() %></p>
    </div>
    <a href="<%=request.getContextPath()%>/customer/complaint/update-form?complaintID=<%=complaint.getComplaintID()%>">Edit Complaint</a>
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
