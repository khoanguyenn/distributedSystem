package Peer2Peer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class DBConnector implements Serializable {
    private String tableName;
    private String DB_URL = "jdbc:mysql://localhost:3306/";
    private String USERNAME = "root";
    private String PASSWORD = "KhoANguyeN2020@";
    private Connection connection = null;
    public DBConnector(String databaseName) {
        this.DB_URL += databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public DBConnector setTable(String tableName) {
        this.tableName = tableName;
        return this;
    }
    public ResultSet getAll() {
            ResultSet res = null;
            try {
                Statement stmt = connection.createStatement();
                res = stmt.executeQuery("SELECT * FROM " + tableName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
			return res;
	}
    public void updateAll(List<Student> listOfStudent) {
        try {
            for (Student student : listOfStudent) {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("INSERT INTO " + 
                tableName + " (id, name, grade) VALUES (" + student.getId() + ", '" + student.getName() + "' ," + student.getGrade() + ")");   
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
