<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.autofuelx.model.Employee" %>
<%@ page import="java.util.List" %>

<%
    Employee employee = (Employee) request.getAttribute("employee");
    List<String> phoneNumbers = (List<String>) request.getAttribute("employee-phone-numbers");
%>

<html>
<head>
    <title>Update Employee</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/employee/update.css">

</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">
    <div class="page-header">
        <h1>Update Employee</h1>
        <p>Modify employee information and manage contact details</p>
    </div>

    <div class="employee-update-container">
        <div class="employee-form-section">
            <div class="card">
                <div class="card-header">
                    <h2 class="card-title">Employee Information</h2>
                </div>
                <div class="card-body">
                    <form action="<%=request.getContextPath()%>/admin/employee/update" method="post"
                          class="employee-form">
                        <input type="hidden" name="employeeID" value="<%= employee.getEmployeeID() %>">

                        <div class="form-row">
                            <div class="form-group">
                                <label for="firstName">First Name</label>
                                <input type="text" id="firstName" name="firstName"
                                       value="<%= employee.getFirstName() %>" required>
                            </div>
                            <div class="form-group">
                                <label for="lastName">Last Name</label>
                                <input type="text" id="lastName" name="lastName" value="<%= employee.getLastName() %>"
                                       required>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="dateOfBirth">Date of Birth</label>
                                <input type="date" id="dateOfBirth" name="dateOfBirth"
                                       value="<%= employee.getDateOfBirth() %>" required>
                            </div>
                            <div class="form-group">
                                <label for="salary">Salary</label>
                                <input type="text" id="salary" name="salary" value="<%= employee.getSalary() %>"
                                       required>
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
                                    <option value="On Leave" <%= "On Leave".equals(employee.getStatus()) ? "selected" : "" %>>
                                        On Leave
                                    </option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="hireDate">Hire Date</label>
                                <input type="date" id="hireDate" name="hireDate" value="<%= employee.getHireDate() %>"
                                       required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Address</label>
                            <div class="address-fields">
                                <input type="text" name="addressNo" placeholder="No"
                                       value="<%= employee.getAddressNo() %>" required>
                                <input type="text" name="addressLane" placeholder="Lane"
                                       value="<%= employee.getAddressLane() %>" required>
                                <input type="text" name="addressArea" placeholder="Area"
                                       value="<%= employee.getAddressArea() %>" required>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" id="email" name="email" value="<%= employee.getEmail() %>" required
                                       readonly>
                            </div>
                            <div class="form-group">
                                <label for="type">Employee Type</label>
                                <input type="text" id="type" name="type" readonly value="<%= employee.getType() %>"
                                       required>
                            </div>
                        </div>

                        <% if ("Refuel Cashier".equals(employee.getType())) { %>
                        <div class="form-group">
                            <label for="shift">Shift</label>
                            <select id="shift" name="shift">
                                <option value="Morning" <%= "Morning".equals(employee.getShift()) ? "selected" : "" %>>
                                    Morning
                                </option>
                                <option value="Afternoon" <%= "Afternoon".equals(employee.getShift()) ? "selected" : "" %>>
                                    Afternoon
                                </option>
                                <option value="Night" <%= "Night".equals(employee.getShift()) ? "selected" : "" %>>
                                    Night
                                </option>
                            </select>
                        </div>
                        <% } else if ("Admin".equals(employee.getType())) { %>
                        <div class="form-group">
                            <label for="role">Role</label>
                            <select id="role" name="role">
                                <option value="Super Admin" <%= "Super".equals(employee.getRole()) ? "selected" : "" %>>
                                    Super Admin
                                </option>
                                <option value="HR Manager" <%= "Normal".equals(employee.getRole()) ? "selected" : "" %>>
                                    HR Manager
                                </option>
                                <option value="Finance Manager" <%= "Finance Manager".equals(employee.getRole()) ? "selected" : "" %>>
                                    Finance Manager
                                </option>
                            </select>
                        </div>
                        <% } else if ("Service Center Staff".equals(employee.getType())) { %>
                        <div class="form-group">
                            <label for="skillSet">Skill Set</label>
                            <select id="skillSet" name="skillSet">
                                <option value="Mechanic" <%= "Mechanic".equals(employee.getSkillSet()) ? "selected" : "" %>>
                                    Mechanic
                                </option>
                                <option value="Electrician" <%= "Electrician".equals(employee.getSkillSet()) ? "selected" : "" %>>
                                    Electrician
                                </option>
                                <option value="Technician" <%= "Technician".equals(employee.getSkillSet()) ? "selected" : "" %>>
                                    Technician
                                </option>
                            </select>
                        </div>
                        <% } %>

                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary">Update Employee</button>
                            <a href="<%=request.getContextPath()%>/admin/employee/list"
                               class="btn btn-secondary">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="phone-numbers-section">
            <div class="card">
                <div class="card-header">
                    <h2 class="card-title">Manage Phone Numbers</h2>
                </div>
                <div class="card-body">
                    <div class="phone-list">
                        <% if (phoneNumbers != null && !phoneNumbers.isEmpty()) { %>
                        <% for (String phone : phoneNumbers) { %>
                        <div class="phone-item">
                            <div class="phone-number">
                                <img src="<%=request.getContextPath()%>/assets/imgs/phone-call.png"
                                     alt="icon of a phone with black background and red highlight">
                                <span><%= phone %></span>
                            </div>
                            <div class="phone-actions">
                                <form action="<%=request.getContextPath()%>/admin/employee/phone-number/delete"
                                      method="post">
                                    <input type="hidden" name="employee-ID" value="<%= employee.getEmployeeID() %>">
                                    <input type="hidden" name="phone-number" value="<%= phone %>">
                                    <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                </form>
                            </div>
                        </div>
                        <% } %>
                        <% } else { %>
                        <div class="no-phone-numbers">
                            <p>No phone numbers available for this employee.</p>
                        </div>
                        <% } %>
                    </div>

                    <div class="add-phone-form">
                        <h3>Add New Phone Number</h3>
                        <form action="<%=request.getContextPath()%>/admin/employee/phone-number/add" method="post">
                            <input type="hidden" name="employee-ID" value="<%= employee.getEmployeeID() %>">
                            <div class="form-group">
                                <input type="tel" name="phone-number" placeholder="Enter phone number"
                                       class="phone-number-input" required>
                            </div>
                            <button type="submit" class="btn btn-success">Add Phone Number</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>