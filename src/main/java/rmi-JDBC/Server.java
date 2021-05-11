import javax.swing.plaf.nimbus.State;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.sql.*;

public class Server implements Summation {
    private Connection connection = null;
    public Server() {}

    public int sum(int n1, int n2) throws RemoteException{
        return n1+n2;
    }

    public int bookQuantity() throws RemoteException {
        connection = getConnection();
        try {
            PreparedStatement getQuantity = connection.prepareStatement("SELECT COUNT(*) FROM book");
            ResultSet resultSet = getQuantity.executeQuery();

            int bookQuantity = 0;
            if (resultSet.next()) {
                bookQuantity = resultSet.getInt(1);
            }

            return bookQuantity;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        } finally {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
    public int newspaperQuantity() throws RemoteException {
        connection = getConnection();
        try {
            PreparedStatement getQuantity = connection.prepareStatement("SELECT COUNT(*) FROM newspaper");
            ResultSet resultSet = getQuantity.executeQuery();

            int newspaperQuantity = 0;
            if (resultSet.next()) {
                newspaperQuantity = resultSet.getInt(1);
            }

            return newspaperQuantity;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        } finally {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
    public int add(Book book) throws RemoteException {
        connection = getConnection();
        try {
            PreparedStatement bookName = connection.prepareStatement(
                    "INSERT INTO media(media_name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            bookName.setString(1, book.getName());
            bookName.executeUpdate();
            ResultSet rs = bookName.getGeneratedKeys();

            //Get generated ID (integer)
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

            //Insert after have the generated ID
            PreparedStatement bookInsert = connection.prepareStatement("INSERT INTO book(media_id, author) VALUES (?,?)");
            bookInsert.setInt(1, generatedKey);
            bookInsert.setString(2, book.getAuthor());
            bookInsert.executeUpdate();

            System.out.println("New book added !");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return 0;
    }

    public int add(Newspaper newspaper) throws RemoteException {
        connection = getConnection();
        try {
            PreparedStatement newspaperAuthor = connection.prepareStatement(
                    "INSERT INTO media(media_name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            newspaperAuthor.setString(1, newspaper.getName());
            newspaperAuthor.executeUpdate();
            ResultSet rs = newspaperAuthor.getGeneratedKeys();

            //Get generated ID (integer)
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

            //Insert after have the generated ID
            PreparedStatement newspaperInsert = connection.prepareStatement("INSERT INTO newspaper(media_id, newspaper_type) VALUES (?,?)");
            newspaperInsert.setInt(1, generatedKey);
            newspaperInsert.setString(2, newspaper.getType());
            newspaperInsert.executeUpdate();

            System.out.println("New newspaper added !");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return 0;
    }
    public static void main(String args[]) {
        try {
            Server obj = new Server();
            Summation stub = (Summation)
			                 UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Summation", stub);

            System.err.println("Server ready");
            obj.getConnection();

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }


    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/distributedsystem", "root", "KhoANguyeN2020@");
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }
}
