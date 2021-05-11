import java.io.Serializable;

public class Book extends Media implements Serializable {
    private String author;

    public Book(String name, String author) {
        super(name);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
