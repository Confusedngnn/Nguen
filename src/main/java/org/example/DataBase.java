package org.example;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    public static void create(ArrayList<Forbes> forbes) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        var connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        var s = connection.createStatement();
        s.execute("DROP TABLE IF EXISTS 'Forbes';");
        s.execute("CREATE TABLE IF NOT EXISTS 'Forbes' " +
                "('id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'rang' int, " +
                "'name' varchar(100)," +
                "'netWorth' real, " +
                "'age' int, " +
                "'country' varchar(100), " +
                "'source' varchar(100), " +
                "'industry' varchar(100));");
        var insertPreparedStatement = connection.prepareStatement("INSERT INTO 'Forbes' " +
                "('rang','name','netWorth','age','country','source', 'industry') " +
                "VALUES (?,?,?,?,?,?,?) ;");
        for (var data : forbes)
            data.db(insertPreparedStatement);
        connection.close();
    }

    public static ResultSet executeQuery(String query) throws SQLException, ClassNotFoundException {
        return getStatement().executeQuery(query);
    }

    public static Statement getStatement() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        var connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        return connection.createStatement();
    }
}
