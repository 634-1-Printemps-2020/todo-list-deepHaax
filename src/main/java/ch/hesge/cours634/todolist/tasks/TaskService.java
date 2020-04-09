package ch.hesge.cours634.todolist.tasks;

import ch.hesge.cours634.todolist.people.User;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface TaskService {

    Task createTask(User owner, String description, Date executionDate);
    void cancelTask(Task task);
    void rescheduleTask(Task task, Date date);
    List<Task> getTasks(User owner, TaskStatus status, Date date);
    Task getTask(int id) throws ParseException;

}
