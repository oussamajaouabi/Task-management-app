package appRMI;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Cursor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;

public class RMIClient extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txt_id_employe;
	private JTable table_emp;
	private JTextField txt_nom;
	private JTextField txt_prenom;
	private JTextField txt_adresse_domiciliation;
	private JTextField txt_numero_compte;
	private JTextField txt_grade;
	private JTextField txt_superieur_hierarchique;
	private JTextField txt_id_tache;
	private JTextField txt_desc;
	private JTextField txt_id_employe_tache;
	private JTable table_tasks;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RMIClient frame = new RMIClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RMIClient() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RMIClient.class.getResource("/img/project_icon.png")));
		setTitle("Application de Gestion des Tâches des Employés");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1376, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(-11, -26, 1381, 390);
		contentPane.add(tabbedPane);
		
		Panel panel_2 = new Panel();
		panel_2.setBackground(new Color(230, 230, 250));
		tabbedPane.addTab("New tab", null, panel_2, null);
		
		JButton btn_read_emp = new JButton("Afficher la liste des Employes");
		btn_read_emp.setBounds(547, 300, 257, 27);
		btn_read_emp.setBorder(UIManager.getBorder("CheckBox.border"));
		btn_read_emp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_read_emp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Afficher la table des Employés
				try {	
					RMIInterface stub = (RMIInterface) Naming.lookup("rmi://localhost:1099/skel");
					
					ArrayList<Employe> employes = new ArrayList<Employe>();
					employes = stub.readEmploye();
					
					DefaultTableModel model = (DefaultTableModel) table_emp.getModel();
				
					String id_employe, nom, prenom, adresse_domiciliation, numero_compte, grade, superieur_hierarchique;
					
					for(Employe employe : employes) {
						id_employe = String.valueOf(employe.getId_employe());
						nom = employe.getNom();
						prenom = employe.getPrenom();
						adresse_domiciliation = employe.getAdresse_domiciliation();
						numero_compte = String.valueOf(employe.getNumero_compte());
						grade = employe.getGrade();
						superieur_hierarchique = String.valueOf(employe.getSuperieur_hierarchique());
						String[] row = {id_employe, nom, prenom, adresse_domiciliation, numero_compte, grade, superieur_hierarchique};
						model.addRow(row);
					}
					
					btn_read_emp.setEnabled(false);
				} catch (Exception exp) {
					JOptionPane.showMessageDialog(null, exp, "Erreur: exception", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		panel_2.setLayout(null);
		btn_read_emp.setFont(new Font("Calibri", Font.PLAIN, 15));
		panel_2.add(btn_read_emp);
		
		JLabel label_id_employe = new JLabel("ID Employe");
		label_id_employe.setBounds(20, 14, 108, 19);
		label_id_employe.setFont(new Font("Secular One", Font.ITALIC, 15));
		panel_2.add(label_id_employe);
		
		JLabel label_nom = new JLabel("Nom");
		label_nom.setBounds(20, 44, 94, 19);
		label_nom.setFont(new Font("Secular One", Font.ITALIC, 15));
		panel_2.add(label_nom);
		
		JLabel label_prenom = new JLabel("Prénom");
		label_prenom.setBounds(20, 74, 94, 19);
		label_prenom.setFont(new Font("Secular One", Font.ITALIC, 15));
		panel_2.add(label_prenom);
		
		JLabel label_adrdomi = new JLabel("Adresse Domiciliation");
		label_adrdomi.setBounds(20, 106, 171, 19);
		label_adrdomi.setFont(new Font("Secular One", Font.ITALIC, 15));
		panel_2.add(label_adrdomi);
		
		JLabel label_num_compte = new JLabel("Numéro Compte");
		label_num_compte.setBounds(20, 138, 144, 19);
		label_num_compte.setFont(new Font("Secular One", Font.ITALIC, 15));
		panel_2.add(label_num_compte);
		
		JLabel label_grade = new JLabel("Grade");
		label_grade.setBounds(20, 168, 94, 19);
		label_grade.setFont(new Font("Secular One", Font.ITALIC, 15));
		panel_2.add(label_grade);
		
		JLabel label_suphierar = new JLabel("Supérieur Hiérarchique");
		label_suphierar.setBounds(20, 199, 181, 19);
		label_suphierar.setFont(new Font("Secular One", Font.ITALIC, 15));
		panel_2.add(label_suphierar);
		
		txt_id_employe = new JTextField();
		txt_id_employe.setBounds(208, 12, 105, 20);
		txt_id_employe.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txt_id_employe.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txt_id_employe.setColumns(10);
		panel_2.add(txt_id_employe);
		
		JButton btn_add_emp = new JButton("Ajouter Employe");
		btn_add_emp.setBounds(76, 243, 161, 27);
		btn_add_emp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Ajouter Employé
				try {
					int id_employe = Integer.parseInt(txt_id_employe.getText());
					String nom = txt_nom.getText();
					String prenom = txt_prenom.getText();
					String adresse_domiciliation = txt_adresse_domiciliation.getText();
					int numero_compte = Integer.parseInt(txt_numero_compte.getText());
					String grade = txt_grade.getText();
					int superieur_hierarchique = Integer.parseInt(txt_superieur_hierarchique.getText());
			
					RMIInterface stub = (RMIInterface) Naming.lookup("rmi://localhost:1099/skel");
					
					ArrayList<Employe> empArrayList = new ArrayList<Employe>();
					empArrayList = stub.readEmploye();
					
					boolean test[] = new boolean[3];
					test[0] = false;
					test[1] = false;
					test[2] = false;
					
					String empGrade = "";
				
					if(empArrayList.isEmpty() && superieur_hierarchique==id_employe) {
						test[1] = true;
					}
					
					for(Employe emp : empArrayList) {
						if(emp.getId_employe() == id_employe) { 
							test[0] = true;
						}
						if(emp.getId_employe() == superieur_hierarchique) { 
							test[1] = true;
						}
						if(emp.getNumero_compte() == numero_compte) {
							test[2] = true;
						}
						if(emp.getId_employe() == superieur_hierarchique) {
							empGrade = emp.getGrade();
						}

					}
					
					if(test[0] == false && test[1] == true && test[2] == false){
						stub.createEmploye(id_employe, nom, prenom, adresse_domiciliation, numero_compte, grade, superieur_hierarchique);
						JOptionPane.showMessageDialog(null, "Employé bien ajouté ..", "Succès", JOptionPane.INFORMATION_MESSAGE);
					} else if(test[0] == true) {
						JOptionPane.showMessageDialog(null, "Employé déjà existe", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					} else if(grade.compareTo(empGrade) < 0) { 
						JOptionPane.showMessageDialog(null, "Impossible d'avoir un employé avec une grade supérieur au celui de son supérieur hiérarchique ..", "Erreur", JOptionPane.INFORMATION_MESSAGE); 
					} else if(test[1] == false) {
						JOptionPane.showMessageDialog(null, "Supérieur hiérarchique n'existe pas ..", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					} else if (test[2] == true) {
						JOptionPane.showMessageDialog(null, "Numéro de compte déjà existe ..", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					} 
				} catch (Exception ex) { 
					JOptionPane.showMessageDialog(null, ex, "Erreur: exception", JOptionPane.INFORMATION_MESSAGE);
				} 
			}
		});
		btn_add_emp.setBorder(UIManager.getBorder("ToggleButton.border"));
		btn_add_emp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_add_emp.setFont(new Font("Calibri", Font.PLAIN, 15));
		panel_2.add(btn_add_emp);
		
		JButton btn_update_emp = new JButton("Modifier Employe");
		btn_update_emp.setBounds(76, 281, 161, 27);
		btn_update_emp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Modifier Employé
				try {
					int id_employe = Integer.parseInt(txt_id_employe.getText());
					String nom = txt_nom.getText();
					String prenom = txt_prenom.getText();
					String adresse_domiciliation = txt_adresse_domiciliation.getText();
					int numero_compte = Integer.parseInt(txt_numero_compte.getText());
					String grade = txt_grade.getText();
					int superieur_hierarchique = Integer.parseInt(txt_superieur_hierarchique.getText());
			
					RMIInterface stub = (RMIInterface) Naming.lookup("rmi://localhost:1099/skel");
					
					ArrayList<Employe> empArrayList = new ArrayList<Employe>();
					empArrayList = stub.readEmploye();
					
					boolean test[] = new boolean[3];
					test[0] = false;
					test[1] = false;
					test[2] = false;
					
					String empGrade = "";
				
					for(Employe emp : empArrayList) { 
						if(emp.getId_employe() == id_employe) { 
							test[0] = true;
						}
						if(emp.getId_employe() == superieur_hierarchique) { 
							test[1] = true;
						}
						if(emp.getNumero_compte() == numero_compte && emp.getId_employe() != id_employe) {
							test[2] = true;
						}
						if(emp.getId_employe() == superieur_hierarchique) {
							empGrade = emp.getGrade();
						}
					}
					
					if(test[0] == false) {
						JOptionPane.showMessageDialog(null, "Employé n'existe pas", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					} else if(grade.compareTo(empGrade) < 0) { 
						JOptionPane.showMessageDialog(null, "Impossible d'avoir un employé avec une grade supérieur au celui de son supérieur hiérarchique ..", "Erreur", JOptionPane.INFORMATION_MESSAGE); 
					} else if(test[1] == false) {
						JOptionPane.showMessageDialog(null, "Supérieur hiérarchique n'existe pas ..", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					} else if (test[2] == true) {
						JOptionPane.showMessageDialog(null, "Numéro de compte déjà existe ..", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					} else if(test[0] == true && test[1] == true && test[2] == false){
						stub.updateEmploye(id_employe, nom, prenom, adresse_domiciliation, numero_compte, grade, superieur_hierarchique);
						JOptionPane.showMessageDialog(null, "Employé bien modifié ..", "Succès", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (Exception ex) { 
					JOptionPane.showMessageDialog(null, ex, "Erreur: exception", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_update_emp.setBorder(UIManager.getBorder("CheckBox.border"));
		btn_update_emp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_update_emp.setFont(new Font("Calibri", Font.PLAIN, 15));
		panel_2.add(btn_update_emp);
		
		JButton btn_delete_emp = new JButton("Supprimer Employe");
		btn_delete_emp.setBounds(76, 319, 161, 27);
		btn_delete_emp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Supprimer Employe
				try {
					int id_employe = Integer.parseInt(txt_id_employe.getText());
					
					RMIInterface stub = (RMIInterface) Naming.lookup("rmi://localhost:1099/skel");
					
					ArrayList<Employe> empArrayList = new ArrayList<Employe>();
					empArrayList = stub.readEmploye();
					
					boolean test = false;
					
					for(Employe emp : empArrayList) {
						if (emp.getId_employe() == id_employe) {
							test = true;
						}
					}
					
					if(test == true) {
						stub.deleteEmploye(id_employe);
						JOptionPane.showMessageDialog(null, "Employé bien supprimé ..", "Succès", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Employé n'existe pas ..", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					}
				
				} catch (Exception ex) { 
					JOptionPane.showMessageDialog(null, ex, "Erreur: exception", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_delete_emp.setBorder(UIManager.getBorder("CheckBox.border"));
		btn_delete_emp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_delete_emp.setFont(new Font("Calibri", Font.PLAIN, 15));
		panel_2.add(btn_delete_emp);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(334, 11, 854, 278);
		scrollPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		scrollPane.setBackground(new Color(255, 153, 153));
		panel_2.add(scrollPane);
		
		table_emp = new JTable();
		table_emp.setGridColor(new Color(0, 0, 0));
		table_emp.setBackground(new Color(245, 222, 179));
		table_emp.setForeground(new Color(0, 0, 0));
		table_emp.setBorder(null);
		table_emp.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane.setViewportView(table_emp);
		table_emp.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Employe", "Nom", "Pr\u00E9nom", "Adresse Domiciliation", "Num\u00E9ro Compte", "Grade", "Sup\u00E9rieur Hi\u00E9rachique"
			}
		));
		table_emp.getColumnModel().getColumn(1).setPreferredWidth(34);
		table_emp.getColumnModel().getColumn(2).setPreferredWidth(47);
		table_emp.getColumnModel().getColumn(3).setPreferredWidth(112);
		table_emp.getColumnModel().getColumn(4).setPreferredWidth(90);
		table_emp.getColumnModel().getColumn(5).setPreferredWidth(41);
		table_emp.getColumnModel().getColumn(6).setPreferredWidth(115);
		
		JButton btn_clear_emp = new JButton("");
		btn_clear_emp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_clear_emp.setBounds(883, 300, 36, 27);
		btn_clear_emp.setIcon(new ImageIcon(RMIClient.class.getResource("/img/btn_clear.png")));
		btn_clear_emp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Réinitialiser la table des Employés
				table_emp.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"ID Employe", "Nom", "Pr\u00E9nom", "Adresse Domiciliation", "Num\u00E9ro Compte", "Grade", "Sup\u00E9rieur Hi\u00E9rachique"
						}
				));
				table_emp.getColumnModel().getColumn(1).setPreferredWidth(34);
				table_emp.getColumnModel().getColumn(2).setPreferredWidth(47);
				table_emp.getColumnModel().getColumn(3).setPreferredWidth(112);
				table_emp.getColumnModel().getColumn(4).setPreferredWidth(90);
				table_emp.getColumnModel().getColumn(5).setPreferredWidth(41);
				table_emp.getColumnModel().getColumn(6).setPreferredWidth(115);
				btn_read_emp.setEnabled(true);
			}
		});
		btn_clear_emp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_clear_emp.setBorder(UIManager.getBorder("CheckBox.border"));
		panel_2.add(btn_clear_emp);
		
		txt_nom = new JTextField();
		txt_nom.setBounds(208, 45, 105, 20);
		txt_nom.setColumns(10);
		txt_nom.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.add(txt_nom);
		
		txt_prenom = new JTextField();
		txt_prenom.setBounds(208, 75, 105, 20);
		txt_prenom.setColumns(10);
		txt_prenom.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.add(txt_prenom);
		
		txt_adresse_domiciliation = new JTextField();
		txt_adresse_domiciliation.setBounds(208, 107, 105, 20);
		txt_adresse_domiciliation.setColumns(10);
		txt_adresse_domiciliation.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.add(txt_adresse_domiciliation);
		
		txt_numero_compte = new JTextField();
		txt_numero_compte.setBounds(208, 139, 105, 20);
		txt_numero_compte.setColumns(10);
		txt_numero_compte.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.add(txt_numero_compte);
		
		txt_grade = new JTextField();
		txt_grade.setBounds(208, 169, 105, 20);
		txt_grade.setColumns(10);
		txt_grade.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.add(txt_grade);
		
		txt_superieur_hierarchique = new JTextField();
		txt_superieur_hierarchique.setBounds(208, 200, 105, 20);
		txt_superieur_hierarchique.setColumns(10);
		txt_superieur_hierarchique.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.add(txt_superieur_hierarchique);
		
		JLabel empLabel_emp = new JLabel("");
		empLabel_emp.setIcon(new ImageIcon(RMIClient.class.getResource("/img/emp1.png")));
		empLabel_emp.setBounds(1048, -66, 387, 456);
		panel_2.add(empLabel_emp);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(230, 230, 250));
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
		JButton btn_read_tache = new JButton("Afficher la liste des Tâches");
		btn_read_tache.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_read_tache.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Afficher la table des Tâches
				try {	
					RMIInterface stub = (RMIInterface) Naming.lookup("rmi://localhost:1099/skel");
					
					ArrayList<Tache> taches = new ArrayList<>();
					taches = stub.readTache();
					
					DefaultTableModel model = (DefaultTableModel) table_tasks.getModel();
				
					String id_tache, description, id_employe_tache;
					
					for(Tache tache : taches) {
						id_tache = String.valueOf(tache.getId_tache());
						description = tache.getDescription();
						id_employe_tache = String.valueOf(tache.getId_employe_tache());
					
						String[] row = {id_tache, description, id_employe_tache};
						model.addRow(row);
					}
					
					btn_read_tache.setEnabled(false);
				} catch (Exception exp) {
					JOptionPane.showMessageDialog(null, exp, "Erreur: exception", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		btn_read_tache.setBounds(793, 286, 257, 27);
		btn_read_tache.setFont(new Font("Calibri", Font.PLAIN, 15));
		btn_read_tache.setBorder(UIManager.getBorder("CheckBox.border"));
		panel_1.add(btn_read_tache);
		
		JLabel label_id_tache = new JLabel("ID Tâche");
		label_id_tache.setBounds(211, 13, 108, 19);
		label_id_tache.setFont(new Font("Secular One", Font.ITALIC, 15));
		panel_1.add(label_id_tache);
		
		JLabel label_desc = new JLabel("Description");
		label_desc.setBounds(211, 43, 94, 19);
		label_desc.setFont(new Font("Secular One", Font.ITALIC, 15));
		panel_1.add(label_desc);
		
		JLabel label_id_empTache = new JLabel("ID Employe");
		label_id_empTache.setBounds(211, 126, 94, 19);
		label_id_empTache.setFont(new Font("Secular One", Font.ITALIC, 15));
		panel_1.add(label_id_empTache);
		
		txt_id_tache = new JTextField();
		txt_id_tache.setBounds(399, 11, 105, 20);
		txt_id_tache.setColumns(10);
		txt_id_tache.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.add(txt_id_tache);
		
		JButton btn_add_tache = new JButton("Ajouter Tâche");
		btn_add_tache.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_add_tache.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Ajouter Tâche
				try {
					int id_tache = Integer.parseInt(txt_id_tache.getText());
					String description = txt_desc.getText();
					int id_employe = Integer.parseInt(txt_id_employe_tache.getText());
			
					RMIInterface stub = (RMIInterface) Naming.lookup("rmi://localhost:1099/skel");
					
					boolean test[] = new boolean[2];
					test[0] = false;
					test[1] = false;
					
					ArrayList<Tache> tacheArrayList = new ArrayList<Tache>();
					
					tacheArrayList = stub.readTache();
					
					ArrayList<Employe> empArrayList = new ArrayList<Employe>();
					
					empArrayList = stub.readEmploye();
					
					for(Employe emp : empArrayList) { 
						if(emp.getId_employe() == id_employe) { 
							test[0] = true;
						}
					}
					
					for(Tache tache : tacheArrayList) { 
						if(tache.getId_tache() == id_tache) { 
							test[1] = true;
						}
					}
					
					if(test[0] == true && test[1] == false) {
						stub.createTache(id_tache, description, id_employe);
						JOptionPane.showMessageDialog(null, "Tâche bien ajoutée ..", "Succès", JOptionPane.INFORMATION_MESSAGE);
					} else if(test[0] == false){
						JOptionPane.showMessageDialog(null, "Employé n'existe pas dans la liste des Employés ..", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					} else if(test[1] == true) {
						JOptionPane.showMessageDialog(null, "Tâche déjà effectuée", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (Exception ex) { 
					JOptionPane.showMessageDialog(null, ex, "Erreur: exception", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_add_tache.setBounds(288, 181, 161, 27);
		btn_add_tache.setFont(new Font("Calibri", Font.PLAIN, 15));
		btn_add_tache.setBorder(UIManager.getBorder("ToggleButton.border"));
		panel_1.add(btn_add_tache);
		
		JButton btn_update_tache = new JButton("Modifier Tâche");
		btn_update_tache.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_update_tache.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Modifier Tâche
				try {
					int id_tache = Integer.parseInt(txt_id_tache.getText());
					String description = txt_desc.getText();
					int id_employe_tache = Integer.parseInt(txt_id_employe_tache.getText());
			
					RMIInterface stub = (RMIInterface) Naming.lookup("rmi://localhost:1099/skel");
					
					boolean test[] = new boolean[2];
					test[0] = false;
					test[1] = false;
					
					ArrayList<Tache> tacheArrayList = new ArrayList<Tache>();
					
					tacheArrayList = stub.readTache();
					
					ArrayList<Employe> empArrayList = new ArrayList<Employe>();
					
					empArrayList = stub.readEmploye();

					for(Employe emp : empArrayList) { 
						if(emp.getId_employe() == id_employe_tache) { 
							test[0] = true;
						}
					}

					for(Tache tache : tacheArrayList) { 
						if(tache.getId_tache() == id_tache) { 
							test[1] = true;
						}
					}
					
					if(test[0] == false){
						JOptionPane.showMessageDialog(null, "Employé n'existe pas dans la liste des Employés ..", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					} else if(test[1] == false) {
						JOptionPane.showMessageDialog(null, "Tâche introuvée ..", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					} else if(test[0] == true && test[1] == true) {
						stub.updateTache(id_tache, description, id_employe_tache);
						JOptionPane.showMessageDialog(null, "Tâche bien modifée ..", "Succès", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (Exception ex) { 
					JOptionPane.showMessageDialog(null, ex, "Erreur: exception", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_update_tache.setBounds(288, 218, 161, 27);
		btn_update_tache.setFont(new Font("Calibri", Font.PLAIN, 15));
		btn_update_tache.setBorder(UIManager.getBorder("CheckBox.border"));
		panel_1.add(btn_update_tache);
		
		JButton btn_delete_tache = new JButton("Supprimer Tâche");
		btn_delete_tache.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_delete_tache.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Supprimer Tâche
				try {
					int id_tache = Integer.parseInt(txt_id_tache.getText());

					RMIInterface stub = (RMIInterface) Naming.lookup("rmi://localhost:1099/skel");
					
					ArrayList<Tache> tacheArrayList = new ArrayList<Tache>();
					tacheArrayList = stub.readTache();
					
					boolean test = false;
					
					for(Tache tache : tacheArrayList) {
						if (tache.getId_tache() == id_tache) {
							test = true;
						}
					}
					
					if(test == true) {
						stub.deleteTache(id_tache);
						JOptionPane.showMessageDialog(null, "Tâche bien supprimé ..", "Succès", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Tâche introuvée ..", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					}
							
				} catch (Exception ex) { 
					JOptionPane.showMessageDialog(null, ex, "Erreur: exception", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_delete_tache.setBounds(288, 258, 161, 27);
		btn_delete_tache.setFont(new Font("Calibri", Font.PLAIN, 15));
		btn_delete_tache.setBorder(UIManager.getBorder("CheckBox.border"));
		panel_1.add(btn_delete_tache);
		
		JButton btn_clear_tache = new JButton("");
		btn_clear_tache.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_clear_tache.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Réinitialiser la table des Tâches
				table_tasks.setModel(new DefaultTableModel(
						new Object[][] {},
						new String[] {
							"ID T\u00E2che", "Description", "ID Employe"
						}));
				btn_read_tache.setEnabled(true);
			}
		});
		btn_clear_tache.setBounds(1110, 286, 36, 27);
		btn_clear_tache.setIcon(new ImageIcon(RMIClient.class.getResource("/img/btn_clear.png")));
		btn_clear_tache.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_clear_tache.setBorder(UIManager.getBorder("CheckBox.border"));
		panel_1.add(btn_clear_tache);
		
		txt_desc = new JTextField();
		txt_desc.setBounds(399, 44, 201, 72);
		txt_desc.setColumns(10);
		txt_desc.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.add(txt_desc);
		
		txt_id_employe_tache = new JTextField();
		txt_id_employe_tache.setBounds(399, 127, 105, 20);
		txt_id_employe_tache.setColumns(10);
		txt_id_employe_tache.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.add(txt_id_employe_tache);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(665, 13, 690, 262);
		panel_1.add(scrollPane_1);
		
		table_tasks = new JTable();
		table_tasks.setBorder(null);
		table_tasks.setFont(new Font("Arial", Font.PLAIN, 14));
		table_tasks.setBackground(new Color(245, 222, 179));
		table_tasks.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID T\u00E2che", "Description", "ID Employe"
			}
		));
		scrollPane_1.setViewportView(table_tasks);
		
		JLabel empLabel_tache = new JLabel("");
		empLabel_tache.setIcon(new ImageIcon(RMIClient.class.getResource("/img/emp2.png")));
		empLabel_tache.setBounds(-143, -71, 344, 485);
		panel_1.add(empLabel_tache);
		
		Panel panel = new Panel();
		panel.setLayout(null);
		panel.setForeground(Color.BLACK);
		panel.setBackground(new Color(220, 20, 60));
		panel.setBounds(-1, 359, 1371, 122);
		contentPane.add(panel);
		
		JButton btn_emp = new JButton("Gestion des Employes");
		btn_emp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_emp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btn_emp.setForeground(new Color(0, 0, 0));
		btn_emp.setFont(new Font("Calibri", Font.PLAIN, 15));
		btn_emp.setBackground(new Color(211, 211, 211));
		btn_emp.setBounds(381, 52, 192, 39);
		panel.add(btn_emp);
		
		JButton btn_tache = new JButton("Gestion des Tâches");
		btn_tache.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_tache.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btn_tache.setForeground(new Color(0, 0, 0));
		btn_tache.setFont(new Font("Calibri", Font.PLAIN, 15));
		btn_tache.setBackground(new Color(211, 211, 211));
		btn_tache.setBounds(653, 52, 192, 39);
		panel.add(btn_tache);
		
		JLabel lblNewLabel_8 = new JLabel("Application de Gestion des Tâches des Employes");
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setFont(new Font("Fredoka One", Font.PLAIN, 25));
		lblNewLabel_8.setBounds(322, 11, 607, 39);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel = new JLabel("© Jaouabi Oussama, Hamdi Helmi | IF4");
		lblNewLabel.setForeground(new Color(255, 255, 240));
		lblNewLabel.setFont(new Font("Ubuntu-Title-fr", Font.PLAIN, 11));
		lblNewLabel.setBounds(1195, 97, 166, 14);
		panel.add(lblNewLabel);
				
	}
}
