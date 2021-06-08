import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import model.Student;

public class Object2Doc {
	public static Document convertXMLString2Doc(String XMLString) {
		Document document = null;
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			ByteArrayInputStream input = new ByteArrayInputStream(XMLString.getBytes("UTF-8"));
			document = builder.parse(input);
			return document;
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
	}
    public static Document convertObject2Doc(Student student) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();

		Element root = doc.createElement("books");
		doc.appendChild(root);

		root.appendChild(createStudent(doc, student));

        return doc;
	}
    private static Node createStudent(Document doc, Student student) {
		Element nodeBook = doc.createElement("student");
		
		Element studentID = doc.createElement("id");
		studentID.appendChild(doc.createTextNode(Integer.toString(student.getId())));

		Element studentName = doc.createElement("name");
		studentName.appendChild(doc.createTextNode(student.getName()));
		
        Element studentGrade = doc.createElement("grade");
        studentGrade.appendChild(doc.createTextNode(Double.toString(student.getGrade())));

        //Append book node
        nodeBook.appendChild(studentID);
        nodeBook.appendChild(studentName);
        nodeBook.appendChild(studentGrade);
        
        return nodeBook;
	}
    public static String convertDoc2XmlString(Document document) {
		String output = "";
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(document), new StreamResult(writer));
			output = writer.getBuffer().toString();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		return output;
	}
}