package org.preethi.lib;

public class Book {
    private int id;
    private String title;
    private String author;
    private String publicationDate;
    private String genre;
    private boolean available;

    public Book(int id, String title, String author, String publicationDate, String genre, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.genre = genre;
        this.available = available;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublicationDate() { return publicationDate; }
    public String getGenre() { return genre; }
    public boolean getAvailable() { return available; }
}
