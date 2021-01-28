package p1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Author Bousri Houssam
 */

public interface Int extends Remote{
	public void afficher () throws RemoteException;
	public void inserer (String nom, String pre, String mat) throws RemoteException;
	public void supprimer (String mat) throws RemoteException;
}
