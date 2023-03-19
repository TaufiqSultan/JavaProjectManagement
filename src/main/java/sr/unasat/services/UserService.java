package sr.unasat.services;

import sr.unasat.dao.UserDAO;
import sr.unasat.entities.User;

import java.util.List;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    public User findUserById(long id) {
        return userDAO.findUserById(id);
    }

    public User insertUser(User user) {
        return userDAO.insertUser(user);
    }

    public int updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public int deleteUser(long id) {
        return userDAO.deleteUser(id);
    }

    // public List<User> findAllUsersByTask(int taskId) {
    //     TaskService taskService = new TaskService();
    //     return taskService.findTaskById(taskId).getAssignedUsers();
    // }
}
