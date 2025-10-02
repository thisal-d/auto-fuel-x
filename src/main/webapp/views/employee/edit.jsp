<%--
  Created by IntelliJ IDEA.
  User: KHThi
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
    String empType = employee.getType();
%>

<html>
<head>
    <title>Edit Profile - Auto Fuel X</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/employee/edit.css">
</head>
<body>

<%if (empType.equals("Refuel Cashier")) {%>
<jsp:include page="/views/refuel-cashier/header.jsp"/>
<%} else if (empType.equals("Customer Care Officer")) {%>
<jsp:include page="/views/customer-care/header.jsp"/>
<%} else if (empType.equals("Admin")) {%>
<jsp:include page="/views/admin/header.jsp"/>
<%} else if (empType.equals("Service Center Staff")) {%>
<jsp:include page="/views/service-center/header.jsp"/>
<%}%>

<div class="profile-container">
    <h1>Edit Profile</h1>

    <form action="<%=request.getContextPath()%>/employee/update" method="post">
        <input type="hidden" name="employeeID" value="<%= employee.getEmployeeID() %>"/>

        <!-- Personal Info -->
        <div class="profile-card">
            <h2>Personal Information</h2>
            <label>First Name:</label>
            <input type="text" name="firstName" value="<%= employee.getFirstName() %>" required />

            <label>Last Name:</label>
            <input type="text" name="lastName" value="<%= employee.getLastName() %>" required />

            <label>Date of Birth:</label>
            <input type="date" name="dateOfBirth" value="<%= employee.getDateOfBirth() %>" required />

            <label>Email:</label>
            <input type="email" name="email" value="<%= employee.getEmail() %>" required readonly/>

            <label>Password:</label>
            <input type="password" name="password" value="<%= employee.getPassword() %>" required />
        </div>

        <!-- Employment Details -->
        <div class="profile-card">
            <h2>Employment Details</h2>
            <label>Role:</label>
            <input type="text" value="<%= empType %>" readonly />

            <label>Type:</label>
            <input type="text" name="type" value="<%= employee.getType() %>" readonly />

            <label>Status:</label>
            <select name="status" required>
                <option value="Active" <%= "Active".equals(employee.getStatus()) ? "selected" : "" %>>Active</option>
                <option value="Inactive" <%= "Inactive".equals(employee.getStatus()) ? "selected" : "" %>>Inactive</option>
            </select>

            <label>Hire Date:</label>
            <input type="date" name="hireDate" value="<%= employee.getHireDate() %>" required />

            <label>Salary (Rs):</label>
            <input type="number" step="0.01" name="salary" value="<%= employee.getSalary() %>" required />

            <%-- admin/ sevice center staff and erfuel cashier --%>
            <% if ("Service Center Staff".equals(empType)) { %>
            <label>Skill Set:</label>
            <input type="text" name="skillSet" value="<%= employee.getSkillSet() %>" />
            <% } else if ("Refuel Cashier".equals(empType)) { %>
            <label>Shift:</label>
            <input type="text" name="shift" value="<%= employee.getShift() %>" />
            <% } else if ("Admin".equals(empType)) { %>
            <label>System Role:</label>
            <input type="text" name="adminRole" value="<%= employee.getRole() %>" />
            <% } else if ("Customer Care Officer".equals(empType)) { %>
            <p>No extra fields to edit.</p>
            <% } %>
        </div>

        <!-- address -->
        <div class="profile-card">
            <h2>Address</h2>
            <label>Address No:</label>
            <input type="text" name="addressNo" value="<%= employee.getAddressNo() %>" />

            <label>Lane:</label>
            <input type="text" name="addressLane" value="<%= employee.getAddressLane() %>" />

            <label>Area:</label>
            <input type="text" name="addressArea" value="<%= employee.getAddressArea() %>" />
        </div>

        <!-- save -->
        <div class="profile-actions">
            <button type="submit" class="btn">Save Changes</button>
            <a href="<%=request.getContextPath()%>/views/employee/profile.jsp" class="btn cancel">Cancel</a>
        </div>
    </form>
</div>

</body>
</html>
