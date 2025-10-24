<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 10/24/2025
  Time: 6:54 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="com.example.autofuelx.dto.ServiceBookingDTO" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ServiceBookingDTO booking = (ServiceBookingDTO) request.getAttribute("booking");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    String customerName = booking.getCustomerFirstName() + " " + booking.getCustomerLastName();
    String vehicleInfo = booking.getVehiclePlate() + " - " + booking.getVehicleModel();
    String formattedDate = booking.getBookingDate().format(dateFormatter);
    String formattedTime = booking.getBookingTime().format(timeFormatter);
%>
<html>
<head>
    <title>Process Payment</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/payment/form.css">
</head>
<body>

<jsp:include page="/views/admin/service-booking/header.jsp"/>

<div class="payment-container">
    <div class="payment-header">
        <h1 class="payment-title">Process Payment</h1>
    </div>

    <div class="booking-info-section">
        <h2>Booking Information</h2>
        <div class="info-grid">
            <div class="info-item">
                <span class="info-label">Booking ID:</span>
                <span class="info-value"><%= booking.getBookingID() %></span>
            </div>
            <div class="info-item">
                <span class="info-label">Customer:</span>
                <span class="info-value"><%= customerName %></span>
            </div>
            <div class="info-item">
                <span class="info-label">Vehicle:</span>
                <span class="info-value"><%= vehicleInfo %></span>
            </div>
            <div class="info-item">
                <span class="info-label">Service:</span>
                <span class="info-value"><%= booking.getServiceType() %></span>
            </div>
            <div class="info-item">
                <span class="info-label">Date:</span>
                <span class="info-value"><%= formattedDate %></span>
            </div>
            <div class="info-item">
                <span class="info-label">Time:</span>
                <span class="info-value"><%= formattedTime %></span>
            </div>
        </div>
    </div>

    <div class="payment-form-section">
        <h2>Payment Details</h2>
        <form action="<%= request.getContextPath() %>/admin/service-booking/complete" method="post" id="paymentForm">
            <input type="hidden" name="bookingId" value="<%= booking.getBookingID() %>">

            <div class="form-group">
                <label for="serviceAmount">Service Amount:</label>
                <input type="number" id="serviceAmount" name="serviceAmount" value="<%= booking.getServiceCost() %>" readonly>
            </div>

            <%
                boolean additionAmountStatus = (booking.getServiceCost() < booking.getTotalCost());
                double additionAmount =  booking.getTotalCost() - booking.getServiceCost();

                boolean discountAmountStatus = (booking.getTotalCost() < booking.getServiceCost());
                double discountAmount =  booking.getServiceCost() - booking.getTotalCost() ;
            %>

            <div class="form-group">
                <label for="additionalCharges">Additional Charges (if any):</label>
                <input type="number" id="additionalCharges" name="additionalCharges" value="<%= (additionAmountStatus)? additionAmount : 0.0%>" step="0.01">
            </div>

            <div class="form-group">
                <label for="discount">Discount:</label>
                <input type="number" id="discount" name="discount" value="<%= (discountAmountStatus)? discountAmount : 0.0%>" step="0.01">
            </div>

            <div class="form-group">
                <label for="totalAmount">Total Amount (can be less or more than base cost):</label>
                <input type="number" id="totalAmount" name="totalAmount" value="<%= booking.getTotalCost() %>" readonly>
            </div>

            <div class="form-group">
                <label>Payment Method:</label>
                <div class="payment-methods">
                    <div class="payment-method">
                        <input type="radio" id="cashPayment" name="paymentMethod" value="CASH" checked>
                        <label for="cashPayment">Cash</label>
                    </div>
                    <div class="payment-method">
                        <input type="radio" id="cardPayment" name="paymentMethod" value="CARD">
                        <label for="cardPayment">Card</label>
                    </div>
                </div>
            </div>

            <div class="card-details" id="cardDetails" style="display: none;">
                <div class="form-group">
                    <label for="cardNumber">Card Number:</label>
                    <input type="text" id="cardNumber" name="cardNumber" placeholder="1234 5678 9012 3456" maxlength="19">
                </div>

                <div class="card-row">
                    <div class="form-group">
                        <label for="expiryDate">Expiry Date:</label>
                        <input type="text" id="expiryDate" name="expiryDate" placeholder="MM/YY" maxlength="5">
                    </div>

                    <div class="form-group">
                        <label for="cvv">CVV:</label>
                        <input type="text" id="cvv" name="cvv" placeholder="123" maxlength="3">
                    </div>
                </div>

                <div class="form-group">
                    <label for="cardholderName">Cardholder Name:</label>
                    <input type="text" id="cardholderName" name="cardholderName" placeholder="John Doe">
                </div>
            </div>

            <div class="form-actions">
                <button type="button" class="btn btn-secondary" id="cancelBtn">Cancel</button>
                <button type="submit" class="btn btn-primary">Process Payment</button>
            </div>
        </form>
    </div>
</div>

<script>
    // Calculate total amount
    document.getElementById('additionalCharges').addEventListener('input', calculateTotal);
    document.getElementById('discount').addEventListener('input', calculateTotal);

    function calculateTotal() {
        const serviceAmount = parseFloat(document.getElementById('serviceAmount').value) || 0;
        const additionalCharges = parseFloat(document.getElementById('additionalCharges').value) || 0;
        const discount = parseFloat(document.getElementById('discount').value) || 0;

        const total = serviceAmount + additionalCharges - discount;
        document.getElementById('totalAmount').value = total.toFixed(2);
    }

    // Show/hide card details based on payment method
    document.getElementById('cashPayment').addEventListener('change', function() {
        document.getElementById('cardDetails').style.display = 'none';
    });

    document.getElementById('cardPayment').addEventListener('change', function() {
        document.getElementById('cardDetails').style.display = 'block';
    });

    // Format card number input
    document.getElementById('cardNumber').addEventListener('input', function(e) {
        let value = e.target.value.replace(/\s/g, '');
        let formattedValue = value.replace(/(.{4})/g, '$1 ').trim();
        if (formattedValue.length > 19) {
            formattedValue = formattedValue.substr(0, 19);
        }
        e.target.value = formattedValue;
    });

    // Format expiry date input
    document.getElementById('expiryDate').addEventListener('input', function(e) {
        let value = e.target.value.replace(/\D/g, '');
        if (value.length >= 2) {
            value = value.substring(0, 2) + '/' + value.substring(2, 4);
        }
        e.target.value = value;
    });

    // Cancel button handler
    document.getElementById('cancelBtn').addEventListener('click', function() {
        if (confirm('Are you sure you want to cancel? Any unsaved changes will be lost.')) {
            window.location.href = '<%= request.getContextPath() %>/admin/service-booking/view?status=awaiting-pickup';
        }
    });
</script>

</body>
</html>