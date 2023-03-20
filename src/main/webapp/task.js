const API_URL = "http://localhost:8080/api/tasks";

// Get all tasks
function getTasks() {
    return fetch(API_URL)
        .then(response => response.json())
        .catch(error => console.error('Error:', error));
}

// Get task by ID
function getTask(id) {
    return fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .catch(error => console.error('Error:', error));
}

// Create a new task
function createTask(task) {
    return fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(task)
    })
        .then(response => response.json())
        .catch(error => console.error('Error:', error));
}

// Update a task
function updateTask(id, task) {
    return fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(task)
    })
        .then(response => {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error("Error updating task");
            }
        })
        .catch(error => console.error('Error:', error));
}

// Delete a task by ID
function deleteTask(id) {
    return fetch(`${API_URL}/${id}`, {
        method: "DELETE"
    })
        .then(response => {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error("Error deleting task");
            }
        })
        .catch(error => console.error('Error:', error));
}

// Assign a user to a task
function assignUserToTask(taskId, user) {
    return fetch(`${API_URL}/${taskId}/users`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    })
        .then(response => {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error("Error assigning user to task");
            }
        })
        .catch(error => console.error('Error:', error));
}

// Unassign a user from a task
function unassignUserFromTask(taskId, userId) {
    return fetch(`${API_URL}/${taskId}/users/${userId}`, {
        method: "DELETE"
    })
        .then(response => {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error("Error unassigning user from task");
            }
        })
        .catch(error => console.error('Error:', error));
}
