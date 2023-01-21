package appRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;

public class RMIInterfaceImp extends UnicastRemoteObject implements RMIInterface{

	static Connection cnx;
	static Statement st;
	static ResultSet rst;
	
	private static final long serialVersionUID = 1L;
	
	protected RMIInterfaceImp() throws RemoteException {
		super();
	}
	
	public static Connection connecterDB(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3307/GestionDesTaches";
			String user = "root";
			String password = "root";
			Connection cnx = DriverManager.getConnection(url, user, password);
			System.out.println("Connexion bien établie avec la base de données ..");
			return cnx;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	public void createEmploye(int id_employe, String nom, String prenom, String adresse_domiciliation, int numero_compte, String grade, int superieur_hierarchique) throws RemoteException, SQLException{
		try {
			cnx = connecterDB();
			String query = "INSERT INTO employe VALUES(" + id_employe + ",'" + nom + "','" + prenom + "','" + adresse_domiciliation + "'," + numero_compte + ",'" + grade + "','" + superieur_hierarchique + "')";
			st = cnx.createStatement();
			st.executeUpdate(query);
			System.out.println("Employé bien ajouté ..");		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	@Override
	public ArrayList<Employe> readEmploye() throws RemoteException, SQLException {
		ArrayList<Employe> employes = new ArrayList<Employe>();
		try {
			cnx = connecterDB();
			st = cnx.createStatement();
			rst = st.executeQuery("SELECT * FROM employe");
			
			while (rst.next()) {
				int id_employe = rst.getInt("id_employe");
				String nom = rst.getString("nom");
				String prenom = rst.getString("prenom");
				String adresse_domiciliation  = rst.getString("adresse_domiciliation");
				int numero_compte = rst.getInt("numero_compte");
				String grade = rst.getString("grade");
				int superieur_hierarchique = rst.getInt("superieur_hierarchique");
				
				Employe employe = new Employe(id_employe, nom, prenom, adresse_domiciliation, numero_compte, grade, superieur_hierarchique);
				employes.add(employe);
			}
			System.out.println("La table des Employes est bien affichée ..");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return employes;
	}
	
	public void updateEmploye(int id_employe, String nom, String prenom, String adresse_domiciliation, int numero_compte, String grade, int superieur_hierarchique) throws RemoteException, SQLException{
		try {
			String query = "UPDATE employe SET nom = '" + nom + "', prenom = '" + prenom +"', adresse_domiciliation = '" + adresse_domiciliation + "', numero_compte = " + numero_compte + ", grade = '" + grade + "', superieur_hierarchique = '" + superieur_hierarchique + "' WHERE id_employe = " + id_employe;
			cnx = connecterDB();
			st = cnx.createStatement();
			st.executeUpdate(query);
			System.out.println("Employé bien modifié ..");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void deleteEmploye(int id_employe) throws RemoteException, SQLException{
		try {
			String query = "DELETE FROM employe WHERE id_employe = " + id_employe;
			cnx = connecterDB();
			st = cnx.createStatement();
			st.executeUpdate(query);
			System.out.println("Employé bien supprimé ..");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void createTache(int id_tache, String description, int id_employe) throws RemoteException, SQLException {
		try {
			String query = "INSERT INTO tache VALUES(" + id_tache + ",'" + description + "'," + id_employe + ")";
			cnx = connecterDB();
			st = cnx.createStatement();
			st.executeUpdate(query);
			System.out.println("Tâche bien ajoutée ..");
		} catch (Exception e) {
			System.out.println(e.toString());
		}	
	}

	@Override
	public ArrayList<Tache> readTache() throws RemoteException, SQLException {
		ArrayList<Tache> taches = new ArrayList<>();
		try {
			cnx = connecterDB();
			st = cnx.createStatement();
			rst = st.executeQuery("SELECT * FROM tache");
			
			while (rst.next()) {
				int id_tache = rst.getInt("id_tache");
				String description = rst.getString("description");
				int id_employe = rst.getInt("id_employe");
				
				Tache tache = new Tache(id_tache, description, id_employe);
				taches.add(tache);
			}
			System.out.println("La table des Tâches est bien affichée ..");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return taches;
	}

	@Override
	public void updateTache(int id_tache, String description, int id_employe) throws RemoteException, SQLException {
		try {
			String query = "UPDATE tache SET id_employe = " + id_employe + ", description = '" + description +  "' WHERE id_tache = " + id_tache;			
			cnx = connecterDB();
			st = cnx.createStatement();
			st.executeUpdate(query);
			System.out.println("Tâche bien modifiée ..");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}

	@Override
	public void deleteTache(int id_tache) throws RemoteException, SQLException {
		try {
			String query = "DELETE FROM tache WHERE id_tache = " + id_tache;
			cnx = connecterDB();
			st = cnx.createStatement();
			st.executeUpdate(query);
			System.out.println("Tâche bien supprimée ..");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}