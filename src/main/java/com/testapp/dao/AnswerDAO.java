package com.testapp.dao;

import com.testapp.models.Answer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO extends AbstractDAO {

    public void save(Answer answer) throws SQLException {
        String query = "INSERT INTO answers (answer_text, isCorrect, questionId) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, answer.getAnswerText());
            statement.setBoolean(2, answer.isCorrect());
            statement.setInt(3, answer.getQuestionId());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    answer.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Answer> findByQuestionId(int questionId) throws SQLException {
        List<Answer> answers = new ArrayList<>();
        String query = "SELECT * FROM answers WHERE questionId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, questionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    answers.add(new Answer(
                            resultSet.getInt("id"),
                            resultSet.getString("answer_text"),
                            resultSet.getBoolean("isCorrect"),
                            resultSet.getInt("questionId")
                    ));
                }
            }
        }
        return answers;
    }

    public void update(Answer answer) throws SQLException {
        String query = "UPDATE answers SET answer_text = ?, isCorrect = ?, questionId = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, answer.getAnswerText());
            statement.setBoolean(2, answer.isCorrect());
            statement.setInt(3, answer.getQuestionId());
            statement.setInt(4, answer.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM answers WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
