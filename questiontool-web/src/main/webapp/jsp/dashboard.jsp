<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ntt.questiontool.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="../assets/css/style.css">
</head>
<body>
    <h2>Welcome, <%= user.getUsername() %></h2>
    <form method="post" action="../test">
        <label>Select Programming Language:</label>
        <select name="language" required>
            <option value="Java">Java</option>
            <option value="Python">Python</option>
            <option value="C">C</option>
        </select>
        <input type="submit" value="Start Test">
    </form>
</body>
</html>
