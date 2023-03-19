package sr.unasat.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import sr.unasat.configuration.JPAConfig;
import sr.unasat.dao.ProjectDAO;
import sr.unasat.entities.Project;

import java.util.List;

public class ProjectService {
    private ProjectDAO projectDAO = new ProjectDAO();
    private EntityManager entityManager = JPAConfig.getEntityManager();

    public List<Project> getAllProjects() {
        return projectDAO.findAllProjects();
    }

    public Project getProjectById(int projectId) {
        return projectDAO.findProjectById(projectId);
    }

    public void addProject(Project project) {
        projectDAO.insertProject(project);
    }

    public void updateProject(Project updatedProject) {
        Project existingProject = projectDAO.findProjectById(updatedProject.getProjectID());
        if (existingProject == null) {
            throw new EntityNotFoundException("Project with ID " + updatedProject.getProjectID() + " not found");
        }
        existingProject.setProjectName(updatedProject.getProjectName());
        existingProject.setProjectDescription(updatedProject.getProjectDescription());
        existingProject.setTasks(updatedProject.getTasks());

        entityManager.getTransaction().begin();
        entityManager.merge(existingProject);
        entityManager.getTransaction().commit();
    }

    public void deleteProject(int projectId) {
        Project projectToDelete = projectDAO.findProjectById(projectId);
        if (projectToDelete == null) {
            throw new EntityNotFoundException("Project with ID " + projectId + " not found");
        }

        entityManager.getTransaction().begin();
        entityManager.remove(projectToDelete);
        entityManager.getTransaction().commit();
    }
}
