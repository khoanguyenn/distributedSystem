package ChatServer;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ChatServerThread extends Thread {
    private Socket socket = null;
    private ChatServer chatServer = null;
    public DataInputStream inputStream;
    public DataOutputStream outputStream;
    private int ID;

    public ChatServerThread(ChatServer chatServer, Socket socket) {
        this.socket = socket;
        this.chatServer = chatServer;
        this.ID = socket.getPort();
    }

    public void run() {
        System.out.println("Server thread " + ID + " running...");
        while(true) {
            try {
                //Read the input
                String input = inputStream.readUTF();
                System.out.println("[ID] " + ID  + " : " + input);

                //Interrupts the loop when user hit bye
                if (input.equals("bye")) {
                    chatServer.chatServerThreadList.remove(this);
                    this.close();
                    break;
                }
                //Broadcasting message
                for (ChatServerThread chatServer : chatServer.chatServerThreadList) {
                    chatServer.broadCasting(this.ID, input);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
                break;
            }
        }

    }
    public void broadCasting(int senderID, String message) throws IOException {
        message = "[ID] " + senderID + ": " + message;
        this.outputStream.writeUTF(message);
        this.outputStream.flush();
    }
    public void open() throws IOException {
        inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    public void close() throws IOException {
        if (socket != null) {
            socket.close();
            System.out.println("Close the connection !");
        };
    }
}
