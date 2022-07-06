package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		


	      //étape 1: charger la classe de driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //étape 2: créer l'objet de connexion
	      Connection conn = DriverManager.getConnection(
	      "jdbc:mysql://localhost:3306/ecole", "root", "");

	        System.out.println("Hello, World!");



	        Statement stmt = conn.createStatement();

	        Scanner scanner = new Scanner(System.in);

	        System.out.println("Recherche de professeur par correspondance de nom :");

	        String nomProfesseur = scanner.nextLine();

	        ResultSet rsProf = stmt.executeQuery("SELECT * FROM professeurs WHERE nom LIKE '%" + nomProfesseur + "%'");

	        while(rsProf.next()){
	            
	            System.out.println(rsProf.getInt(1) + " " + rsProf.getString(2));
	        }
	        
	        /*
	         Recherche de professeur par correspondance de nom :
			bot' OR 1=1; -- 
			1 Bottle
			2 Bolenov
			3 Tonilaclasse
			4 Pastecnov
			5 Selector
			6 Vilplusplus
			7 Francesca
			8 Pucette
	         */
	        
	        System.out.println("Insérer un nouveau professeur : \n Entrez son nom : ");
	        
	        nomProfesseur = scanner.nextLine();
	        
	        System.out.println("Entrez sa spécialité : ");
	        
	        String speProfesseur = scanner.nextLine();
	        
	        
	        
	        int concernedLines = stmt.executeQuery("UPDATE Professeurs SET  ");


	        rsProf.close();

	        stmt.close();

	        conn.close();

	        scanner.close();

	}

}
