package sr.unasat.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sr.unasat.configuration.JPAConfig;
import sr.unasat.entities.Project;

import java.util.List;

public class ProjectDAO {
    private EntityManager entityManager = JPAConfig.getEntityManager();
    private EntityTransaction transaction = entityManager.getTransaction();

    public List<Project> findAllProjects() {
        transaction.begin();
        String jpql = "select p from Project p";
        TypedQuery<Project> query = entityManager.createQuery(jpql, Project.class);
        List<Project> projectList = query.getResultList();
        transaction.commit();
        return projectList;
    }

    public Project findProjectById(int id) {
        transaction.begin();
        String jpql = "select p from Project p where p.id = :id";
        TypedQuery<Project> query = entityManager.createQuery(jpql, Project.class);
        Project project = query.setParameter("id", id).getSingleResult();
        transaction.commit();
        return project;
    }

    public Project insertProject(Project project) {
        transaction.begin();
        entityManager.persist(project);
        transaction.commit();
        return project;
    }

    public int updateProject(Project project) {
        transaction.begin();
        String jpql = "update Project p set p.projectName = :name, p.projectDescription = :description where p.projectID = :id";
        TypedQuery<Project> query = entityManager.createQuery(jpql, Project.class);
        query.setParameter("name", project.getProjectName());
        query.setParameter("description", project.getProjectDescription());
        query.setParameter("id", project.getProjectID());
        int rowsUpdated = query.executeUpdate();
        transaction.commit();
        return rowsUpdated;
    }

    public int deleteProject(int id) {
        transaction.begin();
        String jpql = "delete from Project p where p.id = :id";
        TypedQuery<Project> query = entityManager.createQuery(jpql, Project.class);
        query.setParameter("id", id);
        int rowsDeleted = query.executeUpdate();
        transaction.commit();
        return rowsDeleted;
    }
}
