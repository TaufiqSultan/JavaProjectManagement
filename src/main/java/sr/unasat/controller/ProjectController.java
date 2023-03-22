package sr.unasat.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sr.unasat.dao.ProjectDAO;
import sr.unasat.entities.Project;

import java.util.List;

@Path("/projects")
public class ProjectController {
    private ProjectDAO projectDAO = new ProjectDAO();

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> findAllProjects() {
        return projectDAO.findAllProjects();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project findProjectById(@PathParam("id") int id) {
        return projectDAO.findProjectById(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Project addProject(Project project) {
        return projectDAO.insertProject(project);
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProject(Project updatedProject) {
        int rowsUpdated = projectDAO.updateProject(updatedProject);
        if (rowsUpdated == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(updatedProject).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")

    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProject(@PathParam("id") int id) {
        int rowsDeleted = projectDAO.deleteProject(id);
        if (rowsDeleted == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok().build();
        }
    }
}

