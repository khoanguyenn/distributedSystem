import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Summation extends Remote {
    int sum(int n1, int n2) throws RemoteException;
    int add(Book book) throws RemoteException;
    int add(Newspaper newspaper) throws RemoteException;
    int bookQuantity() throws RemoteException;
    int newspaperQuantity() throws RemoteException;
}
