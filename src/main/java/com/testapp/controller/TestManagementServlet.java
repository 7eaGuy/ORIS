package com.testapp.controller;

import com.testapp.dao.TestDAO;
import com.testapp.dao.QuestionDAO;

import com.testapp.models.Question;
import com.testapp.models.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/quizzes")
public class TestManagementServlet extends HttpServlet {

    private final TestDAO testDAO = new TestDAO();
    private final QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Test> quizzes = testDAO.findAll();
            List<Question> questions = questionDAO.findAll();

            req.setAttribute("quizzes", quizzes);
            req.setAttribute("questions", questions);
            req.getRequestDispatcher("/WEB-INF/views/admin/quizzes.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error loading quizzes", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String[] questionIds = req.getParameterValues("questionIds");

        if (title == null || title.trim().isEmpty() || questionIds == null || questionIds.length == 0) {
            req.setAttribute("error", "Title and at least one question must be selected.");
            doGet(req, resp);
            return;
        }

        try {
            Test quiz = new Test();
            quiz.setTitle(title);

            // Save quiz and get generated ID
            int quizId = testDAO.save(quiz);

            // Assign questions to the quiz
            for (String questionId : questionIds) {
                questionDAO.assignToQuiz(Integer.parseInt(questionId), quizId);
            }

            resp.sendRedirect(req.getContextPath() + "/admin/quizzes");
        } catch (SQLException e) {
            throw new ServletException("Error saving quiz", e);
        }
    }

}
