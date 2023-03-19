package sr.unasat.services;

import sr.unasat.dao.TaskDAO;
import sr.unasat.entities.Task;

import java.util.List;

public class TaskService {
    private TaskDAO taskDAO = new TaskDAO();

    public List<Task> findAllTasks() {
        return taskDAO.findAllTasks();
    }

    public Task findTaskById(int id) {
        return taskDAO.findTaskById(id);
    }

    public Task insertTask(Task task) {
        return taskDAO.insertTask(task);
    }

    public int updateTask(Task task) {
        return taskDAO.updateTask(task);
    }

    public int deleteTask(int id) {
        return taskDAO.deleteTask(id);
    }
}
