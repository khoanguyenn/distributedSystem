package Peer2Peer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.net.UnknownHostException;


public class Client {
    private Socket socket = null;
    private String SERVER_URL = "localhost";
    private int PORT_NUMBER = 1999;
    ResultSet currentRes = null;

    private BufferedReader console = null;
    private ObjectOutputStream toServer = null;
    private ObjectInputStream fromServer = null;
    private DBConnector connector = null;

    public Client(DBConnector connector) {
        //Connection section
        try {
            System.out.println("Establishing connection. Please wait...");
            socket = new Socket(SERVER_URL, PORT_NUMBER);
            System.out.println("Connected: " + socket);
            this.connector = connector;

            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());

            StudentListWrapper studentList = new StudentListWrapper(convert2List(this.connector.getAll()));
            toServer.writeObject(studentList);

            //Sync data receive from server
            StudentListWrapper resFromServer = (StudentListWrapper) fromServer.readObject();

            List<Student> studentListServer = resFromServer.getList();
            syncData(studentListServer);

        } catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
        DBConnector connection_2 = new DBConnector("db2").setTable("student");
        Client client = new Client(connection_2);
    }
}
