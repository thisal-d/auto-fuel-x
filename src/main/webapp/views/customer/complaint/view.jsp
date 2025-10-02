<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/24/2025
  Time: 4:45 PM
--%>
<%@ page import="com.example.autofuelx.dto.ComplaintReplyDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Complaint Details</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/complaint/view.css">
</head>
<body>
<%
    ComplaintReplyDTO complaintReplyDTO = (ComplaintReplyDTO) request.getAttribute("complaintReplyDTO");

    if (complaintReplyDTO != null) {
%>
<jsp:include page="/views/customer/header.jsp" />

<div class="container">
    <div class="page-header">
        <h1>Complaint Details</h1>
        <div class="actions">
            <a href="<%=request.getContextPath()%>/customer/complaint/list" class="btn btn-secondary">Back to List</a>
            <a href="<%=request.getContextPath()%>/customer/complaint/update-form?complaintID=<%=complaintReplyDTO != null ? complaintReplyDTO.getComplaintID() : ""%>" class="btn btn-primary">Edit Complaint</a>
        </div>
    </div>

    <div class="details-container">
        <div class="complaint-details card">
            <div class="card-header">
                <h2>Your Complaint</h2>
                <span class="status-<%=(complaintReplyDTO.getStatus().toLowerCase().replace(' ', '-')) %>"><%= complaintReplyDTO.getStatus() %></span>
            </div>
            <div class="card-body">
                <div class="detail-item">
                    <div class="detail-label">Title</div>
                    <div class="detail-value"><%= complaintReplyDTO.getTitle() %></div>
                </div>
                <div class="detail-item">
                    <div class="detail-label">Description</div>
                    <div class="detail-value"><%= complaintReplyDTO.getDescription() %></div>
                </div>
                <div class="detail-row">
                    <div class="detail-item">
                        <div class="detail-label">Created Date</div>
                        <div class="detail-value"><%= complaintReplyDTO.getCreatedDate() %></div>
                    </div>
                    <div class="detail-item">
                        <div class="detail-label">Created Time</div>
                        <div class="detail-value"><%= complaintReplyDTO.getCreatedTime() %></div>
                    </div>
                </div>
                <div class="detail-row">
                    <div class="detail-item">
                        <div class="detail-label">Last Updated Date</div>
                        <div class="detail-value"><%= complaintReplyDTO.getUpdatedDate() != null ? complaintReplyDTO.getUpdatedDate() : "N/A" %></div>
                    </div>
                    <div class="detail-item">
                        <div class="detail-label">Last Updated Time</div>
                        <div class="detail-value"><%= complaintReplyDTO.getUpdateTime() != null ? complaintReplyDTO.getUpdateTime() : "N/A" %></div>
                    </div>
                </div>
                <div class="detail-item">
                    <div class="detail-label">Author</div>
                    <div class="detail-value">
                        <div class="author-info">
                            <div class="author-avatar">
                                <img src="<%=request.getContextPath()%>/assets/imgs/customer.png" alt="user avatar icon">
                            </div>
                            <div>You</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%
            // Safely check replyComplaintID
            Integer replyId = complaintReplyDTO.getReplyComplaintID();
            if (replyId != null && replyId != 0) {
        %>
        <div class="reply-details card">
            <div class="card-header">
                <h2>Reply Information</h2>
                <span class="status-<%= complaintReplyDTO.getReplyStatus().toLowerCase().replace(' ','-') %>"><%= complaintReplyDTO.getReplyStatus() %></span>
            </div>
            <div class="card-body">
                <div class="detail-item">
                    <div class="detail-label">Title</div>
                    <div class="detail-value"><%= complaintReplyDTO.getReplyTitle() != null ? complaintReplyDTO.getReplyTitle() : "N/A" %></div>
                </div>
                <div class="detail-item">
                    <div class="detail-label">Description</div>
                    <div class="detail-value"><%= complaintReplyDTO.getReplyDescription() != null ? complaintReplyDTO.getReplyDescription() : "N/A" %></div>
                </div>
                <div class="detail-row">
                    <div class="detail-item">
                        <div class="detail-label">Reply Date</div>
                        <div class="detail-value"><%= complaintReplyDTO.getReplyUpdatedDate() != null ? complaintReplyDTO.getReplyUpdatedDate() : "N/A" %></div>
                    </div>
                    <div class="detail-item">
                        <div class="detail-label">Reply Time</div>
                        <div class="detail-value"><%= complaintReplyDTO.getReplyUpdateTime() != null ? complaintReplyDTO.getReplyUpdateTime() : "N/A" %></div>
                    </div>
                </div>
                <div class="detail-item">
                    <div class="detail-label">Author</div>
                    <div class="detail-value">
                        <div class="author-info">
                            <div class="author-avatar employee">
                                <img src="<%=request.getContextPath()%>/assets/imgs/employee.png" alt="employee avatar icon">
                            </div>
                            <div><%= complaintReplyDTO.getRepliedEmployeeName() != null ? complaintReplyDTO.getRepliedEmployeeName() : "N/A" %></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%
        } else {
        %>
        <div class="no-reply card">
            <div class="no-reply-content">
                <img src="<%=request.getContextPath()%>/assets/imgs/no-reply.png" alt="no reply icon">
                <h3>No Reply Available</h3>
                <p>Your complaint is being reviewed. We'll respond as soon as possible.</p>
            </div>
        </div>
        <%
            }
        %>
    </div>
    <%
    } else {
    %>
    <div class="empty-state card">
        <div class="empty-state-content">
            <img src="<%=request.getContextPath()%>/assets/imgs/error.png" alt="error icon">
            <h2>No Complaint Found</h2>
            <p>The complaint you're looking for doesn't exist or has been removed.</p>
            <a href="<%=request.getContextPath()%>/customer/complaint/list" class="btn btn-primary">Back to Complaints</a>
        </div>
    </div>
    <%
        }
    %>
</div>

</body>
</html>