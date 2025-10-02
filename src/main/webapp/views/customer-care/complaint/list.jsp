<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/28/2025
  Time: 8:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.dto.ComplaintReplyDTO" %>
<%
  List<ComplaintReplyDTO> complaints = (List<ComplaintReplyDTO>) request.getAttribute("complaints");
  String filterKeyword = request.getParameter("keyword");
  String lastUpdateDateFilter = request.getParameter("last-update-date");
  String customerEmail = request.getParameter("customer-email");
  String statusFilter = request.getParameter("status");
%>
<html>
<head>
  <title>Complaint List</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer-care/list.css">
</head>
<body>

<jsp:include page="/views/customer-care/header.jsp"/>

<div class="complaint-container">
  <div class="complaint-header">
    <h1 class="complaint-title">Complaint Management</h1>
  </div>

  <div class="filter-section">
    <h2 class="filter-title">Filter Complaints</h2>
    <form method="GET" action="<%= request.getContextPath() %>/customer-care/complaint/list" class="filter-form">
      <div class="filter-group">
        <label class="filter-label">Keyword:</label>
        <input type="text" name="keyword" value="<%= filterKeyword != null ? filterKeyword : "" %>"
               placeholder="Search in title/description" class="form-control">
      </div>

      <div class="filter-group">
        <label class="filter-label">Last Update Date:</label>
        <input type="date" name="last-update-date" value="<%= lastUpdateDateFilter != null ? lastUpdateDateFilter : "" %>"
               class="form-control">
      </div>

      <div class="filter-group">
        <label class="filter-label">Customer Email:</label>
        <input type="email" name="customer-email" value="<%= customerEmail != null ? customerEmail : "" %>"
               placeholder="customer@example.com" class="form-control">
      </div>

      <div class="filter-group">
        <label class="filter-label">Status:</label>
        <select name="status" class="form-control">
          <option value="All" <%= "All".equals(statusFilter) ? "selected" : "" %>>All</option>
          <option value="Open" <%= "Open".equals(statusFilter) ? "selected" : "" %>>Open</option>
          <option value="Closed" <%= "Closed".equals(statusFilter) ? "selected" : "" %>>Closed</option>
        </select>
      </div>

      <div class="filter-actions">
        <button type="submit" class="btn btn-primary">Apply Filters</button>
        <a href="<%= request.getContextPath() %>/customer-care/complaint/list" class="btn btn-secondary">Reset</a>
      </div>
    </form>
  </div>

  <div class="table-section">
    <h2 class="table-title">Complaints</h2>
    <div class="table-container">
      <table class="complaint-table">
        <thead>
        <tr>
          <th>Title</th>
          <th>Description</th>
          <th>Status</th>
          <th>Created Date</th>
          <th>Created Time</th>
          <th>Updated Date</th>
          <th>Updated Time</th>
          <th>Reply Status</th>
          <th>Replied By</th>
          <th>Reply Title</th>
        </tr>
        </thead>
        <tbody>
        <% if (complaints == null || complaints.isEmpty()) { %>
        <tr>
          <td colspan="10" class="no-results">No complaints found matching your criteria.</td>
        </tr>
        <% } else {
          for (ComplaintReplyDTO complaint : complaints) {
            String status = complaint.getStatus();
        %>
        <tr>
          <td>
            <a href="<%= request.getContextPath() %>/customer-care/complaint/view?complaintID=<%= complaint.getComplaintID() %>"
               class="complaint-link">
              <%= complaint.getTitle() %>
            </a>
          </td>
          <td><%= complaint.getDescription() %></td>
          <td>
            <% if ("Open".equals(status)) { %>
            <span class="status status-pending">Open</span>
            <% } else if ("Closed".equals(status)) { %>
            <span class="status status-completed">Closed</span>
            <% } %>
          </td>
          <td><%= complaint.getCreatedDate() %></td>
          <td><%= complaint.getCreatedTime() %></td>
          <td><%= complaint.getUpdatedDate() %></td>
          <td><%= complaint.getUpdateTime() %></td>
          <td>
            <% if ("Replied".equals(complaint.getReplyStatus())) { %>
            <span class="status status-confirmed">Replied</span>
            <% } else { %>
            <span class="status status-awaiting">Pending</span>
            <% } %>
          </td>
          <td><%= complaint.getRepliedEmployeeName() %> (<%= complaint.getRepliedEmployeeType() %>)</td>
          <td><%= complaint.getReplyTitle() %></td>
        </tr>
        <%   }
        } %>
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>
</html>