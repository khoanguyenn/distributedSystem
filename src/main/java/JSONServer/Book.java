package JSONServer;

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
        return this.title;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public Author getAuthor() {
        return this.author;
    }
}
