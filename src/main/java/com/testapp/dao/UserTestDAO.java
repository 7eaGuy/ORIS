package com.testapp.dao;

import com.testapp.models.UserTest;
import com.testapp.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserTestDAO extends AbstractDAO {

    public void save(int userId, int quizId, int score) throws SQLException {
        String query = "INSERT INTO user_quiz (user_id, quiz_id, score) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, quizId);
            statement.setInt(3, score);
            statement.executeUpdate();
        }
    }

    public List<UserTest> findByUserId(int userId) throws SQLException {
        String query = "SELECT uq.id, q.title, uq.score " +
                "FROM user_quiz uq " +
                "JOIN quiz q ON uq.quiz_id = q.id " +
                "WHERE uq.user_id = ?";
        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            List<UserTest> results = new ArrayList<>();
            while (resultSet.next()) {
                UserTest userTest = new UserTest();
                userTest.setId(resultSet.getInt("id"));
                userTest.setTestTitle(resultSet.getString("title"));
                userTest.setScore(resultSet.getInt("score"));
                results.add(userTest);
            }
            return results;
        }
    }

    public void update(UserTest userQuiz) throws SQLException {
        String query = "UPDATE user_quizzes SET userId = ?, quizId = ?, score = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userQuiz.getUserId());
            statement.setInt(2, userQuiz.getQuizId());
            statement.setInt(3, userQuiz.getScore());
            statement.setInt(4, userQuiz.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM user_quizzes WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
