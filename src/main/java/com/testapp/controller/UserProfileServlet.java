package com.testapp.controller;

import com.testapp.dao.UserTestDAO;
import com.testapp.dao.UserDAO;
import com.testapp.models.UserTest;
import com.testapp.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/profile")
public class UserProfileServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();
    private final UserTestDAO userTestDAO = new UserTestDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");

        try {
            User user = userDAO.findById(userId);
            List<UserTest> userQuizzes = userTestDAO.findByUserId(userId);

            req.setAttribute("user", user);
            req.setAttribute("userQuizzes", userQuizzes);
            req.getRequestDispatcher("/WEB-INF/views/userProfile.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error loading user profile", e);
        }
    }
}
