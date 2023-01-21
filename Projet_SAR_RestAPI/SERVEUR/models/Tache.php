<?php
class Tache{
    // Connexion
    private $connexion;
    private $table = "tache";
    public $id_tache;
    public $description;
    public $id_employe;

    public function __construct($db){
        $this->connexion = $db;
    }

    public function lire(){
        // On écrit la requête
        
        $sql = "SELECT e.nom as nom_employe, t.id_tache, t.description, t.id_employe 
                FROM " . $this->table . " t LEFT JOIN employe e ON t.id_employe = e.id_employe";
        
        // On prépare la requête
        $query = $this->connexion->prepare($sql);

        // On exécute la requête
        $query->execute();

        // On retourne le résultat
        return $query;
    }

    public function lire_id(){
        // On écrit la requête
        $sql = "SELECT * FROM " . $this->table . " WHERE id_tache = ?";

        // On prépare la requête
        $query = $this->connexion->prepare($sql);

        $this->id_tache=htmlspecialchars(strip_tags($this->id_tache));
        
        // On attache les variables
        $query->bindParam(1, $this->id_tache);
        
        // On exécute
        $query->execute();
        
        return $query;
    }
    public function creer(){

        // Ecriture de la requête SQL en y insérant le nom de la table
        $sql = "INSERT INTO " . $this->table . " SET description=:description, id_employe=:id_employe";

        // Préparation de la requête
        $query = $this->connexion->prepare($sql);

        // Protection contre les injections
        $this->description=htmlspecialchars(strip_tags($this->description));
        $this->id_employe=htmlspecialchars(strip_tags($this->id_employe));

        // Ajout des données protégées
        $query->bindParam(":description", $this->description);
        $query->bindParam(":id_employe", $this->id_employe);

        // Exécution de la requête
        if($query->execute()){
            return true;
        }
        return false;
    }

    public function supprimer(){
        // On écrit la requête
        $sql = "DELETE FROM " . $this->table . " WHERE id_tache = ?";

        // On prépare la requête
        $query = $this->connexion->prepare( $sql );

        // On sécurise les données
        $this->id_tache=htmlspecialchars(strip_tags($this->id_tache));

        // On attache l'id
        $query->bindParam(1, $this->id_tache);

        // On exécute la requête
        if($query->execute()){
            return true;
        }
        
        return false;
    }

    public function modifier(){
        // On écrit la requête
        $sql = "UPDATE " . $this->table . " SET description=:description, id_employe=:id_employe WHERE id_tache=:id_tache";
        
        // On prépare la requête
        $query = $this->connexion->prepare($sql);
        
        // On sécurise les données
        $this->id_tache=htmlspecialchars(strip_tags($this->id_tache));
        $this->description=htmlspecialchars(strip_tags($this->description));
        $this->id_employe=htmlspecialchars(strip_tags($this->id_employe));

        
        // On attache les variables
        $query->bindParam(":id_tache", $this->id_tache);
        $query->bindParam(":description", $this->description);
        $query->bindParam(":id_employe", $this->id_employe);
        
        // On exécute
        if($query->execute()){
            return true;
        }
        
        return false;
    }

}