package com.testapp.dao;

import com.testapp.models.Question;
import com.testapp.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO extends AbstractDAO {

    public void save(Question question) throws SQLException {
        String query = "INSERT INTO questions (title, question_text, quizId) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, question.getTitle());
            statement.setString(2, question.getQuestionText());
            statement.setInt(3, question.getQuizId());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    question.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Question> findByQuizId(int quizId) throws SQLException {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM questions WHERE quizId = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, quizId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    questions.add(new Question(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("question_text"),
                            resultSet.getInt("quizId")
                    ));
                }
            }
        }
        return questions;
    }

    public Question findById(int id) throws SQLException {
        String query = "SELECT * FROM question WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Question(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("question_text"),
                            resultSet.getInt("test_id")
                    );
                }
            }
        }
        return null;
    }

    public List<Question> findAll() throws SQLException {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM question";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                questions.add(new Question(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("question_text"),
                        resultSet.getInt("test_id")
                ));
            }
        }
        return questions;
    }

    public void update(Question question) throws SQLException {
        String query = "UPDATE questions SET title = ?, question_text = ?, quizId = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, question.getTitle());
            statement.setString(2, question.getQuestionText());
            statement.setInt(3, question.getQuizId());
            statement.setInt(4, question.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM questions WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public void assignToQuiz(int questionId, int quizId) throws SQLException {
        String query = "UPDATE question SET quiz_id = ? WHERE id = ?";

        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, quizId);
            statement.setInt(2, questionId);
            statement.executeUpdate();
        }
    }

    public boolean isCorrectAnswer(int questionId, String answerId) throws SQLException {
        String query = "SELECT is_correct FROM answer WHERE id = ? AND question_id = ?";
        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(answerId));
            statement.setInt(2, questionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("is_correct");
            }
            return false;
        }
    }

}
