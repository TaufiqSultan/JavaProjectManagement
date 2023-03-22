
const API_URL = "http://localhost:8081/javaprojectmanagement_war/api";

// Get projects
const getProjects = async () => {
    const resp = await fetch(`${API_URL}/projects/list`)
    if (!resp.ok){
        console.error(resp.body)
    }
    const json =await  resp.json()

    console.log(json)
    renderProjects(json)
}


//Render projects to screen
const renderProjects = (projects) => {
    const projectListElement = document.getElementById("projectList");

    for (project in projects){
        const projectElement = document.createElement("p")
        projectElement.innerText = project.projectName;
        projectListElement.append(projectElement)
    }

}



// Navigate to project detail


getProjects()