package com.testapp.controller;

import com.testapp.dao.QuestionDAO;
import com.testapp.dao.TestDAO;
import com.testapp.models.Test;
import com.testapp.models.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/createQuiz")
public class CreateTestServlet extends HttpServlet {

    private final TestDAO testDAO = new TestDAO();
    private final QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Question> allQuestions = questionDAO.findAll();
            req.setAttribute("allQuestions", allQuestions);
            req.getRequestDispatcher("/WEB-INF/views/admin/createQuiz.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error loading questions", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String quizTitle = req.getParameter("quizTitle");
        String[] questionIds = req.getParameterValues("questionIds");

        if (quizTitle == null || questionIds == null || questionIds.length == 0) {
            req.setAttribute("errorMessage", "Quiz title and at least one question are required.");
            req.getRequestDispatcher("/WEB-INF/views/admin/createQuiz.jsp").forward(req, resp);
            return;
        }

        try {
            // Create quiz
            Test test = new Test();
            test.setTitle(quizTitle);
            int quizId = testDAO.save(test);

            // Assign questions to quiz
            for (String questionId : questionIds) {
                questionDAO.assignToQuiz(Integer.parseInt(questionId), quizId);
            }

            resp.sendRedirect(req.getContextPath() + "/admin/quizzes");
        } catch (SQLException e) {
            throw new ServletException("Error saving quiz", e);
        }
    }
}