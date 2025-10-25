package com.ntt.questiontool.servlet;

import com.ntt.questiontool.dao.TestDAO;
import com.ntt.questiontool.model.Question;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TestServlet", urlPatterns = {"/testServlet"})
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // handle GET by delegating to doPost logic
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // optionally allow calling with ?language=Java
        String language = request.getParameter("language");
        if (language == null || language.isBlank()) {
            language = "Java";
        }
        // reuse same processing as POST (duplicate small bit intentionally simple)
        TestDAO dao = new TestDAO();
        List<Question> questions = dao.getQuestionsByLanguage(language);

        request.setAttribute("questions", questions);
        request.setAttribute("language", language);
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/takeTest.jsp");
        rd.forward(request, response);
    }

    // keep POST behavior (if forms submit with POST)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // delegate to doGet to avoid code duplication
        doGet(request, response);
    }
}
