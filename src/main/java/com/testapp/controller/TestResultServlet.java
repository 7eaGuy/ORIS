package com.testapp.controller;

import com.testapp.dao.TestDAO;
import com.testapp.models.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/test/result")
public class TestResultServlet extends HttpServlet {

    private final TestDAO quizDAO = new TestDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String quizIdParam = req.getParameter("quizId");
        String scoreParam = req.getParameter("score");

        if (quizIdParam == null || scoreParam == null) {
            resp.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }

        try {
            int testId = Integer.parseInt(quizIdParam);
            int score = Integer.parseInt(scoreParam);

            Test test = quizDAO.findById(testId);
            req.setAttribute("quiz", test);
            req.setAttribute("score", score);

            req.getRequestDispatcher("/WEB-INF/views/quiz/result.jsp").forward(req, resp);
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error loading result", e);
        }
    }
}
