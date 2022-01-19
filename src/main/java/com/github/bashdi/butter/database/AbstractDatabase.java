package com.github.bashdi.butter.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDatabase {

    public abstract Connection getConnection() throws SQLException;

    Connection getConnection(String connectionString) throws SQLException {
        Connection connection = DriverManager.getConnection(connectionString);

        return connection;
    }
}
