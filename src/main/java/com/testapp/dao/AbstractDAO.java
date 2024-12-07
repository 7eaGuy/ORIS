package com.testapp.dao;

import com.testapp.util.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDAO {
    protected Connection getConnection() throws SQLException {
        return DatabaseConnectionUtil.getConnection();
    }
}
