import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {
    private Client() {}

    public static void main(String[] args) {
        String host = "localhost";
        try {
			// get data from users
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
			
            Registry registry = LocateRegistry.getRegistry(host);
            Summation stub = (Summation) registry.lookup("Summation");
			
			while (true) {
				System.out.println("Please enter your option! \n" +
						"[1]: for entering new book \n" +
						"[2]: for entering new newspaper\n" +
						"[3]: for quantity of book \n" +
						"[4]: for quantity of newspaper \n" +
						"[5]: for exit");
				int option = Integer.parseInt(console.readLine());
				switch (option) {
					case 1:
						System.out.println("Enter book name: ");
						String bookName = console.readLine();
						System.out.println("Enter book author: ");
						String bookAuthor = console.readLine();
						Book newBook = new Book(bookName, bookAuthor);
						stub.add(newBook);
						break;
					case 2:
						System.out.println("Enter newspaper name: ");
						String newspaperName = console.readLine();
						System.out.println("Enter newspaper type: ");
						String newspaperType = console.readLine();
						Newspaper newspaper = new Newspaper(newspaperName, newspaperType);
						stub.add(newspaper);
						break;
					case 3:
						System.out.println("Number of books: " + stub.bookQuantity());
						break;
					case 4:
						System.out.println("Number of newspaper: " + stub.newspaperQuantity());
						break;
					case 5:
						System.exit(0);
					default:
						System.out.println("Please enter a correct option!");
						break;
				}
			}
        } catch (IOException ioe) {
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}		
		catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

