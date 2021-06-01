package XMLReader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import XMLReader.model.Author;
import XMLReader.model.Book;

public class Client {
	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		try {
			/* make connection to server socket */
			Socket sock = new Socket("127.0.0.1", 9999);
			Book book = new Book("Java sockets", "VGU", new Author("Khoa Nguyen", 20));
			// send data to server
			DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
			dos.writeUTF(book.toString());

			// receive data from server
			DataInputStream dis = new DataInputStream(sock.getInputStream());

			dos.writeUTF("Hello!");
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
