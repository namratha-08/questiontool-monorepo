<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Result</title>
    <link rel="stylesheet" href="../assets/css/style.css">
</head>
<body>
    <h2>Test Result</h2>
    <p>Your Score: <%= request.getAttribute("score") %></p>
    <a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>
