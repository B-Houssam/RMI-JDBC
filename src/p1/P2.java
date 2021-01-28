package p1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.sql.*;

public class P2 extends java.rmi.server.UnicastRemoteObject implements Int{
	
	/*
	 * Author Bousri Houssam
	 */

	protected P2() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	static Connection conn = null;
	static String url = "jdbc:mysql://localhost:3306/";
	static String dbName = "students";
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String userName = "root";
	static String password = "root";

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			System.out.println("This is P2 ... ");
			
			P2 p2 = new P2();
			Registry reg = LocateRegistry.createRegistry(2000);
			reg.rebind("Refp2", p2);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.toString());
			ExceptionSave s = new ExceptionSave();
			s.save(e);
		}

	}

	@Override
	public void afficher() throws RemoteException {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			System.out.println("Connected to database ...");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from students;");
			System.out.println("----------------------------------------");
			System.out.println("Mat              Nom              Prenom");
			System.out.println("----------------------------------------");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t | \t " + rs.getString(2) + "\t | \t " + rs.getString(3));
			}
			System.out.println("----------------------------------------");
			conn.close();
			System.out.println("Disconnected from database ...");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.toString());
			ExceptionSave s = new ExceptionSave();
			s.save(e);
		}
	}

	@Override
	public void inserer(String nom, String pre, String mat) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			Statement stmt = conn.createStatement();
			int s = stmt.executeUpdate("INSERT INTO students (matricule, nom, prenom) VALUES ("+mat+", '"+nom+"', '"+pre+"');");
			conn.close();
			System.out.println("Affichage apres ajout: ");
			afficher();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.toString());
			ExceptionSave s = new ExceptionSave();
			s.save(e);
		}
	}

	@Override
	public void supprimer(String mat) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			Statement stmt = conn.createStatement();
			int s = stmt.executeUpdate("DELETE FROM students WHERE matricule="+mat+";");
			conn.close();
			System.out.println("Affichage apres suppression: ");
			afficher();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.toString());
			ExceptionSave s = new ExceptionSave();
			s.save(e);
		}
		
	}

}
