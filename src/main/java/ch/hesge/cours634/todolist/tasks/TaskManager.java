package ch.hesge.cours634.todolist.tasks;

import ch.hesge.cours634.todolist.people.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskManager implements TaskService {

    private Map<User, List<Task>> tasks;
    private int lastId = 1;

    public TaskManager() {
        this.tasks = new HashMap<>();
    }

    @Override
    public Task createTask(User owner, String description, Date executionDate) {
        Task task = new Task(lastId++, owner, description, executionDate);

        List<Task> ownerTasks = tasks.get(owner);
        if (ownerTasks == null) {
            ownerTasks = new ArrayList<>();
        }
        ownerTasks.add(task);

        tasks.put(owner, ownerTasks);

        return task;
    }

    @Override
    public void cancelTask(Task task) {
        task.setStatus(TaskStatus.CANCELED);
    }

    @Override
    public void rescheduleTask(Task task, Date date) {
        task.setExecutionDate(date);
    }

    @Override
    public List<Task> getTasks(User owner, TaskStatus status, Date date) {
        List<Task> ownerTasks = tasks.get(owner);
        List<Task> filteredTasks = new ArrayList<>();

        for (Task task : ownerTasks) {

            if (status == null) {
                if (date == null) { // No filtering needed
                    filteredTasks.add(task);
                } else { // Needs filtering by date
                    if (date.equals(task.getExecutionDate())) {
                        filteredTasks.add(task);
                    }
                }
            } else { // Needs filtering by status
                if (date == null) { // Only by status
                    if (status.equals(task.getStatus())) {
                        filteredTasks.add(task);
                    }
                } else { // Filtering by both
                    if (status.equals(task.getStatus()) && date.equals(task.getExecutionDate())) {
                        filteredTasks.add(task);
                    }
                }
            }

        }

        return filteredTasks;
    }

    @Override
    public Task getTask(int id) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Task task = new Task(id, new User(0, "name"), "description", sdf.parse("31/01/2100"));
        for (Map.Entry<User, List<Task>> entry : tasks.entrySet()) {
            int index = entry.getValue().indexOf(task);
            if (index != -1) {
                return entry.getValue().get(index);
            }
        }
        return null;
    }
}
