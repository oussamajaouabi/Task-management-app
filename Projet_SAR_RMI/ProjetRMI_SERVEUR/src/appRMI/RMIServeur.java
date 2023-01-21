package appRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServeur {
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(1099);
			System.out.println("Serveur : construction de l'implémentation ..");
			RMIInterfaceImp skel = new RMIInterfaceImp();
			System.out.println("Objet skel lié dans le RMI Registry ..");
			registry.rebind("skel", skel);
			System.out.println("Attente des invocations des clients ..");
		} catch (Exception e) {
			System.out.println("Erreur de liasion de l'objet skel ..");
			System.out.println(e.toString());
		}
	}
}
