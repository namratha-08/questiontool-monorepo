<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title><c:out value="${pageTitle != null ? pageTitle : 'Question Tool'}" /></title>

  <!-- Bootstrap 5 CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-..." crossorigin="anonymous">

  <!-- Optional Font / Icons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />

  <!-- Chart.js (for performance view later) -->
  <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>

  <!-- Custom CSS (you'll add this next) -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm mb-4">
  <div class="container">
    <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/">Question Tool</a>
    <div class="collapse navbar-collapse">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/jsp/dashboard.jsp">Home</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/jsp/performance.jsp">Performance</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/jsp/login.jsp">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container">
