import javax.swing.plaf.IconUIResource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class GameClient {
    public static Scanner in = new Scanner(System.in); // the input Scanner
    public static void main(String[] args) {
        String host = "localhost";
        try {
            // get data from users
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            Registry registry = LocateRegistry.getRegistry(host);
            GamePlay stub = (GamePlay) registry.lookup("TicTacToe");
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

            stub.initGame();
            do {

                int currentPlayer = stub.getCurrentPlayer();
                // Print message if game-over
                if (stub.getCurrentState() == stub.CROSS_WON) {
                    System.out.println("'X' won! Bye!");
                    break;
                } else if (stub.getCurrentState() == stub.NOUGHT_WON) {
                    System.out.println("'O' won! Bye!");
                    break;
                } else if (stub.getCurrentState() == stub.DRAW) {
                    System.out.println("It's a Draw! Bye!");
                    break;
                }
                if (thisPlayer == currentPlayer) {
                    System.out.println(stub.printBoard());
                    System.out.println("Your turn now!");
                    int row = in.nextInt() - 1;  // array index starts at 0 instead of 1
                    int col = in.nextInt() - 1;
                    stub.playerMove(thisPlayer, row, col);
                    stub.updateGame(thisPlayer);
                    System.out.println(stub.printBoard());
                    // Switch player
                    stub.setCurrentPlayer((currentPlayer == stub.CROSS) ? stub.NOUGHT : stub.CROSS);
                    System.out.println("Waiting for your opponent...");
                }
            } while (stub.getCurrentState() == 0);

        } catch (IOException ioe) {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
        catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
