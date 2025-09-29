<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/28/2025
  Time: 10:51 PM
  To change this template use File | Settings | File Templates.
--%>
<nav>
    <a href="<%= request.getContextPath() %>/admin/employee/all">All employees</a> |
    <a href="<%= request.getContextPath() %>/admin/employee/list-by-type?type=service-center-staff">Service Center Staff</a> |
    <a href="<%= request.getContextPath() %>/admin/employee/list-by-type?type=admin">Admin</a> |
    <a href="<%= request.getContextPath() %>/admin/employee/list-by-type?type=customer-care-officer">Customer Care Officer</a> |
    <a href="<%= request.getContextPath() %>/admin/employee/list-by-type?type=refuel-cashier">Refuel Cashier</a> |
</nav>
<hr>