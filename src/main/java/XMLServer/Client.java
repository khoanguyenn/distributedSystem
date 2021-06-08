import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import model.Student;


public class Client {
	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		try {
			/* make connection to server socket */
			Socket sock = new Socket("127.0.0.1", 9999);
			// send data to server
			DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

			// receive data from server
			DataInputStream dis = new DataInputStream(sock.getInputStream());

            try {
                //Create document
                Document document = Object2Doc.convertObject2Doc(new Student(4, "Khoa", 2.0));
                String resultText = Object2Doc.convertDoc2XmlString(document);
                dos.writeUTF(resultText);
            } catch (ParserConfigurationException exception) {
                exception.printStackTrace();
            }
            
			String dataFromServer = dis.readUTF();
			System.out.println("From Server: " + dataFromServer);

			/* close the socket connection */
			dis.close();
			dos.close();
			sock.close();
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
	}
}