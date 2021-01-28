package p1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class P1 {
	
	/*
	 * Author Bousri Houssam
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			int b = 999;
			System.out.println("This is P1 ... ");
			Scanner sc = new Scanner(System.in);
			Scanner s = new Scanner(System.in);
			Registry reg1 = LocateRegistry.getRegistry("localhost", 2000);
			Int i = (Int) reg1.lookup("Refp2");
			
			while (true) {
				System.out.println("Faite votre choix:  ");
				System.out.println("1- affichage de la base de donnée.");
				System.out.println("2- supression d'un élément.");
				System.out.println("3- ajout d'un élément.");
				System.out.println("0- Quitter.");
				b = sc.nextInt();
				
				switch (b) {
				case 0: {
					System.exit(0);
				}
				case 1: {
					i.afficher();
					break;
				}
				case 2: {
					System.out.println("Donner le matricule: ");
					String mat = s.nextLine();
					i.supprimer(mat);
					break;
				}
				case 3: {
					System.out.println("Donner le nom: ");
					String Nom = s.nextLine();
					System.out.println("prénom: ");
					String Pre = s.nextLine();
					System.out.println("matricule: ");
					String Mat = s.nextLine();
					i.inserer(Nom, Pre, Mat);
					break;
				}
				default:
					System.out.println("Couix doit etre entre 0 et 4");
					break;
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.toString());
			ExceptionSave s = new ExceptionSave();
			s.save(e);
		}

	}

}
