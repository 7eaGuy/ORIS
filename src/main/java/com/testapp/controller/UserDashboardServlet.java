package com.testapp.controller;


import com.testapp.dao.TestDAO;
import com.testapp.dao.UserTestDAO;
import com.testapp.models.Test;
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

@WebServlet("/dashboard")
public class UserDashboardServlet extends HttpServlet {

    private final UserTestDAO userTestDAO = new UserTestDAO();
    private final TestDAO testDAO = new TestDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        try {
            List<UserTest> userQuizzes = userTestDAO.findByUserId(user.getId());
            req.setAttribute("userQuizzes", userQuizzes);

            req.getRequestDispatcher("/WEB-INF/views/user/dashboard.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error loading user dashboard", e);
        }
    }
}
