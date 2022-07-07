package persistance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.Cours;

public class CoursDAO extends Cours{
	
	public CoursDAO() {}
	
	public List<Cours> getCours() throws SQLTimeoutException, ClassNotFoundException, SQLException{
		List<Cours> cours = new ArrayList<>();
		
		DataAccess DA = new DataAccess("jdbc:mysql://localhost:3306/ecole", "root", "");
		
		PreparedStatement psCours = DA.connection.prepareStatement("Select * from cours");
		
		ResultSet rsCours = psCours.executeQuery();
		
		while(rsCours.next()){
			
		    cours.add(new Cours(rsCours.getInt("num_cours"), rsCours.getString("nom"), rsCours.getInt("nbheures"), rsCours.getInt("annee")));
		}
		
		DA.close();
		psCours.close();
		rsCours.close();
		
		return cours;
	}
	
	public Boolean updateNbHeures(int numCours, int nbHeures) {
		DataAccess DA = new DataAccess("jdbc:mysql://localhost:3306/ecole", "root", "");
		Statement stmt = DA.createStmt();
		int result = stmt.executeUpdate("UPDATE cours SET nbheures = " + nbHeures + " WHERE num_cours = " + numCours);
		DA.close();
		stmt.close();
	}
	
	public Boolean updateNbHeuresPS(int numCours, int nbHeures) {
		DataAccess DA = new DataAccess("jdbc:mysql://localhost:3306/ecole", "root", "");
		PreparedStatement psUpdate = DA.connection.prepareStatement("UPDATE cours SET nbheures = ? WHERE num_cours = ?");
		psUpdate.setInt(1, nbHeures);
		psUpdate.setInt(2, nbHeures);
		int result = psUpdate.executeUpdate();
		if (result > 0)
			return true;
		else 
			return false;
	}
	
}
