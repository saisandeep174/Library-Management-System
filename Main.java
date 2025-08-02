import models.Book;
import models.User;
import services.BookService;
import services.UserService;
import services.TransactionService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        BookService bookService = new BookService();
        UserService userService = new UserService();
        TransactionService txService = new TransactionService();

        while (true) {
            System.out.println("\n1. Add Book\n2. Search Book\n3. Register User\n4. Issue Book\n5. Return Book\n6. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Title: "); String title = sc.nextLine();
                    System.out.print("Author: "); String author = sc.nextLine();
                    System.out.print("Genre: "); String genre = sc.nextLine();
                    System.out.print("Copies: "); int copies = sc.nextInt();
                    bookService.addBook(new Book(title, author, genre, copies));
                    break;

                case 2:
                    System.out.print("Search: ");
                    bookService.searchBook(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Email: "); String email = sc.nextLine();
                    System.out.print("Type (student/faculty): "); String type = sc.nextLine();
                    userService.registerUser(new User(name, email, type));
                    break;

                case 4:
                    System.out.print("User ID: "); int userId = sc.nextInt();
                    System.out.print("Book ID: "); int bookId = sc.nextInt();
                    txService.issueBook(userId, bookId);
                    break;

                case 5:
                    System.out.print("User ID: "); userId = sc.nextInt();
                    System.out.print("Book ID: "); bookId = sc.nextInt();
                    txService.returnBook(userId, bookId);
                    break;

                case 6:
                    System.exit(0);
            }
        }
    }
}
