package models;

public class Book {
    public int id;
    public String title;
    public String author;
    public String genre;
    public int totalCopies;
    public int availableCopies;

    public Book(String title, String author, String genre, int totalCopies) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }
}
