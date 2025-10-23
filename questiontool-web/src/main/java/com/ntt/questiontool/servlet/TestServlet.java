package com.ntt.questiontool.servlet;

import com.ntt.questiontool.dao.TestDAO;
import com.ntt.questiontool.model.Question;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String language = request.getParameter("language");
        TestDAO dao = new TestDAO();
        List<Question> questions = dao.getQuestionsByLanguage(language);

        request.setAttribute("questions", questions);
        RequestDispatcher rd = request.getRequestDispatcher("jsp/takeTest.jsp");
        rd.forward(request, response);
    }
}
