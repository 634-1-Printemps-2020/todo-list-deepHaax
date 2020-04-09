package ch.hesge.cours634.todolist.tasks;

import ch.hesge.cours634.todolist.people.User;

import java.util.Date;
import java.util.Objects;

public class Task {

    private int id;
    private User owner;
    private String description;
    private Date executionDate;
    private TaskStatus status;
    private TaskResolution resolution;

    public Task(int id, User owner, String description, Date executionDate) {


        validateOwner(owner);

        validateDate(executionDate);

        this.id = id;
        this.owner = owner;
        this.description = description;
        this.executionDate = executionDate;
        this.status = TaskStatus.OPEN;
        // No default resolution
    }

    private void validateOwner(User owner) {
        if (owner == null) {
            throw new InvalidOwnerException("Owner cannot be empty in order to create a task.");
        }
    }

    private void validateDate(Date executionDate) {
        if (executionDate == null) {
            throw new InvalidDateException("Execution date needs to be defined. Not null.");
        }
        if (executionDate.before(new Date())) {
            throw new InvalidDateException("Execution date cannot be defined as being in the past.");
        }
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        validateOwner(owner);
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        validateDate(executionDate);
        this.executionDate = executionDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskResolution getResolution() {
        return resolution;
    }

    public void setResolution(TaskResolution resolution) {
        this.resolution = resolution;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
