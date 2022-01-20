package com.github.bashdi.butter.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDatabase {

    Connection connection;

    public abstract Connection getConnection() throws SQLException;

    protected Connection getConnection(String connectionString) throws SQLException {

        if (this.connection == null || this.connection.isClosed()) {
            this.connection = DriverManager.getConnection(connectionString);
        }

        return this.connection;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {

        }

    }
}
