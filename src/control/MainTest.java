package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modele.Cours;
import persistance.CoursDAO;

public class MainTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {		
		

	//étape 1: charger la classe de driver
	Class.forName("com.mysql.cj.jdbc.Driver");
	
	  //étape 2: créer l'objet de connexion
	  Connection conn = DriverManager.getConnection(
	  "jdbc:mysql://localhost:3306/ecole", "root", "");

	System.out.println("------- TP JDBC -------\n");
	
	
	
	Statement stmt = conn.createStatement();
	
	Scanner scanner = new Scanner(System.in);
	
	System.out.println("Recherche de professeur par correspondance de nom : \n");
	
	String nomProfesseur = scanner.nextLine();
	
	ResultSet rsProf = stmt.executeQuery("SELECT * FROM professeurs WHERE nom LIKE '%" + nomProfesseur + "%'");
	
	while(rsProf.next()){
	    
	    System.out.println(rsProf.getInt(1) + " " + rsProf.getString(2));
	}
	
	System.out.println("\nEffectuer une injection pour recuperer tous les professeurs :");
	
	String injection = scanner.nextLine();
	
	rsProf = stmt.executeQuery("SELECT * FROM professeurs WHERE nom LIKE '%" + injection + "%'");
	
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
	
	System.out.println("\nInserer un nouveau professeur ?\n");
	
	String choice = scanner.nextLine();
	
	if (choice.equals("non")) {
		
		System.out.println("\nPas d'insertion de professeur");
		
	} else {
	
	    System.out.println("\nEntrez son nom : )");
	    
	    nomProfesseur = scanner.nextLine();
	    
	    System.out.println("\nEntrez sa specialite : ");
	    
	    String speProfesseur = scanner.nextLine();
	    
	    System.out.println("\nEntrez son salaire actuel : ");	        
	    
	    Double salaireProf = scanner.nextDouble();	        
	    
	    int queryResult = stmt.executeUpdate("INSERT INTO Professeurs (nom, specialite, date_entree, salaire_actuel)"
	    		+ " VALUES ('" + nomProfesseur + "', '" + speProfesseur + "', now(), " + salaireProf + ")");
	    
	    if (queryResult == 0) {
	    	System.out.println("\nErreur, pas d'insertion effectuee");
	    } else System.out.println("\nL'insertion a bien ete effectuee");
	    
	}
	
	System.out.println("\n----------- Requetes preparees -----------\n");
	
	
	System.out.println("Recherche de professeur par correspondance de nom : \n");
	
	nomProfesseur = scanner.nextLine();
	
	PreparedStatement requetePrep = conn.prepareStatement("SELECT * FROM professeurs WHERE nom LIKE ?");
	
	requetePrep.setString(1, "%" + nomProfesseur + "%");
	
	rsProf = requetePrep.executeQuery();
	
	while(rsProf.next()){
	    
	    System.out.println(rsProf.getInt(1) + " " + rsProf.getString(2));
	}
	
	System.out.println("\nEffectuer une injection pour recuperer tous les professeurs :");
	
	injection = scanner.nextLine();
	
	requetePrep = conn.prepareStatement("SELECT * FROM professeurs WHERE nom LIKE ?");
	
	requetePrep.setString(1, "%" + injection + "%");
	
	rsProf = requetePrep.executeQuery();
	
	int rowCount = 0;
	
	while(rsProf.next()){
		
		rowCount++;
	    
	    System.out.println(rsProf.getInt(1) + " " + rsProf.getString(2));
	}
	
	if (rowCount == 0)
		System.out.println("\nLa requete n'a rien retourne");
	
	
	rsProf.close();
	
	stmt.close();
	
	conn.close();
	
	CoursDAO coursDao = new CoursDAO();
	
	List<Cours> cours = new ArrayList<>();
	
	cours = coursDao.getCours();
	
	cours.forEach(cour -> System.out.println(cour));
	
	System.out.println("Entrez le numero du cours a mettre a jour\n");
	
	int numCours = scanner.nextInt();
	
	System.out.println("Entrer le nombre d'heures a mettre\n");
	
	int nbHeures = scanner.nextInt();
	
	coursDao.updateNbHeures(numCours, nbHeures);
	
	System.out.println("Effectuez une injection : \n");
	
	System.out.println("Entrez le numero du cours a mettre a jour\n");
	
	numCours = scanner.nextInt();
	
	System.out.println("Entrer le nombre d'heures a mettre\n");
	
	nbHeures = scanner.nextInt();
	
	coursDao.updateNbHeures(numCours, nbHeures);
	
	cours = coursDao.getCours();
	
	cours.forEach(cour -> System.out.println(cour));	
	
	System.out.println("\n------- Requete preparee -------\n");
	
	System.out.println("Entrez le numero du cours a mettre a jour\n");
	
	numCours = scanner.nextInt();
	
	System.out.println("Entrer le nombre d'heures a mettre\n");
	
	nbHeures = scanner.nextInt();
	
	Boolean fonctionne1 = coursDao.updateNbHeuresPS(numCours, nbHeures);
	
	System.out.println("Effectuez une injection : \n");
	
	System.out.println("Entrez le numero du cours a mettre a jour\n");
	
	numCours = scanner.nextInt();
	
	System.out.println("Entrer le nombre d'heures a mettre\n");
	
	nbHeures = scanner.nextInt();
	
	Boolean fonctionne2 = coursDao.updateNbHeuresPS(numCours, nbHeures);
	
	System.out.println("\n\nResultat des requetes preparees : normale (" + fonctionne1 + "), injection (" + fonctionne2 + ")");
	
	
	
	
	
	
	
	

	}

}
