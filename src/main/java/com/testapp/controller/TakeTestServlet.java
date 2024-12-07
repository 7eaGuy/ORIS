package com.testapp.controller;

import com.testapp.dao.QuestionDAO;
import com.testapp.dao.TestDAO;
import com.testapp.dao.UserTestDAO;
import com.testapp.models.Question;
import com.testapp.models.Test;
import com.testapp.models.User;
import com.testapp.models.UserTest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/quiz/take")
public class TakeTestServlet extends HttpServlet {

    private final TestDAO testDAO = new TestDAO();
    private final QuestionDAO questionDAO = new QuestionDAO();
    private final UserTestDAO userTestDAO = new UserTestDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String testIdParam = req.getParameter("testId");
        if (testIdParam == null) {
            resp.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }

        try {
            int testId = Integer.parseInt(testIdParam);
            Test quiz = testDAO.findById(testId);
            List<Question> questions = questionDAO.findByQuizId(testId);

            req.setAttribute("quiz", quiz);
            req.setAttribute("questions", questions);
            req.getRequestDispatcher("/WEB-INF/views/quiz/takeQuiz.jsp").forward(req, resp);
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error loading quiz", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String quizIdParam = req.getParameter("quizId");
        if (quizIdParam == null) {
            resp.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }

        try {
            int quizId = Integer.parseInt(quizIdParam);
            List<Question> questions = questionDAO.findByQuizId(quizId);

            // Calculate score
            int score = 0;
            for (Question question : questions) {
                String userAnswer = req.getParameter("answer_" + question.getId());
                if (userAnswer != null && questionDAO.isCorrectAnswer(question.getId(), userAnswer)) {
                    score++;
                }
            }

            // Save result to UserQuiz
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null) {
                userTestDAO.save(user.getId(), quizId, score);
            }

            resp.sendRedirect(req.getContextPath() + "/quiz/result?quizId=" + quizId + "&score=" + score);
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error processing quiz", e);
        }
    }
}
