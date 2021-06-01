package XMLReader;

import java.io.File;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import XMLReader.model.Author;
import XMLReader.model.Book;

public class Object2Doc {
	public static String convertDoc2XmlString(Document doc) {
		String output = "";
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			output = writer.getBuffer().toString();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	public static Document convertObject2Doc(Book book) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();

		Element root = doc.createElement("books");
		doc.appendChild(root);

		root.appendChild(createBook(doc, book));

        return doc;
	}

	private static Node createBook(Document doc, Book book) {
		Element nodeBook = doc.createElement("book");
		
		Element titleNode = doc.createElement("title");
		titleNode.appendChild(doc.createTextNode(book.getTitle()));

		Element publisherNode = doc.createElement("publisher");
		publisherNode.appendChild(doc.createTextNode(book.getPublisher()));
		
		Element authorNode = doc.createElement("author");

        Element authorName = doc.createElement("name");
        authorName.appendChild(doc.createTextNode(book.getAuthor().getName()));

        Element authorAge = doc.createElement("age");
        authorAge.appendChild(doc.createTextNode(book.getAuthor().getAge() + ""));
		
        //Append author node
        authorNode.appendChild(authorName);
        authorNode.appendChild(authorAge);

        //Append book node
        nodeBook.appendChild(titleNode);
        nodeBook.appendChild(publisherNode);
        nodeBook.appendChild(authorNode);
		
		return nodeBook;
	}

	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        Book book = new Book("Java sourcecode", "Khoa", new Author("Khoa Nguyen", 20));
        Document doc = convertObject2Doc(book);
        String XMLString = convertDoc2XmlString(doc);
        System.out.println(XMLString);
	}
}
