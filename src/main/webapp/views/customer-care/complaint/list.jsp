<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.dto.ComplaintReplyDTO" %>
<html>
<head>
  <title>Complaint List</title>
</head>
<body>
<h2>Complaint Management</h2>

<!-- Filter Form -->
<form method="GET" action="<%= request.getContextPath() %>/customer-care/complaint/list">
  <div>
    <label>Keyword:
      <input type="text" name="keyword" value="<%= request.getParameter("keyword") != null ? request.getParameter("keyword") : "" %>" placeholder="Search in title/description">
    </label>
  </div>

  <div>
    <label>Last Update Date:
      <input type="date" name="last-update-date" value="<%= request.getParameter("last-update-date") != null ? request.getParameter("last-update-date") : "" %>">
    </label>
  </div>

  <div>
    <label>Customer Email:
      <input type="email" name="customer-email" value="<%= request.getParameter("customer-email") != null ? request.getParameter("customer-email") : "" %>" placeholder="customer@example.com">
    </label>
  </div>

  <div>
    <label>Status:
      <select name="status">
        <option value="All" <%= "All".equals(request.getParameter("status")) ? "selected" : "" %>>All</option>
        <option value="Open" <%= "Open".equals(request.getParameter("status")) ? "selected" : "" %>>Open</option>
        <option value="Closed" <%= "Closed".equals(request.getParameter("status")) ? "selected" : "" %>>Closed</option>
      </select>
    </label>
  </div>

  <div>
    <button type="submit">Apply Filters</button>
    <a href="<%= request.getContextPath() %>/customer-care/complaint/list">Reset</a>
  </div>
</form>

<hr>

<!-- Complaint List -->
<h3>Complaints</h3>

<%
  List<ComplaintReplyDTO> complaints = (List<ComplaintReplyDTO>) request.getAttribute("complaints");
  if (complaints == null || complaints.isEmpty()) {
%>
<p>No complaints found matching your criteria.</p>
<%
} else {
%>
<table border="1">
  <tr>
    <th>ID</th>
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

  <%
    for (ComplaintReplyDTO complaint : complaints) {
  %>
  <tr>
    <td><%= complaint.getComplaintID() %></td>
    <td>
      <a href="<%= request.getContextPath() %>/customer-care/complaint/view?complaintID=<%= complaint.getComplaintID() %>">
        <%= complaint.getTitle() %>
      </a>
    </td>
    <td><%= complaint.getDescription() %></td>
    <td><%= complaint.getStatus() %></td>
    <td><%= complaint.getCreatedDate() %></td>
    <td><%= complaint.getCreatedTime() %></td>
    <td><%= complaint.getUpdatedDate() %></td>
    <td><%= complaint.getUpdateTime() %></td>
    <td><%= complaint.getReplyStatus() %></td>
    <td><%= complaint.getRepliedEmployeeName() %> (<%= complaint.getRepliedEmployeeType() %>)</td>
    <td><%= complaint.getReplyTitle() %></td>
  </tr>
  <%
    }
  %>
</table>
<%
  }
%>
</body>
</html>