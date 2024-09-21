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

    public User getUser(String username) throws SQLException {
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

    public void updatePassword(String username, String hashedPassword) throws SQLException {
        String updatePasswordSQL = "UPDATE users SET password = ? WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updatePasswordSQL);
        preparedStatement.setString(1, hashedPassword);
        preparedStatement.setString(2, username);
        preparedStatement.executeUpdate();
    }

    public int returnID(String username) throws SQLException {
        PreparedStatement returnID = connection.prepareStatement("SELECT id FROM users WHERE username = ?");
        returnID.setString(1, username);
        ResultSet rs = returnID.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
        return -1;
    }
}
