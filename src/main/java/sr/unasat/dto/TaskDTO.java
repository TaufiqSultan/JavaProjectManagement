package sr.unasat.dto;

import sr.unasat.entities.User;
import sr.unasat.entities.Task;

import java.util.Date;
import java.util.Set;

public class TaskDTO {
    private int id;

    private String taskName;

    private String taskDescription;

    private Date startDate;

    private Date dueDate;

    private String taskStatus;

    private Set<User> assignedUsers;

    public TaskDTO() {
    }

    public TaskDTO(Task task) {
        this.id = task.getTaskID();
        this.taskName = task.getTaskName();
        this.taskDescription = task.getTaskDescription();
        this.startDate = task.getStartDate();
        this.dueDate = task.getDueDate();
        this.taskStatus = task.getTaskStatus();
        this.assignedUsers = task.getAssignedUsers();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Set<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(Set<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", startDate=" + startDate +
                ", dueDate=" + dueDate +
                ", taskStatus='" + taskStatus + '\'' +
                ", assignedUsers=" + assignedUsers +
                '}';
    }
}

