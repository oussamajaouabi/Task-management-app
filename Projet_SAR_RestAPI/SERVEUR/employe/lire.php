<?php
// Headers requis
header("Access-Control-Allow-Origin: *"); //api public
header("Content-Type: application/json; charset=UTF-8"); //réponse en JSON
header("Access-Control-Allow-Methods: GET"); //La méthode est accepté par GET
header("Access-Control-Max-Age: 3600"); //durér de vie de la requête
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

// On vérifie que la méthode utilisée est correcte
if($_SERVER['REQUEST_METHOD'] == 'GET'){
    // On inclut les fichiers de configuration et d'accès aux données
    include_once '../config/Database.php';
    include_once '../models/Employe.php';

    // On instancie la base de données
    $database = new Database();
    $db = $database->getConnection();

    // On instancie les produits
    $employe = new Employe($db);

    // On récupère les données
    $stmt = $employe->lire();

    // On vérifie si on a au moins 1 produit
    if($stmt->rowCount() > 0){
        // On initialise un tableau associatif
        $tableauEmployes = [];
        $tableauEmployes['employe'] = [];

        // On parcourt les produits
        while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
            extract($row);

            $emp = [
                "id_employe" => $id_employe,
                "nom" => $nom,
                "prenom" => $prenom,
                "adresse_domiciliation" => $adresse_domiciliation,
                "numero_compte" => $numero_compte,
                "grade" => $grade,
                "superieur_hierarchique" => $superieur_hierarchique
            ];

            $tableauEmployes['employe'][] = $emp;
        }

        // On envoie le code réponse 200 OK
        http_response_code(200);

        // On encode en json et on envoie
        echo json_encode($tableauEmployes);
    }

}else{
    // On gère l'erreur
    http_response_code(405);
    echo json_encode(["message" => "La méthode n'est pas autorisée"]);
}