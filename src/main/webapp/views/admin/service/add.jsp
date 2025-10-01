<%--
  Created by IntelliJ IDEA.
  User: KHThi
  Date: 9/29/2025
  Time: 8:30 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Service</title>
</head>
<body>
<h1>Add New Service</h1>


<form method="post" action="<%= request.getContextPath() %>/admin/service/add">
    <div>
        <label for="type">Service Type:</label>
        <input type="text" id="type" name="type" required>
    </div>

    <div>
        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="4" cols="50"></textarea>
    </div>

    <div>
        <label for="cost">Cost ($):</label>
        <input type="number" id="cost" name="cost" step="0.01" min="0" required>
    </div>

    <div>
        <button type="submit">Add Service</button>
        <a href="<%= request.getContextPath() %>/services">Cancel</a>
    </div>
</form>
</body>
</html>