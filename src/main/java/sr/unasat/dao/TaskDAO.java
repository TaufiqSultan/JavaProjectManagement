package sr.unasat.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import sr.unasat.configuration.JPAConfig;
import sr.unasat.entities.Task;
import sr.unasat.entities.User;

import java.util.List;
import java.util.Set;

public class TaskDAO {
    private EntityManager entityManager = JPAConfig.getEntityManager();
    private EntityTransaction transaction = entityManager.getTransaction();

    public List<Task> findAllTasks() {
        transaction.begin();
        String jpql = "select t from Task t";
        TypedQuery<Task> query = entityManager.createQuery(jpql, Task.class);
        List<Task> taskList = query.getResultList();
        transaction.commit();
        return taskList;
    }

    public Task findTaskById(int id) {
        transaction.begin();
        String jpql = "select t from Task t left join fetch t.assignedUsers where t.id = :id";
        TypedQuery<Task> query = entityManager.createQuery(jpql, Task.class);
        query.setParameter("id", id);
        Task task = query.getSingleResult();
        transaction.commit();
        return task;
    }

    public Task insertTask(Task task) {
        transaction.begin();
        entityManager.persist(task);
        transaction.commit();
        return task;
    }

    public int updateTask(Task task) {
        transaction.begin();
        String jpql = "update Task t set t.taskName = :taskName, t.taskDescription = :taskDescription, t.startDate = :startDate, t.dueDate = :dueDate, t.taskStatus = :taskStatus where t.id = :id";
        TypedQuery<Task> query = entityManager.createQuery(jpql, Task.class);
        query.setParameter("taskName", task.getTaskName());
        query.setParameter("taskDescription", task.getTaskDescription());
        query.setParameter("startDate", task.getStartDate());
        query.setParameter("dueDate", task.getDueDate());
        query.setParameter("taskStatus", task.getTaskStatus());
        query.setParameter("id", task.getTaskID());
        int rowsUpdated = query.executeUpdate();
        transaction.commit();
        return rowsUpdated;
    }

    public int deleteTask(int id) {
        transaction.begin();
        String jpql = "delete from Task t where t.id = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);
        int rowsDeleted = query.executeUpdate();
        transaction.commit();
        return rowsDeleted;
    }

    public void updateTaskAssignee(Task task, Set<User> assignedUsers) {
        transaction.begin();
        Task managedTask = entityManager.find(Task.class, task.getTaskID());
        managedTask.setAssignedUsers(assignedUsers);
        entityManager.merge(managedTask);
        transaction.commit();
    }
}
