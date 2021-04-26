package AdvertisingExercise;

import AdvertisingExercise.model.Employee;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
	public static void main(String[] args) {
		int serverPort = 1999;
		ServerSocket serverSocket = null;
		ObjectOutputStream toClient = null;
		ObjectInputStream fromClient = null;
		try {
			serverSocket = new ServerSocket(serverPort);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Just connected to " + socket.getRemoteSocketAddress());

				toClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				fromClient = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

				List<Employee> msgRequest = (List<Employee>) fromClient.readObject();

				if (msgRequest != null)
					toClient.writeObject("Well received!");
				else
					toClient.writeObject("Something wrong...");
				toClient.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
