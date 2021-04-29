package ChatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    ServerSocket serverSocket = null;
    ChatServerThread client = null;
    protected List<ChatServerThread> chatServerThreadList = new ArrayList<ChatServerThread>();

    public ChatServer(int port) {
        try {
            //Open connection on the given port
            System.out.println("Binding to " + port + ", please wait...");
            serverSocket = new ServerSocket(port);
            System.out.println("Server started: " + serverSocket);

            //Listening incoming connections
            while(true) {
                System.out.println("Waiting for connection...");
                addThread(serverSocket.accept());
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void addThread(Socket socket) {
        System.out.println("Client accepted: " + socket);
        client = new ChatServerThread(this, socket);
        chatServerThreadList.add(client);
        try {
            client.open();
            client.start();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer(1999);
    }
}
