package services;

import db.DBConnection;

import java.sql.*;
import java.time.LocalDate;

public class TransactionService {
    public void issueBook(int userId, int bookId) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            String check = "SELECT available_copies FROM books WHERE book_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(check);
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                LocalDate issueDate = LocalDate.now();
                LocalDate dueDate = issueDate.plusDays(7);

                String insert = "INSERT INTO transactions(user_id, book_id, issue_date, due_date, status) VALUES (?, ?, ?, ?, 'issued')";
                PreparedStatement insertStmt = conn.prepareStatement(insert);
                insertStmt.setInt(1, userId);
                insertStmt.setInt(2, bookId);
                insertStmt.setDate(3, Date.valueOf(issueDate));
                insertStmt.setDate(4, Date.valueOf(dueDate));
                insertStmt.executeUpdate();

                String update = "UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(update);
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();

                conn.commit();
                System.out.println("Book issued.");
            } else {
                System.out.println("Book unavailable.");
            }
        }
    }

    public void returnBook(int userId, int bookId) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String find = "SELECT transaction_id, due_date FROM transactions WHERE user_id=? AND book_id=? AND status='issued'";
            PreparedStatement findStmt = conn.prepareStatement(find);
            findStmt.setInt(1, userId);
            findStmt.setInt(2, bookId);
            ResultSet rs = findStmt.executeQuery();

            if (rs.next()) {
                int transactionId = rs.getInt("transaction_id");
                LocalDate dueDate = rs.getDate("due_date").toLocalDate();
                LocalDate returnDate = LocalDate.now();
                String status = returnDate.isAfter(dueDate) ? "overdue" : "returned";

                String updateTx = "UPDATE transactions SET return_date=?, status=? WHERE transaction_id=?";
                PreparedStatement updateStmt = conn.prepareStatement(updateTx);
                updateStmt.setDate(1, Date.valueOf(returnDate));
                updateStmt.setString(2, status);
                updateStmt.setInt(3, transactionId);
                updateStmt.executeUpdate();

                String updateBook = "UPDATE books SET available_copies = available_copies + 1 WHERE book_id = ?";
                PreparedStatement bookStmt = conn.prepareStatement(updateBook);
                bookStmt.setInt(1, bookId);
                bookStmt.executeUpdate();

                System.out.println("Book returned. Status: " + status);
            } else {
                System.out.println("No issued book found for return.");
            }
        }
    }
}
