<%--
  Created by IntelliJ IDEA.
  User: saachika
  Date: 9/30/2025
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.autofuelx.model.Employee" %>

<%
    Employee employee = (Employee) session.getAttribute("employee");
    if (employee == null) {
        response.sendRedirect(request.getContextPath() + "/views/employee/login.jsp");
        return;
    }

    List<String> phoneNumbers = (List<String>) session.getAttribute("phone-numbers");
    String empType = employee.getType();   // empType comes from employee object
    String firstInitial = employee.getFirstName() != null && !employee.getFirstName().isEmpty() ?
            employee.getFirstName().substring(0, 1) : "";
    String lastInitial = employee.getLastName() != null && !employee.getLastName().isEmpty() ?
            employee.getLastName().substring(0, 1) : "";
    String initials = firstInitial + lastInitial;
    boolean isActive = "Active".equalsIgnoreCase(employee.getStatus());
%>

<html>
<head>
    <title>My Profile - Auto Fuel X</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/employee/profile.css">
</head>
<body>

<%if (empType.equals("Refuel Cashier")) {%>
<jsp:include page="/views/refuel-cashier/header.jsp"/>
<%} else if (empType.equals("Customer Care Officer")) {%>
<jsp:include page="/views/customer-care/header.jsp"/>
<%} else if (empType.equals("Service Center Staff")) {%>
<jsp:include page="/views/service-center/header.jsp"/>
<%}%>


<div class="profile-container">
    <!-- Profile Header with Avatar -->
    <div class="profile-header">
        <div class="profile-avatar"><%= initials %>
        </div>
        <div class="profile-info">
            <h1><%= employee.getFirstName() %> <%= employee.getLastName() %>
            </h1>
            <p><%= empType %> <span
                    class="status-badge <%= isActive ? "status-active" : "status-inactive" %>"><%= employee.getStatus() %></span>
            </p>
            <p>Employee ID: <%= employee.getEmployeeID() %>
            </p>
        </div>
    </div>

    <div class="profile-dashboard">
        <!-- Main Content -->
        <div class="profile-main">
            <!-- Personal Info -->
            <div class="profile-section">
                <h2>
                    <div class="section-icon">P</div>
                    Personal Information
                </h2>
                <div class="info-grid">
                    <div class="info-item">
                        <div class="info-label">Employee ID</div>
                        <div class="info-value"><%= employee.getEmployeeID() %>
                        </div>
                    </div>
                    <div class="info-item">
                        <div class="info-label">Full Name</div>
                        <div class="info-value"><%= employee.getFirstName() %> <%= employee.getLastName() %>
                        </div>
                    </div>
                    <div class="info-item">
                        <div class="info-label">Date of Birth</div>
                        <div class="info-value"><%= employee.getDateOfBirth() %>
                        </div>
                    </div>
                    <div class="info-item">
                        <div class="info-label">Email</div>
                        <div class="info-value"><%= employee.getEmail() %>
                        </div>
                    </div>
                </div>

                <p><strong>Phone Numbers:</strong></p>
                <% if (phoneNumbers != null && !phoneNumbers.isEmpty()) { %>
                <ul class="phone-list">
                    <% for (String phone : phoneNumbers) { %>
                    <li>
                        <div class="phone-icon">📱</div>
                        <span><%= phone %></span>
                    </li>
                    <% } %>
                </ul>
                <% } else { %>
                <p style="font-style: italic; margin-left: var(--spacing-md);">No phone numbers available</p>
                <% } %>
            </div>

            <!-- Employment Details -->
            <div class="profile-section">
                <h2>
                    <div class="section-icon">E</div>
                    Employment Details
                </h2>
                <div class="info-grid">
                    <div class="info-item">
                        <div class="info-label">Role</div>
                        <div class="info-value"><%= empType %>
                        </div>
                    </div>
                    <div class="info-item">
                        <div class="info-label">Type</div>
                        <div class="info-value"><%= employee.getType() %>
                        </div>
                    </div>
                    <div class="info-item">
                        <div class="info-label">Status</div>
                        <div class="info-value"><%= employee.getStatus() %>
                        </div>
                    </div>
                    <div class="info-item">
                        <div class="info-label">Hire Date</div>
                        <div class="info-value"><%= employee.getHireDate() %>
                        </div>
                    </div>
                    <div class="info-item">
                        <div class="info-label">Salary</div>
                        <div class="info-value">Rs. <%= employee.getSalary() %>
                        </div>
                    </div>
                    <div class="info-item">
                        <div class="info-label">Additional Info</div>
                        <div class="info-value">
                            <%-- Role Specific Attributes --%>
                            <% if ("Service Center Staff".equals(empType)) { %>
                            <%= employee.getSkillSet() %>
                            <% } else if ("Refuel Cashier".equals(empType)) { %>
                            Shift: <%= employee.getShift() %>
                            <% } else if ("Admin".equals(empType)) { %>
                            Role: <%= employee.getRole() %>
                            <% } else if ("Customer Care Officer".equals(empType)) { %>
                            <em>No additional attributes</em>
                            <% } %>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Sidebar -->
        <div class="profile-sidebar">
            <!-- Address -->
            <div class="profile-section">
                <h2>
                    <div class="section-icon">A</div>
                    Address
                </h2>
                <div class="address-card">
                    <p><%= employee.getAddressNo() %>
                    </p>
                    <p><%= employee.getAddressLane() %>
                    </p>
                    <p><%= employee.getAddressArea() %>
                    </p>
                </div>
            </div>

            <!-- Quick Actions -->
            <div class="profile-section">
                <h2>
                    <div class="section-icon">Q</div>
                    Quick Actions
                </h2>
                <div style="display: flex; flex-direction: column; gap: var(--spacing-md);">
                    <a href="<%=request.getContextPath()%>/views/employee/edit.jsp" class="btn btn-primary">Edit
                        Profile</a>
                    <a href="<%=request.getContextPath()%>/user/logout" class="btn logout">Logout</a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>