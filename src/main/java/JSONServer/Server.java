package JSONServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import JSONServer.JSONReader;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket socketServer = new ServerSocket(9999);
			System.out.println("I'm a server. I'm listening on the port 9999.");
			/* now listen for connections */

			Socket sock = socketServer.accept();
            Book tempBook = new Book("Khoa booking", "KhoaNguyen", new Author("Khoa Nguyen Dang", 20));

            String message = JSONReader.object2JsonString(tempBook);
			// receive data from client
			DataInputStream dis = new DataInputStream(sock.getInputStream());
			// send to client
			DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
            dos.writeUTF(message);
			// close
			dis.close();
			dos.close();
			socketServer.close();
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
	}

}