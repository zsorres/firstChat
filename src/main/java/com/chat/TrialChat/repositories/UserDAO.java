package com.chat.TrialChat.repositories;

import com.chat.TrialChat.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
        private final String jdbcURL;
        private final String jdbcUsername;
        private final String jdbcPassword;
        private Connection jdbcConnection;

        public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
            this.jdbcURL = jdbcURL;
            this.jdbcUsername = jdbcUsername;
            this.jdbcPassword = jdbcPassword;
        }

        protected void connect() throws SQLException {
            if (jdbcConnection == null || jdbcConnection.isClosed()) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    throw new SQLException(e);
                }
                jdbcConnection = DriverManager.getConnection(
                        jdbcURL, jdbcUsername, jdbcPassword);
            }
        }

        protected void disconnect() throws SQLException {
            if (jdbcConnection != null && !jdbcConnection.isClosed()) {
                jdbcConnection.close();
            }
        }

        public boolean insertUser(User user) throws SQLException {
            String sql = "INSERT INTO user (user_id, username) VALUES (?, ?)";
            connect();

            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, user.getUser_id());
            statement.setString(2, user.getUsername());

            boolean rowInserted = statement.executeUpdate() > 0;
            statement.close();
            disconnect();
            return rowInserted;
        }

        public List<User> listAllUsers() throws SQLException {
            List<User> listUser = new ArrayList<>();

            String sql = "SELECT * FROM user";

            connect();

            Statement statement = jdbcConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String username = resultSet.getString("username");

                User user = new User(user_id, username);
                listUser.add(user);
            }

            resultSet.close();
            statement.close();

            disconnect();

            return listUser;
        }

        public boolean deleteUser(User user) throws SQLException {
            String sql = "DELETE FROM user where user_id = ?";

            connect();

            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, user.getUser_id());

            boolean rowDeleted = statement.executeUpdate() > 0;
            statement.close();
            disconnect();
            return rowDeleted;
        }

        public boolean updateUser(User user) throws SQLException {
            String sql = "UPDATE user SET username = ?,";
            sql += " WHERE user_id = ?";
            connect();

            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setInt(2, user.getUser_id());

            boolean rowUpdated = statement.executeUpdate() > 0;
            statement.close();
            disconnect();
            return rowUpdated;
        }

        public User getUser(int id) throws SQLException {
            User user = null;
            String sql = "SELECT * FROM user WHERE user_id = ?";

            connect();

            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("username");

                user = new User(username);
            }

            resultSet.close();
            statement.close();

            return user;
        }
}
