package ChatServer;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ChatClient {
    private Socket socket = null;
    private String serverName = "localhost";
    private int serverPort = 1999;

    private BufferedReader console = null;
    private DataOutputStream outputStream = null;
    private DataInputStream inputStream = null;

    public ChatClient() {
        //Connection section
        try {
            System.out.println("Establishing connection. Please wait...");
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);

            console = new BufferedReader(new InputStreamReader(System.in));
            outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (UnknownHostException unknownHostException) {
            System.out.println("Host unknown: " + unknownHostException.getMessage());
        } catch (IOException ioException) {
            System.out.println("Unexpected exception: " + ioException.getMessage());
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                String line = "";
                while(!line.equals("bye")) {
                    try {
                        line = console.readLine();
                        outputStream.writeUTF(line);
                        outputStream.flush();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                try {
                    socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        if (!socket.isClosed()) {
                            System.out.println(inputStream.readUTF());
                        }
                    } catch (SocketException socketException) {
                        System.out.println("Close the connection!");
                        break;
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                        break;
                    }
                }
            }
        }).start();
    }
    public static void main(String[] args) {
        new ChatClient();
    }
}
