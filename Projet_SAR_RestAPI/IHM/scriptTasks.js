var editTaskFormData;

function getTaskFormData() {
    return {
        description:document.getElementById("description").value,
        id_employe:document.getElementById("id_employe").value
    }
}

function clearTaskFormData() {
    document.getElementById("description").value = "";
    document.getElementById("id_employe").value = "";
}

function setTaskFormData(description, id_employe) {
    document.getElementById("description").value = description;
    document.getElementById("id_employe").value = id_employe;
}

function getTasks() {
    fetch("http://localhost:81/Projet_SAR_RestAPI/SERVEUR/tache/lire.php")
        .then((res)=>res.json())
        .then((response)=>{
            var tmpTasksData = "";
            for (const [key, value] of Object.entries(response)){
                for (const [k, v] of Object.entries(value)){
                    tmpTasksData+= "<tr>"
                    tmpTasksData+= "<td>" + v.id_tache + "</td>";
                    tmpTasksData+= "<td>" + v.description + "</td>";
                    tmpTasksData+= "<td>" + v.id_employe + "</td>";
                    tmpTasksData+= "<td><button class='btn btn-info text-white' onclick='editTaskDataCall(`" + v.id_tache + "`)'>Modifier</button></td>";
                    tmpTasksData+= "<td><button class='btn btn-danger' onclick='DeleteTask(`" + v.id_tache + "`)'>Supprimer</button></td>";
                    tmpTasksData+= "</tr>";
                }
            }
            document.getElementById("tableTasks").innerHTML = tmpTasksData;
        });             
}  

function addTask() {
    let taskFormData = getTaskFormData();
    fetch("http://localhost:81/Projet_SAR_RestAPI/SERVEUR/tache/ajouter.php",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(taskFormData)
    })
    .then((res)=>res.json())
    .then((response)=>{
        clearTaskFormData();
        getTasks();
        setSuccessMessage(response.message);
    })
}

function editTaskDataCall(id) {
    fetch("http://localhost:81/Projet_SAR_RestAPI/SERVEUR/tache/lire_id.php", {
        method:"PUT",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify({"id_tache": id})
    })
    .then((res)=>res.json())
    .then((response)=>{
        editTaskFormData = response.tache[0];
        editTaskFormData['id_tache'] = id;
        setTaskFormData(editTaskFormData.description, editTaskFormData.id_employe);
    })
}

function editTaskData(){
    var taskFormData = getTaskFormData();
    taskFormData['id_tache'] = editTaskFormData.id_tache; 
    fetch("http://localhost:81/Projet_SAR_RestAPI/SERVEUR/tache/modifier.php",{
        method:"PUT",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(taskFormData)
    })
    .then((res)=>res.json()).then((response)=>{
        setSuccessMessage(response.message);
        clearTaskFormData(); 
        getTasks(); 
    })
}

function DeleteTask(id) {
    fetch("http://localhost:81/Projet_SAR_RestAPI/SERVEUR/tache/supprimer.php",{
        method:"DELETE",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify({"id_tache": id})
    })
    .then((res)=>res.json())
    .then(
        (response)=>{
            setSuccessMessage(response.message);
            getTasks();
    })
}

function submitTaskForm() {
    if(!editTaskFormData) addTask();
    else{
        editTaskData();
        editTaskFormData = null;
    } 
}

function setSuccessMessage(message) {
    document.getElementById("message-tasks").innerHTML = 
        `<div class="alert alert-primary alert-dismissible fade show">` + 
            message + 
            `<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>`;
}

getTasks();