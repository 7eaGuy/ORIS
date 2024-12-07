package com.testapp.controller;

import com.testapp.dao.UserDAO;
import com.testapp.models.User;
import com.testapp.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.setAttribute("error", "Username and password cannot be empty.");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        try {
            if (userDAO.findByUsername(username) != null) {
                req.setAttribute("error", "Username already exists.");
                req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
                return;
            }

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(PasswordUtil.hashPassword(password)); // Используем PasswordUtil для хэширования пароля
            newUser.setAdmin(false);

            userDAO.save(newUser);

            resp.sendRedirect(req.getContextPath() + "/login");
        } catch (SQLException e) {
            throw new ServletException("Error during registration", e);
        }
    }
}