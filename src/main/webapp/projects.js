
const API_URL = "http://localhost:8081/javaprojectmanagement_war/api";
const baseUrl = "http://localhost:5500/projectView.html"
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
    console.log(projectListElement);
    for (let index = 0; index < projects.length; index++) {
        const project = projects[index];
        const projectElement = document.createElement("a")
        projectElement.innerHTML = project.projectName
        projectElement.href =  `${baseUrl}?p=${project.projectID}`

        

        projectListElement.append(projectElement)
        
    }

}


const createProject = async (name, desc) => {
    const res = fetch(`${API_URL}/projects/add`, {
        body: JSON.stringify({
            "projectName": name,
            "projectDescription":desc
          }),
        method: "POST",  
        headers: new Headers({'content-type': 'application/json'}),

    })
    getProjects()
}


window.onload = () => {
    document.getElementById("createProjectForm").addEventListener("submit", function(e) {
      e.preventDefault();
      const name = document.getElementById("addName").value.trim()
      const desc = document.getElementById("addDesc").value.trim()
 
      
      createProject(name,desc)
    });
  };

// Navigate to project detail


getProjects()