package com.testapp.controller;


import com.testapp.dao.UserDAO;
import com.testapp.models.User;
import com.testapp.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/changePassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");

        try {
            User user = userDAO.findById(userId);

            if (PasswordUtil.checkPassword(oldPassword, user.getPassword())) {
                String encryptedNewPassword = PasswordUtil.hashPassword(newPassword);
                user.setPassword(encryptedNewPassword);
                userDAO.update(user);

                resp.sendRedirect(req.getContextPath() + "/profile");
            } else {
                req.setAttribute("error", "Incorrect old password");
                req.getRequestDispatcher("/WEB-INF/views/changePassword.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException("Error changing password", e);
        }
    }
}
