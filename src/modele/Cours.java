package modele;

public class Cours {
	
	private int numCours;
	private String nom;
	private int nbHeures;
	private int annee;
	
	// Constructor 
	
	public Cours() {}
	
	public Cours(int numCours, String nom, int nbHeures, int annee) {
		this.nom = nom;
		this.nbHeures = nbHeures;
		this.numCours = numCours;
		
		
		if (annee == 1 || annee == 2) {
			this.annee = annee;
		} else {
			System.out.println("L'annee doit etre 1 ou 2 !");
			this.annee = 00;
		}
	}
	
	// Getters & Setters
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setNbHeures(int nbHeures) {
		this.nbHeures = nbHeures;
	}
	
	public void setAnnee(int annee) {
		if (annee == 1 || annee == 2) {
			this.annee = annee;
		} else {
			System.out.println("L'annee doit etre 1 ou 2 !");
		}
	}
	
	public int getId() {
		return this.numCours;
	}
	
	public String getNomCours() {
		return this.nom;
	}
	
	public int getNbHeures() {
		return this.nbHeures;
	}

	@Override
	public String toString() {
		return "Cours " + numCours + " : " + nom + ". " + nbHeures + " heures, annee : " + annee;
	}
	
	
}
