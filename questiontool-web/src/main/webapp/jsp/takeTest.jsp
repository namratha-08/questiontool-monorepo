<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ntt.questiontool.model.Question"%>
<%
    List<Question> questions = (List<Question>) request.getAttribute("questions");
%>
<html>
<head>
    <title>Take Test</title>
    <link rel="stylesheet" href="../assets/css/style.css">
</head>
<body>
    <h2>Test</h2>
    <form method="post" action="../result">
        <%
            if (questions != null) {
                for (Question q : questions) {
        %>
        <p><b><%= q.getQuestionText() %></b></p>
        <input type="radio" name="answer" value="correct"> <%= q.getOptionA() %><br>
        <input type="radio" name="answer" value="wrong"> <%= q.getOptionB() %><br>
        <input type="radio" name="answer" value="wrong"> <%= q.getOptionC() %><br>
        <input type="radio" name="answer" value="wrong"> <%= q.getOptionD() %><br><br>
        <%      }
            }
        %>
        <input type="submit" value="Submit Test">
    </form>
</body>
</html>
