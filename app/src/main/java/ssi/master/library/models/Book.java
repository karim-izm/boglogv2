package ssi.master.library.models;

public class Book {
    private int id;
    private String isbn;
    private String title;
    private String author;
    private String category;

    public Book() {}

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public Book(String isbn, String title, String category,  String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public Book(int id, String isbn, String title, String category,  String author) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
