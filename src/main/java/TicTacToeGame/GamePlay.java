import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GamePlay extends Remote {
    String printBoard() throws RemoteException;
    boolean hasWon(int theSeed, int currentRow, int currentCol) throws RemoteException;
    boolean isDraw() throws RemoteException;
    void updateGame(int theSeed) throws RemoteException;
    void playerMove(int theSeed, int row, int col) throws RemoteException;
    void initGame() throws RemoteException;
    void setCurrentPlayer(int currentPlayer) throws RemoteException;
    int getCurrentPlayer() throws RemoteException;
    int getCurrentState() throws RemoteException;
    int gameAuth(String username, String password) throws RemoteException;
    // (PLAYING, DRAW, CROSS_WON, NOUGHT_WON)
    public int currentRow = 0, currentCol = 0; // current seed's row and column
    // Name-constants to represent the seeds and cell contents
    public static final int EMPTY = 0;
    public static final int CROSS = 1;
    public static final int NOUGHT = 2;

    // Name-constants to represent the various states of the game
    public static final int PLAYING = 0;
    public static final int DRAW = 1;
    public static final int CROSS_WON = 2;
    public static final int NOUGHT_WON = 3;
}
