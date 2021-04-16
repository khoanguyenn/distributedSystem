import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.*;

public class Client {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

	private void prepareGUI() {

		mainFrame = new JFrame("Vi du Java Swing");
		mainFrame.setSize(500, 300);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		headerLabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);
		statusLabel.setSize(350, 100);
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}
	private void showTextFieldDemo() {
		headerLabel.setText("Control in action: JTextField");
		JLabel idLabel = new JLabel("Student ID: ", JLabel.LEFT);
		final JTextField idText = new JTextField(6);
		JLabel nameLabel = new JLabel("Student name: ", JLabel.LEFT);
		final JTextField nameText = new JTextField(6);
		JLabel yearLabel = new JLabel("Student year: ", JLabel.LEFT);
		final JTextField yearText = new JTextField(6);
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = new Student(Integer.parseInt(idText.getText()), nameText.getText(), yearText.getText());
				sendToServer(student);
			}
		});
		controlPanel.add(idLabel);
		controlPanel.add(idText);

		controlPanel.add(nameLabel);
		controlPanel.add(nameText);
		controlPanel.add(yearLabel);
		controlPanel.add(yearText);
		controlPanel.add(submitButton);
		mainFrame.setVisible(true);
	}
	Client() {
		prepareGUI();
	}
	public static void main(String[] args) {
		Client swingDemo = new Client();
		swingDemo.showTextFieldDemo();


	}
	private void sendToServer(Student student) {
		int serverPort = 1999;
		Socket socket = null;
		ObjectOutputStream toServer = null;
		ObjectInputStream fromServer = null;
		BufferedReader br = null;
		System.out.println("Client is on!");
		try {
			InetAddress serverHost = InetAddress.getByName("localhost");
			System.out.println("Connecting to server on port " + serverPort);
			socket = new Socket(serverHost, serverPort);
			System.out.println("Just connected to " + socket.getRemoteSocketAddress());

			toServer = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			Student msgToSend = new Student(student.getId(), student.getFullName(), student.getYear());

			toServer.writeObject(msgToSend);
			toServer.flush();

			// This will block until the corresponding ObjectOutputStream
			// in the server has written an object and flushed the header
			fromServer = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			Student msgFromReply = (Student) fromServer.readObject();
			System.out.println(msgFromReply.getId() + " - " + msgFromReply.getFullName() + " - " + msgFromReply.getYear());
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
