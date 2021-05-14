import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GameClient {
    public static void main(String[] args) {
        String host = "localhost";
        try {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            Registry registry = LocateRegistry.getRegistry(host);
            GameService stub = (GameService) registry.lookup("TicTacToe");

            String username = "";
            String password = "";
            int thisPlayer;
            do {
                System.out.println("Enter your username: ");
                username = console.readLine();
                System.out.println("Enter your password: ");
                password = console.readLine();
            }
            while ((thisPlayer = stub.gameAuth(username, password)) == 0);
            System.out.println("Authentication successful!");


            String symbol = (thisPlayer == 1) ? "X" : "O";
            System.out.println(symbol + " is connected !");

            while (true) {
                if (stub.isWin(symbol)) {
                    System.out.println("You win!");
                    break;
                }
                if (stub.isLose(symbol)) {
                    System.out.println("You lose!");
                    break;
                }
                if (stub.isDraw()) {
                    System.out.println("Both draw!");
                    break;
                }

                if (stub.getCurrentPlayer().equals(symbol)) {
                        if (stub.isWin(symbol)) break;
                        if (stub.isLose(symbol)) break;
                        if (stub.isDraw()) break; 
                    try {
                        System.out.println(stub.printBoard());
                        System.out.println("Your turn now!");
    
                        System.out.print("Enter x coordinate: ");
                        int xCoord = Integer.parseInt(console.readLine());
                        System.out.print("Enter y coordinate: ");
                        int yCoord = Integer.parseInt(console.readLine());
                        System.out.println();
                        stub.setMove(xCoord, yCoord, symbol);
                        System.out.println(stub.printBoard());
                        System.out.println("Wait for you opponent...");
                    } catch(IOException ioException) {
                        System.out.println(ioException.getMessage());
                    } catch(NumberFormatException numberFormatException) {
                        System.out.println(numberFormatException.getMessage());
                    }

                }
            }
        } catch (IOException ioe) {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
