<?php
// Headers requis
header("Access-Control-Allow-Origin: *"); //api public
header("Content-Type: application/json; charset=UTF-8"); //réponse en JSON
header("Access-Control-Allow-Methods: PUT"); //La méthode est accepté par PUT
header("Access-Control-Max-Age: 3600"); //durér de vie de la requête
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

// On vérifie que la méthode utilisée est correcte
if($_SERVER['REQUEST_METHOD'] == 'PUT'){
    // On inclut les fichiers de configuration et d'accès aux données
    include_once '../config/Database.php';
    include_once '../models/Tache.php';

    // On instancie la base de données
    $database = new Database();
    $db = $database->getConnection();

    // On instancie les produits
    $tache = new Tache($db);

    // On récupère les informations envoyées
    $donnees = json_decode(file_get_contents("php://input"));

    if (!empty($donnees->id_tache)) {
        $tache->id_tache = $donnees->id_tache;

        // On récupère les données
        $stmt = $tache->lire_id();

        // On initialise un tableau associatif
        $tableauTaches = [];
        $tableauTaches['tache'] = [];

        // On parcourt les produits
        while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
            extract($row); 

            $tache = [
                "description" => $description,
                "id_employe" => $id_employe
            ];

            $tableauTaches['tache'][] = $tache;
        }

        // On envoie le code réponse 200 OK
        http_response_code(200);

        // On encode en json et on envoie
        echo json_encode($tableauTaches);
    }
}else{
    // On gère l'erreur
    http_response_code(405);
    echo json_encode(["message" => "La méthode n'est pas autorisée"]);
}