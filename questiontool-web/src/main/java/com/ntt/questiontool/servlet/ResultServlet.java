package com.ntt.questiontool.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] answers = request.getParameterValues("answer");
        String[] questionIds = request.getParameterValues("questionId");
        int score = 0;

        if (answers != null && questionIds != null) {
            for (String ans : answers) {
                if (ans.equalsIgnoreCase("correct")) { // For simplicity, mark as "correct" or "wrong"
                    score++;
                }
            }
        }

        request.setAttribute("score", score);
        RequestDispatcher rd = request.getRequestDispatcher("jsp/result.jsp");
        rd.forward(request, response);
    }
}
