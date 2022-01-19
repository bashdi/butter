package com.github.bashdi.butter.database;

import java.sql.Connection;
import java.sql.SQLException;

public class H2Database extends AbstractDatabase{

    final String CONNECTION_STRING_TEMPLATE = "jdbc:h2:file:~/{filename};";
    String connectionString;



    public H2Database(String filename) throws ClassNotFoundException {
        //Class.forName( "org.hsqldb.jdbcDriver" );
        connectionString = CONNECTION_STRING_TEMPLATE.replace("{filename}", filename);
    }



    @Override
    public Connection getConnection() throws SQLException {
        return getConnection(connectionString);
    }


}
