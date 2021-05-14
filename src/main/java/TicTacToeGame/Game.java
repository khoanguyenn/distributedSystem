import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Game implements GameService {
    private String[][] gameBoard = { { "   ", "   ", "   " }, { "   ", "   ", "   " }, { "   ", "   ", "   " } };
    private String currentPlayer = "X";
    private String winnerPlayer = null;
    private int moveCount = 0;

    public String printBoard() throws RemoteException{
        StringBuilder boardString = new StringBuilder("");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                boardString.append(gameBoard[row][col]);
                if (col != 2) {
                    boardString.append("|");
                }
            }
            if (row != 2) {
                boardString.append("\n-----------\n");
            }
        }
        return boardString.toString();
    }

    public void setMove(int x, int y, String player) throws RemoteException, IOException {
        // Sanitize the inputs;
        if ((x >= 1 && x <= 3) && (y >= 1 && y <= 3)) {
            x -= 1;
            y -= 1;
        } else {
            throw new IOException("Wrong coordinator, must not exceed 3!");
        }
        if (!(player.equals("X") || player.equals("O"))) {
            throw new IOException("No specific player " + player);
        }
        if (!this.currentPlayer.equals(player)) {
            throw new IOException("Wrong turn !");
        }
        if (this.winnerPlayer != null) {
            throw new IOException("There is a winner!");
        }

        if (gameBoard[x][y].contains("X") || gameBoard[x][y].contains("O")) {
            throw new IOException("Already exists move!");
        }
        String input = " " + player + " ";
        // Otherwise, makes change to the gameBoard
        gameBoard[x][y] = input;

        if (isWin(this.currentPlayer)) {
            this.winnerPlayer = this.currentPlayer;
        };
        // Switch player
        this.currentPlayer = (currentPlayer == "X") ? "O" : "X";

        this.moveCount++;
    }

    public boolean isWin(String player) throws RemoteException {
        //Check horizontal
        for (int index = 0; index < 3; index++) {
            if (gameBoard[index][0].contains(player) && gameBoard[index][1].contains(player) && gameBoard[index][2].contains(player)) {
                return true;
            }
        }
        //Check vertical
        for (int index = 0; index < 3; index++) {
            if (gameBoard[0][index].contains(player) && gameBoard[1][index].contains(player) && gameBoard[2][index].contains(player)) {
                return true;
            }
        }
        // Check diagonal
        if ((gameBoard[0][0].contains(player) && gameBoard[1][1].contains(player) && gameBoard[2][2].contains(player))
                || (gameBoard[2][0].contains(player) && gameBoard[1][1].contains(player)
                        && gameBoard[0][2].contains(player))) {
            return true;
        }
        return false;
    }
    public boolean isLose(String player) {
        return this.winnerPlayer != null && !player.equals(this.winnerPlayer);
    }
    public boolean isDraw() {
        if (this.winnerPlayer == null && this.moveCount == 9) {
            return true;
        } else {
            return false;
        }
    }

    public String getWinner() {
        return this.winnerPlayer;
    }
    
    public String getCurrentPlayer() {
        return this.currentPlayer;
    }
    public int gameAuth(String username, String password) throws RemoteException, IOException {
        int gameId = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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

}