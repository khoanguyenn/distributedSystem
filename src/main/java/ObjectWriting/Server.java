package ObjectWriting;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

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

				Student msgRequest = (Student) fromClient.readObject();
				int id = msgRequest.getId();
				String fullName = msgRequest.getFullName();
				String studentYear = msgRequest.getYear();
				System.out.println("ObjectWriting.Student year: " + studentYear);

				Student studentObject = new Student(id, fullName, studentYear);

				System.out.println("Insert to database: " + insertStudent(studentObject));
				toClient.writeObject(studentObject);
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
	public static boolean insertStudent(Student student) {
		// for insert a new candidate
		ResultSet rs = null;
		int candidateId = 0;

		String sql = "INSERT INTO student(id, fullName, studyYear) VALUES(?,?,?)";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/distributesocket", "root", "KhoANguyeN2020@")
		) {
			PreparedStatement pstmt = conn.prepareStatement(sql);

				// set parameters for statement
				pstmt.setInt(1, student.getId());
				pstmt.setString(2, student.getFullName());
				pstmt.setString(3, student.getYear());

				return pstmt.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				if(rs != null)  rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
