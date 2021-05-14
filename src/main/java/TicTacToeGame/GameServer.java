import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.Scanner;

public class GameServer implements GamePlay {


    // The game board and the game status
    public static final int ROWS = 3, COLS = 3; // number of rows and columns
    public static int[][] board = new int[ROWS][COLS]; // game board in 2D array
    //  containing (EMPTY, CROSS, NOUGHT)
    public int currentState;  // the current state of the game
    // (PLAYING, DRAW, CROSS_WON, NOUGHT_WON)
    public int currentPlayer; // the current player (CROSS or NOUGHT)
    public int currentRow, currentCol; // current seed's row and column

    public static int MAX_PLAYER = 2;
    public static Scanner in = new Scanner(System.in); // the input Scanner

    public GameServer() {

    }

    public void initGame() throws RemoteException {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                board[row][col] = EMPTY;  // all cells empty
            }
        }
        currentState = PLAYING; // ready to play
        currentPlayer = CROSS;  // cross plays first
    }

    public void playerMove(int theSeed, int row, int col) throws RemoteException {
        boolean validInput = false;  // for input validation
        do {
            if (row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == EMPTY) {
                board[row][col] = theSeed;  // update game-board content
                validInput = true;  // input okay, exit loop
            } else {
                System.out.println("This move at (" + (row + 1) + "," + (col + 1)
                        + ") is not valid. Try again...");
            }
        } while (!validInput);  // repeat until input is valid
    }


    public void updateGame(int theSeed) throws RemoteException  {
        if (hasWon(theSeed, currentRow, currentCol)) {  // check if winning move
            currentState = (theSeed == CROSS) ? CROSS_WON : NOUGHT_WON;
        } else if (isDraw()) {  // check for draw
            currentState = DRAW;
        }
        // Otherwise, no change to currentState (still PLAYING).
    }

    public boolean isDraw() throws RemoteException  {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (board[row][col] == EMPTY) {
                    return false;  // an empty cell found, not draw, exit
                }
            }
        }
        return true;  // no empty cell, it's a draw
    }

    public boolean hasWon(int theSeed, int currentRow, int currentCol) throws RemoteException {
        return (board[currentRow][0] == theSeed         // 3-in-the-row
                && board[currentRow][1] == theSeed
                && board[currentRow][2] == theSeed
                || board[0][currentCol] == theSeed      // 3-in-the-column
                && board[1][currentCol] == theSeed
                && board[2][currentCol] == theSeed
                || currentRow == currentCol            // 3-in-the-diagonal
                && board[0][0] == theSeed
                && board[1][1] == theSeed
                && board[2][2] == theSeed
                || currentRow + currentCol == 2  // 3-in-the-opposite-diagonal
                && board[0][2] == theSeed
                && board[1][1] == theSeed
                && board[2][0] == theSeed);
    }

    public String printBoard() throws RemoteException{
        StringBuilder currentBoard = new StringBuilder("");
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                printCell(currentBoard, board[row][col]); // print each of the cells
                if (col != COLS - 1) {
                    currentBoard.append("|");   // print vertical partition
                }
            }
            if (row != ROWS - 1) {
                currentBoard.append("\n");
                currentBoard.append("-----------"); // print horizontal partition
                currentBoard.append("\n");
            }
        }
        currentBoard.append("\n");
        if (currentState == CROSS_WON) {
            return "'X' won! Bye!";
        } else if (currentState == NOUGHT_WON) {
            return "'O' won! Bye!";
        } else if (currentState == DRAW) {
            return "It's a Draw! Bye!";
        }
        return currentBoard.toString();
    }

    public void printCell(StringBuilder currentBoard, int content) throws RemoteException  {
        switch (content) {
            case EMPTY:  currentBoard.append("   "); break;
            case NOUGHT: currentBoard.append(" O "); break;
            case CROSS:  currentBoard.append(" X "); break;
        }
    }
    public void setCurrentPlayer(int player) {
        this.currentPlayer = player;
    }
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }
    public int getCurrentState() {
        return this.currentState;
    }
    public int gameAuth(String username, String password) {
        int gameId = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(username + password);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/distributedsystem", "root", "KhoANguyeN2020@");
            PreparedStatement statement = connection.prepareStatement("SELECT auth_id FROM auth WHERE username = ? AND passwort = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                gameId = resultSet.getInt(1);
                return gameId;
            } else {
                return 0;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static void main(String args[]) {
        try {
            GameServer obj = new GameServer();
            GamePlay stub = (GamePlay)
                    UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("TicTacToe", stub);


            System.err.println("Server ready");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
