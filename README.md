# 📚 Library Management System

A console-based Library Management System built using **Java** and **MySQL**. This project helps manage books, users, and transactions such as issuing and returning books. Designed to demonstrate core concepts of RDBMS, JDBC, and object-oriented programming.

---

## 🚀 Features

- ➕ Add new books
- 🔍 Search books by title
- 👤 Register new users (students or faculty)
- 📚 Issue books (with due date calculation)
- ✅ Return books (handles overdue)
- 📦 MySQL backend with indexing and normalization
- 💬 Clean, menu-based command-line interface

---

## 🧰 Tech Stack

- **Language**: Java (JDK 8+)
- **Database**: MySQL
- **Connector**: MySQL Connector/J (`mysql-connector-j-9.4.0.jar`)
- **Tools**: JDBC, Console Input/Output

---

## 🗂️ Project Structure
LibraryManagementSystem/
├── db/
│ └── DBConnection.java
├── models/
│ ├── Book.java
│ ├── User.java
│ └── Transaction.java
├── services/
│ ├── BookService.java
│ ├── UserService.java
│ └── TransactionService.java
├── mysql-connector-j-9.4.0.jar
├── Main.java
└── README.md

