<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Result</title>
    <link rel="stylesheet" href="../assets/css/style.css">
    <style>
      /* quick debug styling so it is visible */
      .debug { color: #444; font-size: 0.95rem; margin-top: 1rem; background:#f7f7f7; padding:8px; border-radius:6px; }
      .summary { margin-bottom: 1rem; }
    </style>
</head>
<body>
    <h2>Test Result</h2>

    <div class="summary">
        <p>Your Score: <strong><%= request.getAttribute("score") %></strong> / <strong><%= request.getAttribute("total") %></strong></p>
    </div>

    <!-- DEBUG: show attemptId set by the servlet -->
    <div class="debug">
        DEBUG: attemptId = <%= (request.getAttribute("attemptId") == null ? "(none)" : request.getAttribute("attemptId")) %>
    </div>

    <p><a href="dashboard.jsp">Back to Dashboard</a></p>
</body>
</html>
