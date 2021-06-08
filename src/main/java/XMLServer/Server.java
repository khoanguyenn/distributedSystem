import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Student;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket socketServer = new ServerSocket(9999);
			System.out.println("I'm a server. I'm listening on the port 9999.");
			/* now listen for connections */

			Socket sock = socketServer.accept();

			// receive data from client
			DataInputStream dis = new DataInputStream(sock.getInputStream());
			// send to client
			DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

			Document document = Object2Doc.convertXMLString2Doc(dis.readUTF());
            NodeList studentAttributes = document.getElementsByTagName("student");

            Node node = studentAttributes.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                int studentID = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                String studentName = element.getElementsByTagName("name").item(0).getTextContent();
                double studentGrade = Double.parseDouble(element.getElementsByTagName("grade").item(0).getTextContent());
                Student student = new Student(studentID, studentName, studentGrade);
                System.out.println(student.getId() + " :" + student.getName() + " :" + student.getGrade());
            }

			// close
			dis.close();
			dos.close();
			socketServer.close();
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
	}

}