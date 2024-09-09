package com.gardenplanner.gardenplanner;

import java.sql.*;

public class UserDAO {
    private final Connection connection;

    public UserDAO() {
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() throws SQLException {
        Statement createTable = connection.createStatement();
        createTable.execute(
                "CREATE TABLE IF NOT EXISTS users ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "username TEXT NOT NULL, "
                        + "email TEXT NOT NULL, "
                        + "password TEXT NOT NULL"
                        + ")"
        );
    }

    public void insert(User user) throws SQLException {
        PreparedStatement insertUser = connection.prepareStatement(
                "INSERT INTO users (username, email, password) VALUES (?, ?, ?)"
        );
        insertUser.setString(1, user.username());
        insertUser.setString(2, user.email());
        insertUser.setString(3, user.hashedPassword());
        insertUser.execute();
    }

    public User getUserByUsername(String username) throws SQLException {
        PreparedStatement getUser = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
        getUser.setString(1, username);
        ResultSet rs = getUser.executeQuery();

        if (rs.next()) {
            return new User(
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password")
            );
        }
        return null;
    }
}
