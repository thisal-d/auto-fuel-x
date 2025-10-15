<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.dto.FuelUsageByTypeDTO" %>
<%@ page import="com.example.autofuelx.dto.VehicleSummaryDTO" %>
<%@ page import="com.example.autofuelx.dto.ComplaintSummaryDTO" %>
<%@ page import="com.example.autofuelx.dto.ServiceBookingSummaryDTO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/dashboard.css">
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="dashboard-header">
        <h1>Customer Dashboard</h1>
        <h2>Welcome to Your Dashboard</h2>
    </div>

    <%
        // Get the customer from session
        Object customerObj = session.getAttribute("customer");
        if (customerObj == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
    %>

    <!-- Fuel Usage Summary Section -->
    <div class="dashboard-section">
        <h3 class="section-title fuel">Fuel Usage Summary</h3>

        <table class="data-table">
            <tr>
                <th>Fuel Type</th>
                <th>Total Quantity</th>
                <th>Total Cost</th>
                <th>Number of Purchases</th>
                <th>Average Cost Per Purchase</th>
            </tr>
            <%
                List<FuelUsageByTypeDTO> fuelUsageSummary = (List<FuelUsageByTypeDTO>) request.getAttribute("fuelUsageSummary");
                if (fuelUsageSummary != null && !fuelUsageSummary.isEmpty()) {
                    for (FuelUsageByTypeDTO fuel : fuelUsageSummary) {
            %>
            <tr>
                <td><%= fuel.getFuelType() %></td>
                <td><%= fuel.getTotalQuantity() %></td>
                <td>$<%= fuel.getTotalCost() %></td>
                <td><%= fuel.getNumberOfPurchases() %></td>
                <td>$<%= fuel.getAverageCostPerPurchase() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="5" class="no-data">No fuel usage data available</td>
            </tr>
            <%
                }
            %>
        </table>

        <div class="section-actions">
            <form action="<%= request.getContextPath() %>/customer/fuel/history" method="get">
                <button type="submit" class="action-btn">View Detailed Fuel Usage</button>
            </form>
        </div>
    </div>

    <!-- Vehicle Summary Section -->
    <div class="dashboard-section">
        <h3 class="section-title vehicle">Vehicle Summary</h3>

        <table class="data-table">
            <tr>
                <th>Vehicle Type</th>
                <th>Count</th>
            </tr>
            <%
                List<VehicleSummaryDTO> vehicleSummary = (List<VehicleSummaryDTO>) request.getAttribute("vehicleSummary");
                if (vehicleSummary != null && !vehicleSummary.isEmpty()) {
                    for (VehicleSummaryDTO vehicle : vehicleSummary) {
            %>
            <tr>
                <td><%= vehicle.getVehicleType() %></td>
                <td><%= vehicle.getVehicleCount() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="2" class="no-data">No vehicle data available</td>
            </tr>
            <%
                }
            %>
        </table>

        <div class="section-actions">
            <form action="<%= request.getContextPath() %>/customer/vehicle/list" method="get">
                <button type="submit" class="action-btn">Manage Vehicles</button>
            </form>
        </div>
    </div>

    <!-- Service Booking Summary Section -->
    <div class="dashboard-section">
        <h3 class="section-title service">Service Booking Summary</h3>

        <%
            ServiceBookingSummaryDTO serviceBookingSummary = (ServiceBookingSummaryDTO) request.getAttribute("serviceBookingSummary");
            if (serviceBookingSummary != null) {
        %>
        <div class="stats-summary">
            <div class="stat-item">
                <div class="stat-value"><%= serviceBookingSummary.getTotalBookings() %></div>
                <div class="stat-label">Total Bookings</div>
            </div>
            <div class="stat-item">
                <div class="stat-value"><%= serviceBookingSummary.getTotalActiveBookings() %></div>
                <div class="stat-label">Active Bookings</div>
            </div>
            <div class="stat-item">
                <div class="stat-value"><%= serviceBookingSummary.getTotalCompletedBookings() %></div>
                <div class="stat-label">Completed Bookings</div>
            </div>
        </div>

        <div class="sub-section">
            <h4 class="sub-section-title">Booking Status Breakdown</h4>
            <table class="data-table">
                <tr>
                    <th>Status</th>
                    <th>Count</th>
                </tr>
                <tr>
                    <td>Awaiting Confirmation</td>
                    <td><%= serviceBookingSummary.getTotalAwaitingConfirmation() %></td>
                </tr>
                <tr>
                    <td>Confirmed</td>
                    <td><%= serviceBookingSummary.getTotalConfirmed() %></td>
                </tr>
                <tr>
                    <td>In Progress</td>
                    <td><%= serviceBookingSummary.getTotalInProgress() %></td>
                </tr>
                <tr>
                    <td>Missed Appointment</td>
                    <td><%= serviceBookingSummary.getTotalMissedAppointment() %></td>
                </tr>
                <tr>
                    <td>Awaiting Pickup</td>
                    <td><%= serviceBookingSummary.getTotalAwaitingPickup() %></td>
                </tr>
                <tr>
                    <td>Reschedule Required</td>
                    <td><%= serviceBookingSummary.getTotalRescheduleRequired() %></td>
                </tr>
                <tr>
                    <td>Cancelled</td>
                    <td><%= serviceBookingSummary.getTotalCancelled() %></td>
                </tr>
            </table>
        </div>
        <%
        } else {
        %>
        <p class="no-data">No service booking data available</p>
        <%
            }
        %>

        <div class="section-actions">
            <form action="<%= request.getContextPath() %>/customer/service-booking/history" method="get">
                <button type="submit" class="action-btn">Manage Service Bookings</button>
            </form>
        </div>
    </div>

    <!-- Complaint Summary Section -->
    <div class="dashboard-section">
        <h3 class="section-title complaint">Complaint Summary</h3>

        <%
            ComplaintSummaryDTO complaintSummary = (ComplaintSummaryDTO) request.getAttribute("complaintSummary");
            if (complaintSummary != null) {
        %>
        <div class="sub-section">
            <h4 class="sub-section-title">Complaint Statistics</h4>
            <table class="data-table">
                <tr>
                    <th>Metric</th>
                    <th>Count</th>
                </tr>
                <tr>
                    <td>Total Complaints</td>
                    <td><%= complaintSummary.getTotalComplaints() %></td>
                </tr>
                <tr>
                    <td>Active Complaints</td>
                    <td><%= complaintSummary.getActiveComplaints() %></td>
                </tr>
                <tr>
                    <td>Unread Complaints</td>
                    <td><%= complaintSummary.getUnreadComplaints() %></td>
                </tr>
                <tr>
                    <td>Inactive Complaints</td>
                    <td><%= complaintSummary.getInactiveComplaints() %></td>
                </tr>
            </table>
        </div>

        <%
        } else {
        %>
        <p class="no-data">No complaint data available</p>
        <%
            }
        %>

        <div class="section-actions">
            <form action="<%= request.getContextPath() %>/customer/complaint/list" method="get">
                <button type="submit" class="action-btn">Manage Complaints</button>
            </form>
        </div>
    </div>

    <!-- Quick Actions Section -->
    <div class="dashboard-section">
        <h3 class="section-title quick-actions">Quick Actions</h3>

        <div class="quick-actions-grid">
            <div class="quick-action-item">
                <form action="<%= request.getContextPath() %>/customer/fuel/history" method="get" class="quick-action-form">
                    <button type="submit" class="quick-action-btn">Record Fuel Purchase</button>
                </form>
            </div>
            <div class="quick-action-item">
                <form action="<%= request.getContextPath() %>/customer/service-booking/form" method="get" class="quick-action-form">
                    <button type="submit" class="quick-action-btn">Book Service</button>
                </form>
            </div>
            <div class="quick-action-item">
                <form action="<%= request.getContextPath() %>/views/customer/complaint/form.jsp" method="get" class="quick-action-form">
                    <button type="submit" class="quick-action-btn">File Complaint</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>