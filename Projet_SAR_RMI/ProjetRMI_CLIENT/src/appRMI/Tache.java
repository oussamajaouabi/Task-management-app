package appRMI;

public class Tache implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id_tache, id_employe_tache;
	private String description;
	
	public Tache(int id_tache, String description, int id_employe_tache) {
		this.id_tache = id_tache;
		this.description = description;
		this.id_employe_tache = id_employe_tache;
	}
	
	public int getId_tache() {
		return id_tache;
	}

	public int getId_employe_tache() {
		return id_employe_tache;
	}

	public String getDescription() {
		return description;
	}
		   
}

