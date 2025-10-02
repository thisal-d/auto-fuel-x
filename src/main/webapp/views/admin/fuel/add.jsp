<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add Fuel Type</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/fuel/add.css">
</head>
<body>
<jsp:include page="/views/admin/header.jsp"/>

<div class="fuel-container">
  <div class="fuel-header">
    <h1 class="fuel-title">Add New Fuel Type</h1>
  </div>

  <form action="<%=request.getContextPath()%>/admin/fuel/add" method="post" class="fuel-form">
    <div class="form-group">
      <label for="type" class="form-label">Fuel Type:</label>
      <input type="text" name="type" id="type" class="form-control" required>
      <div class="form-help">Enter the name of the fuel type (e.g., Regular, Premium, Diesel)</div>
    </div>

    <div class="form-group">
      <label for="quantity" class="form-label">Quantity (liters):</label>
      <input type="number" step="0.01" name="quantity" id="quantity" class="form-control" required>
      <div class="form-help">Enter the initial quantity in liters</div>
    </div>

    <div class="form-group">
      <label for="cost" class="form-label">Cost per Liter:</label>
      <input type="number" step="0.01" name="costPerLiter" id="cost" class="form-control" required>
      <div class="form-help">Enter the cost per liter in your local currency</div>
    </div>

    <div class="form-actions">
      <button type="submit" class="btn btn-primary">Add Fuel</button>
      <a href="<%=request.getContextPath()%>/admin/dashboard" class="btn btn-secondary">Cancel</a>
    </div>
  </form>

  <div class="fuel-info-card">
    <h2 class="info-card-title">About Adding Fuel Types</h2>
    <div class="info-card-content">
      <p>When adding a new fuel type to the system, please ensure you provide accurate information:</p>
      <ul class="info-list">
        <li>Fuel Type should be descriptive and easily recognizable</li>
        <li>Initial Quantity should reflect the actual available amount</li>
        <li>Cost per Liter should be set according to current market rates</li>
        <li>Once added, fuel types can be managed through the Fuel Management section</li>
      </ul>
    </div>
  </div>
</div>
</body>
</html>