package sr.unasat.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sr.unasat.dao.UserDAO;
import sr.unasat.entities.User;

import java.util.List;

@Path("/user")
public class UserController {

    private UserDAO userDAO = new UserDAO();

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findUserById(@PathParam("id") Long id) {
        return userDAO.findUserById(id);
    }

    @GET
    @Path("/username/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findUserByUsername(@PathParam("username") String username) {
        return userDAO.findUserByUsername(username);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertUser(User user) {
        userDAO.insertUser(user);
        return Response.ok(user).build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        int rowsUpdated = userDAO.updateUser(user);
        if (rowsUpdated == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") long id) {
        int rowsDeleted = userDAO.deleteUser(id);
        if (rowsDeleted == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();
    }
}
