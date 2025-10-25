package com.ntt.questiontool.servlet;

import com.ntt.questiontool.dao.UserDAO;
import com.ntt.questiontool.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.validateUser(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("fullname", user.getFullName());
            session.setAttribute("role", user.getRole());

            // ✅ Redirect to dashboard after login
            response.sendRedirect(request.getContextPath() + "/jsp/dashboard.jsp");
        } else {
            // If invalid credentials
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }
}
