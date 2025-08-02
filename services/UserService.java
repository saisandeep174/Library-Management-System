package services;

import db.DBConnection;
import models.User;

import java.sql.*;

public class UserService {
    public void registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users(name, email, user_type) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.name);
            stmt.setString(2, user.email);
            stmt.setString(3, user.userType);
            stmt.executeUpdate();
            System.out.println("User registered successfully.");
        }
    }
}
