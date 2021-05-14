import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameService extends Remote {
    public void setMove(int x, int y, String player) throws RemoteException, IOException;
    public boolean isDraw() throws RemoteException;
    public boolean isWin(String player) throws RemoteException;
    public boolean isLose(String player) throws RemoteException;
    public String getCurrentPlayer() throws RemoteException;
    public String getWinner() throws RemoteException;
    public String printBoard() throws RemoteException;
    public int gameAuth(String username, String password) throws RemoteException, IOException;
}
