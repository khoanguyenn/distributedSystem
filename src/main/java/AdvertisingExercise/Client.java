package AdvertisingExercise;

import AdvertisingExercise.model.Employee;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class Client {
    private String FILE_PATH = "src/main/java/AdvertisingExercise/data/employees.csv";
    public static void main(String[] args) {
        Client client = new Client();
        client.sendToServer();
    }
    private void sendToServer() {
        int serverPort = 1999;
        Socket socket = null;
        ObjectOutputStream toServer = null;
        ObjectInputStream fromServer = null;
        BufferedReader br = null;
        try {
            //Connects to server
            InetAddress serverHost = InetAddress.getByName("localhost");
            System.out.println("Connecting to server on port " + serverPort);
            socket = new Socket(serverHost, serverPort);
            System.out.println("Just connected to " + socket.getRemoteSocketAddress());

            //Read the CSV file
            List<Employee> employeeList = CSVUtil.readData(FILE_PATH);

            for (Employee employee : employeeList) {
                System.out.println(employee.toString());
            }

            //Send to server
            toServer = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            toServer.writeObject(employeeList);
            toServer.flush();

            // This will block until the corresponding ObjectOutputStream
            // in the server has written an object and flushed the header
            fromServer = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            String msgFromReply = (String) fromServer.readObject();
            System.out.println(msgFromReply);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
