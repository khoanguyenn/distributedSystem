package com.wsexample.wsdemo;

public class Book {
    private String title;
    private String publisher;
    private Author author;

    public Book(String title, String publisher, Author author) {
        this.title = title;
        this.publisher = publisher;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public Author getAuthor() {
        return author;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        result.append(" Title: ");
        result.append(title);
        result.append(" Publisher: ");
        result.append(publisher);
        result.append(" | ");
        result.append(author.toString());
        return result.toString();
    }
}
