<?php
class Employe{
    // Connexion
    private $connexion;
    private $table = "employe";
    public $id_employe;
    public $nom;
    public $prenom;
    public $adresse_domiciliation;
    public $numero_compte;
    public $grade;
    public $superieur_hierarchique;

    public function __construct($db){
        $this->connexion = $db;
    }

    public function lire(){
        // On écrit la requête
        $sql = "SELECT * FROM " . $this->table;

        // On prépare la requête
        $query = $this->connexion->prepare($sql);

        // On exécute la requête
        $query->execute();

        // On retourne le résultat
        return $query;
    }

    public function lire_id(){
        // On écrit la requête
        $sql = "SELECT * FROM " . $this->table . " WHERE id_employe = ?";

        // On prépare la requête
        $query = $this->connexion->prepare($sql);

        $this->id_employe=htmlspecialchars(strip_tags($this->id_employe));
        
        // On attache les variables
        $query->bindParam(1, $this->id_employe);
        
        // On exécute
        $query->execute();
        
        return $query;
    }
    public function creer(){

        // Ecriture de la requête SQL en y insérant le nom de la table
        $sql = "INSERT INTO " . $this->table . " SET nom=:nom, prenom=:prenom, adresse_domiciliation=:adresse_domiciliation, numero_compte=:numero_compte, grade=:grade, superieur_hierarchique=:superieur_hierarchique";

        // Préparation de la requête
        $query = $this->connexion->prepare($sql);

        // Protection contre les injections
        $this->nom=htmlspecialchars(strip_tags($this->nom));
        $this->prenom=htmlspecialchars(strip_tags($this->prenom));
        $this->adresse_domiciliation=htmlspecialchars(strip_tags($this->adresse_domiciliation));
        $this->numero_compte=htmlspecialchars(strip_tags($this->numero_compte));
        $this->grade = htmlspecialchars(strip_tags($this->grade));
        $this->superieur_hierarchique = htmlspecialchars(strip_tags($this->superieur_hierarchique));

        // Ajout des données protégées
        $query->bindParam(":nom", $this->nom);
        $query->bindParam(":prenom", $this->prenom);
        $query->bindParam(":adresse_domiciliation", $this->adresse_domiciliation);
        $query->bindParam(":numero_compte", $this->numero_compte);
        $query->bindParam(":grade", $this->grade);
        $query->bindParam(":superieur_hierarchique", $this->superieur_hierarchique);

        // Exécution de la requête
        if($query->execute()){
            return true;
        }
        return false;
    }

    public function supprimer(){
        // On écrit la requête
        $sql = "DELETE FROM " . $this->table . " WHERE id_employe = ?";

        // On prépare la requête
        $query = $this->connexion->prepare( $sql );

        // On sécurise les données
        $this->id_employe=htmlspecialchars(strip_tags($this->id_employe));

        // On attache l'id
        $query->bindParam(1, $this->id_employe);

        // On exécute la requête
        if($query->execute()){
            return true;
        }
        
        return false;
    }

    public function modifier(){
        // On écrit la requête
        $sql = "UPDATE " . $this->table . " SET nom=:nom, prenom=:prenom, adresse_domiciliation=:adresse_domiciliation, numero_compte=:numero_compte, grade=:grade, superieur_hierarchique=:superieur_hierarchique WHERE id_employe = :id_employe";
        
        // On prépare la requête
        $query = $this->connexion->prepare($sql);
        
        // On sécurise les données
        $this->id_employe=htmlspecialchars(strip_tags($this->id_employe));
        $this->nom=htmlspecialchars(strip_tags($this->nom));
        $this->prenom=htmlspecialchars(strip_tags($this->prenom));
        $this->adresse_domiciliation=htmlspecialchars(strip_tags($this->adresse_domiciliation));
        $this->numero_compte=htmlspecialchars(strip_tags($this->numero_compte));
        $this->grade = htmlspecialchars(strip_tags($this->grade));
        $this->superieur_hierarchique = htmlspecialchars(strip_tags($this->superieur_hierarchique));
        
        // On attache les variables
        $query->bindParam(":id_employe", $this->id_employe);
        $query->bindParam(":nom", $this->nom);
        $query->bindParam(":prenom", $this->prenom);
        $query->bindParam(":adresse_domiciliation", $this->adresse_domiciliation);
        $query->bindParam(":numero_compte", $this->numero_compte);
        $query->bindParam(":grade", $this->grade);
        $query->bindParam(":superieur_hierarchique", $this->superieur_hierarchique);
        
        // On exécute
        if($query->execute()){
            return true;
        }
        
        return false;
    }

}