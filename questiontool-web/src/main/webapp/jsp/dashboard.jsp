<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj == null || sessionObj.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String fullname = (String) sessionObj.getAttribute("fullname");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Question Tool</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5 text-center">
    <div class="card shadow p-4 mx-auto" style="max-width: 600px;">
        <h2 class="mb-3">Welcome, <%= fullname %> 👋</h2>
        <p>Select your preferred programming language to begin the test:</p>

        <form action="${pageContext.request.contextPath}/test" method="get">
            <div class="mb-3">
                <select name="language" class="form-select" required>
                    <option value="">-- Select Language --</option>
                    <option value="Java">Java</option>
                    <option value="Python">Python</option>
                    <option value="C++">C++</option>
                    <option value="JavaScript">JavaScript</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary w-100">Start Test</button>
        </form>

        <hr>
        <a href="logout.jsp" class="btn btn-outline-danger w-100 mt-2">Logout</a>
    </div>
</div>
</body>
</html>
