<%@ page import="com.example.autofuelx.model.Vehicle" %>
<%@ page import="com.example.autofuelx.model.Service" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Nadeeth
  Date: 9/28/2025
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Service Booking Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customer/service-booking/form.css">
</head>
<body>
<jsp:include page="/views/customer/header.jsp"/>

<div class="booking-container">
    <div class="booking-form-section">
        <div class="form-header">
            <h1>Book a Service</h1>
            <p>Schedule your vehicle maintenance with our expert team</p>
        </div>

        <%
            List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles");
            List<Service> services = (List<Service>) request.getAttribute("services");
        %>

        <form action="<%= request.getContextPath() %>/customer/service-booking/create" method="post">
            <div class="form-field">
                <label for="vehicle">Select Vehicle</label>
                <select id="vehicle" name="vehicle-id" class="form-input" required>
                    <option value="">-- Select Vehicle --</option>
                    <% for (Vehicle vehicle : vehicles) { %>
                    <option value="<%= vehicle.getVehicleID() %>">
                        <%= vehicle.getPlateNumber() %> - <%= vehicle.getModel() %> (<%= vehicle.getType() %>)
                    </option>
                    <% } %>
                </select>
            </div>

            <div class="form-field">
                <label for="service">Select Service</label>
                <select id="service" name="service-id" class="form-input" required>
                    <option value="">-- Select Service --</option>
                    <% for (Service service : services) { %>
                    <option value="<%= service.getServiceID() %>">
                        <%= service.getType() %> - $<%= service.getCost() %>
                    </option>
                    <% } %>
                </select>
            </div>

            <div class="form-field">
                <label for="booking-date">Date</label>
                <input type="date" id="booking-date" name="booking-date" class="form-input" required>
            </div>

            <div class="form-field">
                <label for="booking-time">Time</label>
                <input type="time" id="booking-time" name="booking-time" class="form-input" required>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn-submit-form">Book Service</button>
            </div>
        </form>
    </div>

    <div class="booking-info-section">
        <div class="info-content">
            <h2>Why Choose Our Service?</h2>

            <div class="service-feature">
                <div class="feature-icon">
                    <img src="<%=request.getContextPath()%>/assets/imgs/service-medal.png" alt="expert icon" width="20" height="20">
                </div>
                <div class="feature-text">
                    <h3>Expert Technicians</h3>
                    <p>Our certified mechanics have years of experience with all vehicle types.</p>
                </div>
            </div>

            <div class="service-feature">
                <div class="feature-icon">
                    <img src="<%=request.getContextPath()%>/assets/imgs/service-time.png" alt="time icon" width="20" height="20">
                </div>
                <div class="feature-text">
                    <h3>Quick Service</h3>
                    <p>We value your time and strive to complete all services efficiently.</p>
                </div>
            </div>

            <div class="service-feature">
                <div class="feature-icon">
                    <img src="<%=request.getContextPath()%>/assets/imgs/service-quality.png" alt="quality icon" width="20" height="20">
                </div>
                <div class="feature-text">
                    <h3>Quality Parts</h3>
                    <p>We use only high-quality parts and materials for all repairs.</p>
                </div>
            </div>

            <div class="service-feature">
                <div class="feature-icon">
                    <img src="<%=request.getContextPath()%>/assets/imgs/service-shield.png" alt="warranty icon" width="20" height="20">
                </div>
                <div class="feature-text">
                    <h3>Service Warranty</h3>
                    <p>All our services come with a comprehensive warranty for your peace of mind.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    const now = new Date();

    // --- Min date = tomorrow ---
    const tomorrow = new Date(now);
    tomorrow.setDate(now.getDate() + 1);
    const minDate = tomorrow.toISOString().split("T")[0];

    const dateInput = document.getElementById("booking-date");
    dateInput.setAttribute("min", minDate);

    // --- Max date = 1 year from today ---
    const oneYearLater = new Date(now);
    oneYearLater.setDate(now.getDate() + 365);
    const maxDate = oneYearLater.toISOString().split("T")[0];

    dateInput.setAttribute("max", maxDate);
</script>


</body>
</html>