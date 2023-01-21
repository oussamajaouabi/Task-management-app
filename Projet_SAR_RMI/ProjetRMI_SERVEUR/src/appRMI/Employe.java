package appRMI;

public class Employe implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id_employe, numero_compte, superieur_hierarchique;
	private String nom, prenom, adresse_domiciliation, grade;
	
	public Employe(int id_employe, String nom, String prenom, String adresse_domiciliation, int numero_compte,
			String grade, int superieur_hierarchique) {
		this.id_employe = id_employe;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse_domiciliation = adresse_domiciliation;
		this.numero_compte = numero_compte;
		this.grade = grade;
		this.superieur_hierarchique = superieur_hierarchique;
	}
	public int getId_employe() {
		return id_employe;
	}
	
	public int getNumero_compte() {
		return numero_compte;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public String getAdresse_domiciliation() {
		return adresse_domiciliation;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public int getSuperieur_hierarchique() {
		return superieur_hierarchique;
	}  	  
	
}

