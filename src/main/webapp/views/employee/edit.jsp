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
    <div class="profile-header">
        <h1>Edit Profile</h1>
        <p>Update your personal information and preferences</p>
    </div>

    <div class="profile-content">
        <div class="profile-sidebar">
            <div class="profile-actions-sidebar">
                <a href="<%=request.getContextPath()%>/views/employee/profile.jsp" class="btn btn-secondary">Back to
                    Profile</a>
            </div>
        </div>

        <div class="profile-main">
            <form action="<%=request.getContextPath()%>/employee/update" method="post" class="profile-form">
                <input type="hidden" name="employeeID" value="<%= employee.getEmployeeID() %>"/>

                <!-- Personal Info -->
                <div class="profile-card">
                    <div class="card-header">
                        <h2>Personal Information</h2>
                        <span class="card-icon">👤</span>
                    </div>
                    <div class="card-body">
                        <div class="form-row">
                            <div class="form-group">
                                <label for="firstName">First Name</label>
                                <input type="text" id="firstName" name="firstName"
                                       value="<%= employee.getFirstName() %>" required/>
                            </div>
                            <div class="form-group">
                                <label for="lastName">Last Name</label>
                                <input type="text" id="lastName" name="lastName" value="<%= employee.getLastName() %>"
                                       required/>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="dateOfBirth">Date of Birth</label>
                                <input type="date" id="dateOfBirth" name="dateOfBirth"
                                       value="<%= employee.getDateOfBirth() %>" required/>
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" id="email" name="email" value="<%= employee.getEmail() %>" required
                                       readonly/>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" id="password" name="password"
                                       value="<%= employee.getPassword() %>" required/>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Employment Details -->
                <div class="profile-card">
                    <div class="card-header">
                        <h2>Employment Details</h2>
                        <span class="card-icon">💼</span>
                    </div>
                    <div class="card-body">
                        <div class="form-row">
                            <div class="form-group">
                                <label for="role">Role</label>
                                <input type="text" id="role" value="<%= empType %>" readonly/>
                            </div>
                            <div class="form-group">
                                <label for="type">Type</label>
                                <input type="text" id="type" name="type" value="<%= employee.getType() %>" readonly/>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="status">Status</label>
                                <select id="status" name="status" required>
                                    <option value="Active" <%= "Active".equals(employee.getStatus()) ? "selected" : "" %>>
                                        Active
                                    </option>
                                    <option value="Inactive" <%= "Inactive".equals(employee.getStatus()) ? "selected" : "" %>>
                                        Inactive
                                    </option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="hireDate">Hire Date</label>
                                <input type="date" id="hireDate" name="hireDate" value="<%= employee.getHireDate() %>"
                                       required/>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="salary">Salary (Rs)</label>
                                <input type="number" step="0.01" id="salary" name="salary"
                                       value="<%= employee.getSalary() %>" required/>
                            </div>
                            <%-- admin/ sevice center staff and refuel cashier --%>
                            <% if ("Service Center Staff".equals(empType)) { %>
                            <div class="form-group">
                                <label for="skillSet">Skill Set</label>
                                <input type="text" id="skillSet" name="skillSet" value="<%= employee.getSkillSet() %>"/>
                            </div>
                            <% } else if ("Refuel Cashier".equals(empType)) { %>
                            <div class="form-group">
                                <label for="shift">Shift</label>
                                <select id="shift" name="shift">
                                    <option value="Morning" <%= "Day".equals(employee.getShift()) ? "selected" : "" %>>
                                        Day
                                    </option>
                                    <option value="Night" <%= "Night".equals(employee.getShift()) ? "selected" : "" %>>
                                        Night
                                    </option>
                                </select>
                            </div>
                            <% } else if ("Admin".equals(empType)) { %>
                            <div class="form-group">
                                <label for="adminRole">System Role</label>
                                <input type="text" id="adminRole" name="adminRole" value="<%= employee.getRole() %>"/>
                            </div>
                            <% } else if ("Customer Care Officer".equals(empType)) { %>
                            <div class="form-group">
                                <label>Specialization</label>
                                <input type="text" value="Customer Relations" readonly/>
                            </div>
                            <% } %>
                        </div>
                    </div>
                </div>

                <!-- address -->
                <div class="profile-card">
                    <div class="card-header">
                        <h2>Address</h2>
                        <span class="card-icon">🏠</span>
                    </div>
                    <div class="card-body">
                        <div class="form-row">
                            <div class="form-group">
                                <label for="addressNo">Address No</label>
                                <input type="text" id="addressNo" name="addressNo"
                                       value="<%= employee.getAddressNo() %>"/>
                            </div>
                            <div class="form-group">
                                <label for="addressLane">Lane</label>
                                <input type="text" id="addressLane" name="addressLane"
                                       value="<%= employee.getAddressLane() %>"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="addressArea">Area</label>
                            <input type="text" id="addressArea" name="addressArea"
                                   value="<%= employee.getAddressArea() %>"/>
                        </div>
                    </div>
                </div>

                <!-- save -->
                <div class="profile-actions">
                    <button type="submit" class="btn btn-primary">Save Changes</button>
                    <a href="<%=request.getContextPath()%>/views/employee/profile.jsp"
                       class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>