<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.dto.ComplaintReplyDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Complaint List</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/complaint/list.css">
</head>
<body>

<jsp:include page="/views/customer/header.jsp" />

<div class="container">
    <div class="page-header">
        <h1>My Complaints</h1>
        <div class="actions">
            <a href="<%=request.getContextPath()%>/customer/complaint/add-form" class="btn btn-primary">New Complaint</a>
        </div>
    </div>

    <%
        List<ComplaintReplyDTO> complaints = (List<ComplaintReplyDTO>) request.getAttribute("complaints");
        if (complaints != null && !complaints.isEmpty()) {
            for (ComplaintReplyDTO complaint : complaints) {
    %>

    <div class="complaint-card">
        <a href="<%=request.getContextPath()%>/customer/complaint/view?complaintID=<%=complaint.getComplaintID()%>" class="complaint-link">
            <div class="complaint-header">
                <h3><%= complaint.getTitle() %></h3>
                <span class="status-<%= complaint.getStatus().toLowerCase().replace(' ','-') %>"><%= complaint.getStatus() %></span>
            </div>
            <div class="complaint-body">
                <p><%= complaint.getDescription() %></p>
            </div>
            <div class="complaint-footer">
                <div class="complaint-meta">
                    <span class="date"><img src="<%=request.getContextPath()%>/assets/imgs/calendar.png" alt="calendar icon"> <%= complaint.getCreatedDate() %></span>
                    <span class="time"><img src="<%=request.getContextPath()%>/assets/imgs/clock.png" alt="clock icon"> <%= complaint.getCreatedTime() %></span>
                </div>
                <div class="complaint-actions">
                    <a href="<%=request.getContextPath()%>/customer/complaint/update-form?complaintID=<%=complaint.getComplaintID()%>" class="btn btn-secondary btn-sm">Edit</a>
                </div>
            </div>
        </a>

        <div class="reply-section">
            <% if (complaint.getReplyComplaintID() == null || complaint.getReplyComplaintID() == 0) {%>
            <div class="no-reply">
                <img src="<%=request.getContextPath()%>/assets/imgs/no-reply.png" alt="no reply icon">
                <p>No reply available</p>
            </div>
            <% } else if (complaint.getReplyStatus().equals("Open")) { %>
            <div class="reply-available">
                <img src="<%=request.getContextPath()%>/assets/imgs/msg-available.png" alt="new message icon">
                <a href="<%=request.getContextPath()%>/customer/complaint/view?complaintID=<%=complaint.getComplaintID()%>">New reply available</a>
            </div>
            <% } else if (complaint.getReplyStatus().equals("Seen")) {%>
            <div class="reply-viewed">
                <img src="<%=request.getContextPath()%>/assets/imgs/check-mark.png" alt="viewed message icon">
                <p>Reply viewed</p>
            </div>
            <%}%>
        </div>
    </div>

    <%
        }
    } else { %>
    <div class="empty-state">
        <img src="<%=request.getContextPath()%>/assets/imgs/no-complaint.png" alt="empty complaint list icon">
        <h2>No complaints available</h2>
        <p>You haven't submitted any complaints yet.</p>
        <a href="<%=request.getContextPath()%>/customer/complaint/add-form" class="btn btn-primary">Submit a Complaint</a>
    </div>
    <%
        }
    %>
</div>

</body>
</html>