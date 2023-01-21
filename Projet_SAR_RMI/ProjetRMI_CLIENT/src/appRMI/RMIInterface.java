package appRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RMIInterface extends Remote{
	public void createEmploye(int id_employe, String nom, String prenom, String adresse_domiciliation, int numero_compte, String grade, int superieur_hierarchique) throws RemoteException, SQLException;
	public ArrayList<Employe> readEmploye() throws RemoteException, SQLException;
	public void updateEmploye(int id_employe, String nom, String prenom, String adresse_domiciliation, int numero_compte, String grade, int superieur_hierarchique) throws RemoteException, SQLException;
	public void deleteEmploye(int id_employe) throws RemoteException, SQLException;
	
	public void createTache(int id_tache, String description, int id_employe) throws RemoteException, SQLException;
	public ArrayList<Tache> readTache() throws RemoteException, SQLException;
	public void updateTache(int id_tache, String description, int id_employe) throws RemoteException, SQLException;
	public void deleteTache(int id_tache) throws RemoteException, SQLException;
}

