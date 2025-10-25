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
    boolean hasReply = complaintDTO.getReplyComplaintID() != null;
%>
<html>
<head>
    <title>View Complaint</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer-care/view.css">
</head>
<body>

<jsp:include page="/views/customer-care/header.jsp"/>

<div class="complaint-container">
    <div class="complaint-header">
        <h1 class="complaint-title">Complaint Details</h1>
    </div>

    <div class="back-link">
        <a href="<%=request.getContextPath()%>/customer-care/complaint/list" class="btn btn-secondary">← Back to
            Complaint List</a>
    </div>

    <!-- Customer Complaint Details -->
    <div class="complaint-details-section">
        <h2 class="section-title">Customer Complaint</h2>
        <div class="complaint-details-card">
            <div class="detail-row">
                <div class="detail-label">Title:</div>
                <div class="detail-value"><%= complaintDTO.getTitle() %>
                </div>
            </div>
            <div class="detail-row">
                <div class="detail-label">Description:</div>
                <div class="detail-value"><%= complaintDTO.getDescription() %>
                </div>
            </div>
            <div class="detail-row">
                <div class="detail-label">Created Date:</div>
                <div class="detail-value"><%= complaintDTO.getCreatedDate() %> <%= complaintDTO.getCreatedTime() %>
                </div>
            </div>
            <div class="detail-row">
                <div class="detail-label">Updated Date:</div>
                <div class="detail-value"><%= complaintDTO.getUpdatedDate() %> <%= complaintDTO.getUpdateTime() %>
                </div>
            </div>
        </div>
    </div>

    <!-- Existing Reply (if any) -->
    <% if (hasReply) { %>
    <div class="reply-section">
        <h2 class="section-title">Existing Reply</h2>
        <div class="reply-card">
            <div class="detail-row">
                <div class="detail-label">Reply Title:</div>
                <div class="detail-value"><%= complaintDTO.getReplyTitle() %>
                </div>
            </div>
            <div class="detail-row">
                <div class="detail-label">Reply Description:</div>
                <div class="detail-value"><%= complaintDTO.getReplyDescription() %>
                </div>
            </div>
            <div class="detail-row">
                <div class="detail-label">Replied By:</div>
                <div class="detail-value"><%= complaintDTO.getRepliedEmployeeName() %>
                    (<%= complaintDTO.getRepliedEmployeeType() %>)
                </div>
            </div>
            <div class="detail-row">
                <div class="detail-label">Reply Status:</div>
                <div class="detail-value">
                    <% if ("Seen".equals(complaintDTO.getReplyStatus())) { %>
                    <span class="status status-confirmed">Seen</span>
                    <% } else { %>
                    <span class="status status-awaiting">Sent</span>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
    <% } %>

    <!-- Reply Form -->
    <div class="reply-form-section">
        <h2 class="section-title"><%= hasReply ? "Update Reply" : "Reply to Complaint" %>
        </h2>
        <div class="reply-form-card">
            <form action="<%=request.getContextPath()%>/customer-care/complaint/<%=hasReply ? "update" : "create"%>"
                  method="post" class="reply-form">
                <input type="hidden" name="complaintID" value="<%= complaintDTO.getComplaintID() %>"/>
                <input type="hidden" name="replyComplaintID" value="<%= complaintDTO.getReplyComplaintID() %>"/>

                <div class="form-group">
                    <label for="replyTitle" class="form-label">Title:</label>
                    <input type="text" id="replyTitle" name="replyTitle"
                           value="<%= hasReply ? complaintDTO.getReplyTitle() : "" %>"
                           class="form-control" required/>
                </div>

                <div class="form-group">
                    <label for="replyDescription" class="form-label">Description:</label>
                    <textarea id="replyDescription" name="replyDescription" rows="5"
                              class="form-control"
                              required><%= hasReply ? complaintDTO.getReplyDescription() : "" %></textarea>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary"><%= hasReply ? "Update Reply" : "Submit Reply" %>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>