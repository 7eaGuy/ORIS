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

@WebServlet("/admin/createQuestion")
public class CreateQuestionServlet extends HttpServlet {

    private final QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/admin/createQuestion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questionTitle = req.getParameter("questionTitle");
        String questionText = req.getParameter("questionText");

        if (questionTitle == null || questionText == null) {
            req.setAttribute("errorMessage", "Question title and text are required.");
            req.getRequestDispatcher("/WEB-INF/views/admin/createQuestion.jsp").forward(req, resp);
            return;
        }

        try {
            Question question = new Question();
            question.setTitle(questionTitle);
            question.setQuestionText(questionText);
            questionDAO.save(question);

            resp.sendRedirect(req.getContextPath() + "/admin/questions");
        } catch (SQLException e) {
            throw new ServletException("Error saving question", e);
        }
    }
}
