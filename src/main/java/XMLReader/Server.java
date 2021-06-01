package XMLReader;

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

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket socketServer = new ServerSocket(9999);
			System.out.println("I'm a server. I'm listening on the port 9999.");
			/* now listen for connections */

			Socket sock = socketServer.accept();

			// receive data from client
			DataInputStream dis = new DataInputStream(sock.getInputStream());
			System.out.println(dis.readUTF());
			// send to client
			DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

			String dataFromClient = dis.readUTF();
			System.out.println("From client: " + dataFromClient);
			dos.writeUTF("Hi");

			// close
			dis.close();
			dos.close();
			socketServer.close();
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
	}
}
