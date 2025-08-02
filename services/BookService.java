package services;

import db.DBConnection;
import models.Book;

import java.sql.*;

public class BookService {
    public void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO books(title, author, genre, total_copies, available_copies) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.title);
            stmt.setString(2, book.author);
            stmt.setString(3, book.genre);
            stmt.setInt(4, book.totalCopies);
            stmt.setInt(5, book.availableCopies);
            stmt.executeUpdate();
            System.out.println("Book added successfully.");
        }
    }

    public void searchBook(String keyword) throws SQLException {
        String sql = "SELECT * FROM books WHERE title LIKE ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("book_id") + ", Title: " + rs.getString("title"));
            }
        }
    }
}
