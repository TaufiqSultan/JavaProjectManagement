
const API_URL = "http://localhost:8081/javaprojectmanagement_war/api";
const baseUrl = "http://localhost:5500"


const urlSearchParams = new URLSearchParams(window.location.search);
const params = Object.fromEntries(urlSearchParams.entries());
const currentProjectId = params.p






const getProject = async () => {
  const res = await fetch(`${API_URL}/projects/${currentProjectId}`)
  const project = await res.json()
  console.log(project);
  renderProject(project);
}


const deleteProject = async () => {
  const res = await fetch(`${API_URL}/projects/delete/${currentProjectId}`, {
    method: "DELETE"
  });
  window.location.replace(`${baseUrl}/projects.html`);

  console.log(res);
}

const updateProject = async (name, desc) => {
  const res = await fetch(`${API_URL}/projects/update/${currentProjectId}`, {
    method: "PUT",
    body: JSON.stringify({
      "projectName": name,
      "projectDescription":desc
    }),
  headers: new Headers({'content-type': 'application/json'}),


  });
  document.getElementById("projectName").innerText = name
  document.getElementById("projectDescription").innerText = desc
}

const deleteTask = async (id) => {
  if(!confirm("Are you sure")) return;
  const res = await fetch(`${API_URL}/tasks/${id}`, {
    method: "DELETE"
  });
  console.log(res);
  if(res.ok){
    const projectTaskListElement = document.getElementById("projectTaskList")
    const elList = document.querySelectorAll(`[data-id]`);
       elList.forEach(el => {
        if(el.dataset.id == id){
          projectTaskListElement.remove(el)
        }
    });
  }
}

const createTask = async (name, desc, start, due) => {
  const res = await fetch(`${API_URL}/tasks/add`, {method: "POST", body: JSON.stringify({
    taskName: name,
    taskDescription: desc,
    startDate: start,
    dueDate: due,
    taskStatus: "Pending",
    project: {
      projectID : currentProjectId
    },
    assignedUsers: [{
      userID: 1
    }]
  }),        
  headers: new Headers({'content-type': 'application/json'}),
})
}


const renderProject = (project) => {
  console.log(project);
  const projectTitleElement = document.getElementById("projectName")
  const projectDescription = document.getElementById("projectDescription")
  const projectTaskListElement = document.getElementById("projectTaskList")
  const deleteBtn = document.getElementById("deleteProjectBtn")
  projectTitleElement.innerText = project.projectName
  projectDescription.innerText = project.projectDescription

  for (let i = 0; i < project.tasks.length; i++) {
    const task = project.tasks[i];
    projectTaskListElement.append(createProjectTask(task))
    
  }



  deleteBtn.addEventListener("click", deleteProject)
}

const createProjectTask = (task) => {
  const taskElement = document.createElement("li")
  const startDate = document.createElement("p")
  const dueDate = document.createElement("p")
  const description = document.createElement("p")
  const name = document.createElement("p")
  const deleteBtn = document.createElement("button")
  deleteBtn.classList.add("btn", "bg-gradient-primary")

  //Set values
  startDate.innerText = task.startDate
  dueDate.innerText = task.dueDate
  description.innerText = task.taskDescription
  name.innerText = task.taskName
  deleteBtn.innerText = "DELETE"
  deleteBtn.addEventListener("click", () => deleteTask(task.taskID))
  taskElement.dataset.id = task.taskID


  taskElement.append(name, description,startDate, dueDate, deleteBtn)
  return taskElement
}


window.onload = () => {
  document.getElementById("addTaskForm").addEventListener("submit", function(e) {
    e.preventDefault();
    const name = document.getElementById("addName").value.trim()
    const desc = document.getElementById("addDesc").value.trim()
    const start = document.getElementById("addStart").value.trim()
    const due = document.getElementById("addDue").value.trim()
    
    createTask(name,desc,start,due)
  });

  document.getElementById("editProjectForm").addEventListener("submit", function(e) {
    e.preventDefault();
    const name = document.getElementById("ediProjecttName").value.trim()
    const desc = document.getElementById("ediProjecttDesc").value.trim()

    
    updateProject(name,desc)
  });
};

getProject()









