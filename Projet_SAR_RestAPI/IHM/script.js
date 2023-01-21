var editFormData;

var idEmp;

function getFormData() {
    return {
        nom:document.getElementById("nom").value,
        prenom:document.getElementById("prenom").value,
        adresse_domiciliation:document.getElementById("adresse_domiciliation").value,
        numero_compte:document.getElementById("numero_compte").value,
        grade:document.getElementById("grade").value,
        superieur_hierarchique:document.getElementById("superieur_hierarchique").value
    }
}

function clearFormData() {
    document.getElementById("nom").value = "";
    document.getElementById("prenom").value = "";
    document.getElementById("adresse_domiciliation").value = "";
    document.getElementById("numero_compte").value = "";
    document.getElementById("grade").value = "";
    document.getElementById("superieur_hierarchique").value = "";
}

function setFormData(nom, prenom, adresse_domiciliation, numero_compte, grade, superieur_hierarchique) {
    document.getElementById("nom").value = nom;
    document.getElementById("prenom").value = prenom;
    document.getElementById("adresse_domiciliation").value = adresse_domiciliation;
    document.getElementById("numero_compte").value = numero_compte;
    document.getElementById("grade").value = grade;
    document.getElementById("superieur_hierarchique").value = superieur_hierarchique;
}

function setSuccessMessage(message) {
    document.getElementById("message-emp").innerHTML = 
        `<div class="alert alert-primary alert-dismissible fade show">` + message + 
            `<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>`;
}

function addEmployee() {
    let payload = getFormData();
    fetch("http://localhost:81/Projet_SAR_RestAPI/SERVEUR/employe/ajouter.php",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(payload)
    })
    .then((res)=>res.json())
    .then((response)=>{
        getEmployees();
        setSuccessMessage(response.message);
    })
}

function getEmployees() {
    fetch("http://localhost:81/Projet_SAR_RestAPI/SERVEUR/employe/lire.php")
        .then((res)=>res.json())
        .then((response)=>{
            var tmpEmpData = "";
            for (const [key, value] of Object.entries(response)){
                for (const [k, v] of Object.entries(value)){
                    tmpEmpData+= "<tr>"
                    tmpEmpData+= "<td>" + v.id_employe + "</td>";
                    tmpEmpData+= "<td>" + v.nom + "</td>";
                    tmpEmpData+= "<td>" + v.prenom + "</td>";
                    tmpEmpData+= "<td>" + v.adresse_domiciliation + "</td>";
                    tmpEmpData+= "<td>" + v.numero_compte + "</td>";
                    tmpEmpData+= "<td>" + v.grade + "</td>";
                    tmpEmpData+= "<td>" + v.superieur_hierarchique + "</td>";
                    tmpEmpData+= "<td><button class='btn btn-info text-white' onclick='editDataCall(`" + v.id_employe + "`)'>Modifier</button></td>";
                    tmpEmpData+= "<td><button class='btn btn-danger' onclick='DeleteEmployee(`" + v.id_employe + "`)'>Supprimer</button></td>";
                    tmpEmpData+= "<td><button class='btn btn-secondary' onclick='openTaskForm(`" + v.id_employe + "`)'>Ajouter</button></td>";
                    tmpEmpData+= "</tr>";
                }
            }
            document.getElementById("tableData").innerHTML = tmpEmpData;
        });             
} 

function editDataCall(id) {
    fetch("http://localhost:81/Projet_SAR_RestAPI/SERVEUR/employe/lire_id.php", {
        method:"PUT",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify({"id_employe": id})
    })
    .then((res)=>res.json())
    .then((response)=>{
        editFormData = response.employe[0];
        editFormData['id_employe'] = id;
        setFormData(editFormData.nom, editFormData.prenom, editFormData.adresse_domiciliation, editFormData.numero_compte, editFormData.grade, editFormData.superieur_hierarchique);
    })
}

function editData(){
    var formData = getFormData();
    formData['id_employe'] = editFormData.id_employe; 
    fetch("http://localhost:81/Projet_SAR_RestAPI/SERVEUR/employe/modifier.php",{
        method:"PUT",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(formData)
    })
    .then((res)=>res.json())
    .then((response)=>{
        clearFormData(); 
        getEmployees(); 
        setSuccessMessage(response.message);
    })
}

function DeleteEmployee(id) {
    fetch("http://localhost:81/Projet_SAR_RestAPI/SERVEUR/employe/supprimer.php",{
        method:"DELETE",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify({"id_employe": id})
    })
    .then((res)=>res.json())
    .then(
        (response)=>{
            getEmployees();
            setSuccessMessage(response.message);
    })
} 

function submitForm() {
    if(!editFormData) addEmployee();
    else{
        editData();
        editFormData = null;
    } 
}

getEmployees();

/*----*/

function getTaskFormData() {
    return {
        description:document.getElementById("description").value
    }
}

function addTask(id) {
    let taskFormData = getTaskFormData();
    taskFormData['id_employe'] = id;
    fetch("http://localhost:81/Projet_SAR_RestAPI/SERVEUR/tache/ajouter.php",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(taskFormData)
    })
    .then((res)=>res.json())
    .then((response)=>{
        setSuccessMessage(response.message);
    })
}

function openTaskForm(id) {
    document.getElementById("TaskForm").style.display = "block";
    idEmp = id;
}

function closeTaskForm() {
    document.getElementById("TaskForm").style.display = "none";
}

function submitTaskForm() {
    addTask(idEmp); 
}