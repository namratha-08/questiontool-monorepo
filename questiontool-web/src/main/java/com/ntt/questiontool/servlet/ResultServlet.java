package com.ntt.questiontool.servlet;

import com.ntt.questiontool.dao.AttemptDAO;
import com.ntt.questiontool.dao.TestDAO;
import com.ntt.questiontool.model.Attempt;
import com.ntt.questiontool.model.AttemptAnswer;
import com.ntt.questiontool.model.Question;
import com.ntt.questiontool.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name="ResultServlet", urlPatterns = {"/result"})
public class ResultServlet extends HttpServlet {
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
            throws ServletException, IOException {

        String language = req.getParameter("language");
        if (language == null) language = "Java";

        TestDAO dao = new TestDAO();
        List<Question> questions = dao.getQuestionsByLanguage(language);

        int total = questions.size();
        int score = 0;

        Attempt attempt = new Attempt();
        // get user from session if present
        User user = (User) req.getSession().getAttribute("user");
        int userId = (user != null) ? user.getId() : 0;
        attempt.setUserId(userId);
        attempt.setTestId(null); // set test id if you have tests table and choose a test
        attempt.setStartedAt(new Timestamp(System.currentTimeMillis())); // or track real start time
        attempt.setFinishedAt(new Timestamp(System.currentTimeMillis()));

        for (Question q : questions) {
            String paramName = "q" + q.getId();
            String answer = req.getParameter(paramName); // "A"/"B"...
            String correct = q.getCorrectOption(); // assume "A"/"B"/"C"/"D" set by TestDAO
            BigDecimal marks = BigDecimal.ZERO;
            if (answer != null && correct != null && answer.equalsIgnoreCase(correct)) {
                score++;
                marks = BigDecimal.ONE; // award 1 mark; change logic if you have weights
            }
            AttemptAnswer aa = new AttemptAnswer();
            aa.setQuestionId(q.getId());
            aa.setAnswerText(answer != null ? answer : "");
            aa.setMarksAwarded(marks);
            attempt.addAnswer(aa);
        }

        attempt.setTotalScore(BigDecimal.valueOf(score));

        // persist attempt + answers
        AttemptDAO attemptDAO = new AttemptDAO();
        int attemptId = attemptDAO.saveAttemptWithAnswers(attempt);
        if (attemptId > 0) {
            attempt.setId(attemptId);
            req.setAttribute("attemptId", attemptId);
        } else {
            req.setAttribute("attemptId", null);
        }

        req.setAttribute("score", score);
        req.setAttribute("total", total);
        req.getRequestDispatcher("/jsp/result.jsp").forward(req, resp);
    }
}
