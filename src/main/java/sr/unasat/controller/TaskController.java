package sr.unasat.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sr.unasat.dao.TaskDAO;
import sr.unasat.entities.Task;
import sr.unasat.entities.User;

import java.util.List;

@Path("/tasks")
public class TaskController {
    private TaskDAO taskDAO = new TaskDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTasks() {
        List<Task> tasks = taskDAO.findAllTasks();
        return Response.ok(tasks).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTask(@PathParam("id") int id) {
        Task task = taskDAO.findTaskById(id);
        return Response.ok(task).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTask(Task task) {
        Task createdTask = taskDAO.insertTask(task);
        return Response.ok(createdTask).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTask(@PathParam("id") int id, Task task) {
        task.setTaskID(id);
        int rowsUpdated = taskDAO.updateTask(task);
        if (rowsUpdated == 1) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") int id) {
        int rowsDeleted = taskDAO.deleteTask(id);
        if (rowsDeleted == 1) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/{taskId}/users")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assignUserToTask(@PathParam("taskId") int taskId, User user) {
        Task task = taskDAO.findTaskById(taskId);
        task.getAssignedUsers().add(user);
        int rowsUpdated = taskDAO.updateTask(task);
        if (rowsUpdated == 1) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    // @DELETE
    // @Path("/{taskId}/users/{userId}")
    // public Response unassignUserFromTask(@PathParam("taskId") int taskId, @PathParam("userId") int userId) {
    //     Task task = taskDAO.findTaskById(taskId);
    //     List<User> assignedUsers = task.getAssignedUsers();
    //     boolean userRemoved = assignedUsers.removeIf(u -> u.getUserID() == userId);
    //     if (userRemoved) {
    //         int rowsUpdated = taskDAO.updateTask(task);
    //         if (rowsUpdated == 1) {
    //             return Response.ok().build();
    //         }
    //     }
    //     return Response.status(Response.Status.BAD_REQUEST).build();
    // }
}
