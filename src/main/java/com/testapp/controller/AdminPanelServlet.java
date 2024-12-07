package com.testapp.controller;

import com.testapp.dao.TestDAO;
import com.testapp.dao.QuestionDAO;
import com.testapp.models.Test;
import com.testapp.models.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/panel")
public class AdminPanelServlet extends HttpServlet {

    private final TestDAO testDAO = new TestDAO();
    private final QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Test> quizzes = testDAO.findAll();
            List<Question> questions = questionDAO.findAll();

            req.setAttribute("quizzes", quizzes);
            req.setAttribute("questions", questions);
            req.getRequestDispatcher("/WEB-INF/views/adminPanel.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error loading admin panel", e);
        }
    }
}
