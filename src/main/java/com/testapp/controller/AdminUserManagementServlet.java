package com.testapp.controller;

import com.testapp.dao.UserDAO;
import com.testapp.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/users")
public class AdminUserManagementServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> users = userDAO.findAll();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/views/admin/userManagement.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error loading users", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String userIdParam = req.getParameter("userId");

        if (action != null && userIdParam != null) {
            try {
                int userId = Integer.parseInt(userIdParam);
                if (action.equals("delete")) {
                    userDAO.delete(userId);
                } else if (action.equals("makeAdmin")) {
                    userDAO.updateRole(userId, true);
                } else if (action.equals("revokeAdmin")) {
                    userDAO.updateRole(userId, false);
                }
                resp.sendRedirect(req.getContextPath() + "/admin/users");
            } catch (SQLException | NumberFormatException e) {
                throw new ServletException("Error managing users", e);
            }
        }
    }
}
