package models;

import java.time.LocalDate;

public class Transaction {
    public int id;
    public int userId;
    public int bookId;
    public LocalDate issueDate;
    public LocalDate dueDate;
    public LocalDate returnDate;
    public String status;
}
