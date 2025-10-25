<%--
  Created by IntelliJ IDEA.
  User: Saachika
  Date: 9/28/2025
  Time: 10:51 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/views/admin/header.jsp"/>

<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/sub-header.css">
    <title>Dashboard</title>
</head>

<nav class="nav-horizontal">
    <a href="<%= request.getContextPath() %>/views/admin/employee/add.jsp">Add New Employee</a> |
    <a href="<%= request.getContextPath() %>/admin/employee/list">All employees</a> |
    <a href="<%= request.getContextPath() %>/admin/employee/list-by-type?type=service-center-staff">Service Center
        Staff</a> |
    <a href="<%= request.getContextPath() %>/admin/employee/list-by-type?type=admin">Admin</a> |
    <a href="<%= request.getContextPath() %>/admin/employee/list-by-type?type=customer-care-officer">Customer Care
        Officer</a> |
    <a href="<%= request.getContextPath() %>/admin/employee/list-by-type?type=refuel-cashier">Refuel Cashier</a>
</nav>