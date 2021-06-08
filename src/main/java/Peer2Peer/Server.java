package Peer2Peer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Server {
    ServerSocket serverSocket = null;
    DBConnector connector = null;
    ResultSet currentRes = null;
    ObjectOutputStream toClient = null;
    ObjectInputStream fromClient = null;
    private int PORT_NUMBER = 1999;
    public Server(DBConnector connector) {
        try {
            //Open connection on the given port
            System.out.println("Binding to " + PORT_NUMBER + ", please wait...");
            serverSocket = new ServerSocket(PORT_NUMBER);
            System.out.println("Server started: " + serverSocket);

            //Listening incoming connections
            System.out.println("Waiting for connection...");
            Socket socket = serverSocket.accept();
            System.out.println("Connected !" + serverSocket);
            this.connector = connector;
            
            toClient = new ObjectOutputStream(socket.getOutputStream());
            fromClient = new ObjectInputStream(socket.getInputStream());
            StudentListWrapper resFromClient = (StudentListWrapper) fromClient.readObject();

            List<Student> studentList = resFromClient.getList();
            syncData(studentList);

            //Send data to Client
            StudentListWrapper studentListClient = new StudentListWrapper(convert2List(this.connector.getAll()));
            toClient.writeObject(studentListClient);

        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
    }
    public ResultSet connectDB() {
        return this.currentRes = connector.getAll();
    }
    private List<Student> convert2List(ResultSet resultSet) {
        List<Student> studentList = new ArrayList<Student>();
        try {
            while(resultSet.next()) {
                int studentID = resultSet.getInt(1);
                String studentName = resultSet.getString(2);
                double studentPoint = resultSet.getDouble(3);
                
                studentList.add(new Student(studentID, studentName, studentPoint));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return studentList;
    }
    private void syncData(List<Student> listOfStudent) {
        connector.updateAll(listOfStudent);
    }
    public static void main(String[] args) {
        DBConnector connection_1 = new DBConnector("db1").setTable("student");
        Server server = new Server(connection_1);
    }
}