package com.testapp.dao;

import com.testapp.models.Test;
import com.testapp.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDAO extends AbstractDAO {

    public int save(Test test) throws SQLException {
        String query = "INSERT INTO quiz (title) VALUES (?) RETURNING id";

        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, test.getTitle());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                throw new SQLException("Quiz ID not generated.");
            }
        }
    }

    public Test findById(int id) throws SQLException {
        String query = "SELECT * FROM quizzes WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Test(resultSet.getInt("id"), resultSet.getString("title"));
                }
            }
        }
        return null;
    }

    public List<Test> findAll() throws SQLException {
        List<Test> tests = new ArrayList<>();
        String query = "SELECT * FROM quizzes";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt("id"), resultSet.getString("title")));
            }
        }
        return tests;
    }

    public void update(Test quiz) throws SQLException {
        String query = "UPDATE quizzes SET title = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, quiz.getTitle());
            statement.setInt(2, quiz.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM quizzes WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
