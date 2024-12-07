package com.testapp.controller;

import com.testapp.dao.QuestionDAO;
import com.testapp.models.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/questions")
public class QuestionManagementServlet extends HttpServlet {

    private final QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Question> questions = questionDAO.findAll();
            req.setAttribute("questions", questions);
            req.getRequestDispatcher("/WEB-INF/views/admin/questions.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error loading questions", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String text = req.getParameter("text");

        if (title == null || title.trim().isEmpty() || text == null || text.trim().isEmpty()) {
            req.setAttribute("error", "Title and text cannot be empty.");
            doGet(req, resp);
            return;
        }

        try {
            Question question = new Question();
            question.setTitle(title);
            question.setQuestionText(text);
            questionDAO.save(question);

            resp.sendRedirect(req.getContextPath() + "/admin/questions");
        } catch (SQLException e) {
            throw new ServletException("Error saving question", e);
        }
    }
}
