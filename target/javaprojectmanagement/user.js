const apiBaseUrl = "http://localhost:8080/api";

// Get all users
async function getAllUsers() {
    const response = await fetch(`${apiBaseUrl}/user/list`);
    const users = await response.json();
    return users;
}

// Get user by ID
async function getUserById(id) {
    const response = await fetch(`${apiBaseUrl}/user/${id}`);
    const user = await response.json();
    return user;
}

// Get user by username
async function getUserByUsername(username) {
    const response = await fetch(`${apiBaseUrl}/user/username/${username}`);
    const user = await response.json();
    return user;
}

// Add a new user
async function addUser(user) {
    const response = await fetch(`${apiBaseUrl}/user/add`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(user),
    });
    const newUser = await response.json();
    return newUser;
}

// Update an existing user
async function updateUser(user) {
    const response = await fetch(`${apiBaseUrl}/user/update`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(user),
    });
    if (response.status === 404) {
        throw new Error("User not found");
    }
    const updatedUser = await response.json();
    return updatedUser;
}

// Delete a user by ID
async function deleteUser(id) {
    const response = await fetch(`${apiBaseUrl}/user/${id}`, {
        method: "DELETE",
    });
    if (response.status === 404) {
        throw new Error("User not found");
    }
}
