<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/28/2025
  Time: 8:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.dto.ServiceBookingDTO" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.example.autofuelx.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ServiceBookingDTO> bookings = (List<ServiceBookingDTO>) request.getAttribute("bookings");
    List<Employee> employees = (List<Employee>) request.getAttribute("employees");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
%>
<html>
<head>
    <title>Pending Approval Bookings</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/service-booking/list.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="booking-container">
    <div class="booking-header">
        <h1 class="booking-title">Pending Approval</h1>
    </div>

    <div class="description-section">
        <p>Bookings waiting for admin/staff approval of requested time.</p>
        <div class="back-link">
            <a href="<%= request.getContextPath() %>/admin/dashboard" class="btn btn-secondary">Back to Dashboard</a>
        </div>
    </div>

    <div class="table-section">
        <h2 class="table-title">Booking List</h2>
        <div class="table-container">
            <table class="booking-table">
                <thead>
                <tr>
                    <th>Customer</th>
                    <th>Vehicle</th>
                    <th>Service</th>
                    <th>Date & Time</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <% if (bookings != null && !bookings.isEmpty()) {
                    for (ServiceBookingDTO booking : bookings) {
                        String customerName = booking.getCustomerFirstName() + " " + booking.getCustomerLastName();
                        String vehicleInfo = booking.getVehiclePlate() + " - " + booking.getVehicleModel();
                        String formattedDate = booking.getBookingDate().format(dateFormatter);
                        String formattedTime = booking.getBookingTime().format(timeFormatter);
                %>
                <tr>
                    <td><%= customerName %>
                    </td>
                    <td><%= vehicleInfo %>
                    </td>
                    <td><%= booking.getServiceType() %>
                    </td>
                    <td><%= formattedDate %> - <%= formattedTime %>
                    </td>
                    <td>
                        <div class="action-buttons">
                            <!-- Approve Form -->
                            <form action="<%= request.getContextPath() %>/admin/service-booking/update-status"
                                  method="post" class="action-form">
                                <input type="hidden" name="bookingId" value="<%= booking.getBookingID() %>">
                                <input type="hidden" name="status" value="Confirmed">
                                <div class="form-group">
                                    <label>
                                        <select name="employeeId" class="form-control" required>
                                            <%
                                                for (Employee e : employees) {
                                                    String employeeDetails = e.getFirstName() + " " + e.getLastName() + " - " + e.getSkillSet();
                                            %>
                                            <option value="<%=e.getEmployeeID()%>"><%=employeeDetails%>
                                            </option>
                                            <%}%>
                                        </select>
                                    </label>
                                </div>
                                <input type="hidden" name="redirect-url"
                                       value="/admin/service-booking/view?status=awaiting-confirmation">
                                <button type="submit" class="btn btn-success">Approve</button>
                            </form>

                            <!-- Reschedule Form -->
                            <form action="<%= request.getContextPath() %>/admin/service-booking/update-status"
                                  method="post" class="action-form">
                                <input type="hidden" name="bookingId" value="<%= booking.getBookingID() %>">
                                <input type="hidden" name="status" value="Reschedule Required">
                                <input type="hidden" name="redirect-url"
                                       value="/admin/service-booking/view?status=awaiting-confirmation">
                                <button type="submit" class="btn btn-warning">Request Reschedule</button>
                            </form>
                        </div>
                    </td>
                </tr>
                <% }
                } else { %>
                <tr>
                    <td colspan="5" class="no-results">No pending approval bookings found.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>