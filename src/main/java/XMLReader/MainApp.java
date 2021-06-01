package XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import XMLReader.model.Author;
import XMLReader.model.Book;

public class MainApp {
    public static void main(String[] args) {

        try {        
            Book book = new Book("Java sourcecode", "Khoa", new Author("Khoa Nguyen", 20));
            Document doc = Object2Doc.convertObject2Doc(book);
            String XMLString = Object2Doc.convertDoc2XmlString(doc);
            System.out.println(XMLString);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

    }
}