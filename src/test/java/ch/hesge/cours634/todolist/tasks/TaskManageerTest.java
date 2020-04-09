package ch.hesge.cours634.todolist.tasks;

import ch.hesge.cours634.todolist.people.User;
import org.junit.Assert;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskManageerTest {

    @Test
    public void createTask() throws ParseException {
        TaskManager storage = new TaskManager();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date nextMonth = sdf.parse("30/03/2020");

        Task task = storage.createTask(new User(1, "Flavio"), "Task description", nextMonth);

        Assert.assertEquals(task.getOwner().getName(), "Flavio");
        // Tester les autres champs si envie
    }

    @Test
    public void cancelTask() throws ParseException {
        TaskManager storage = new TaskManager();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date nextMonth = sdf.parse("30/03/2020");
        Task task = new Task(1, new User(1, "Flavio"), "Description", nextMonth);

        storage.cancelTask(task);

        Assert.assertEquals(TaskStatus.CANCELED, task.getStatus());
    }

    @Test
    public void rescheduleTask() throws ParseException {
        TaskManager storage = new TaskManager();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date nextMonth = sdf.parse("30/03/2020");
        Task task = new Task(1, new User(1, "Flavio"), "Description", nextMonth);
        Date newDate = sdf.parse("25/03/2020");
        storage.rescheduleTask(task, newDate);

        Assert.assertEquals(newDate, task.getExecutionDate());

    }

    @Test
    public void getTasks() throws ParseException {
        TaskManager storage = new TaskManager();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date date1 = sdf.parse("30/03/2020");
        Date date2 = sdf.parse("30/04/2020");

        User owner1 = new User(1, "Flavio");
        User owner2 = new User(2, "Alex");

        Task task1 = storage.createTask(owner1, "Creating a new database", date1);
        Task task2 = storage.createTask(owner1, "Refactoring this code", date2);

        Task task3 = storage.createTask(owner2, "Creating test", date1);
        Task task4 = storage.createTask(owner2, "Test new SonarQube rules", date2);

        Assert.assertEquals(2, storage.getTasks(owner1, null, null).size());
        Assert.assertEquals(2, storage.getTasks(owner2, null, null).size());

        Assert.assertEquals(2, storage.getTasks(owner1, TaskStatus.OPEN, null).size());
        task2.setStatus(TaskStatus.CLOSED);
        Assert.assertEquals(1, storage.getTasks(owner1, TaskStatus.OPEN, null).size());


        Assert.assertEquals(1, storage.getTasks(owner1, null, date1).size());
        Assert.assertEquals(0, storage.getTasks(owner1, TaskStatus.CLOSED, date1).size());


    }

    @Test
    public void getTask() throws ParseException {
        TaskManager storage = new TaskManager();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date date1 = sdf.parse("30/03/2020");
        Date date2 = sdf.parse("30/04/2020");

        User owner1 = new User(1, "Flavio");
        User owner2 = new User(2, "Alex");

        storage.createTask(owner1, "Creating a new database", date1);
        storage.createTask(owner1, "Refactoring this code", date2);

        storage.createTask(owner2, "Creating test", date1);
        storage.createTask(owner2, "Test new SonarQube rules", date2);

        Task task = storage.getTask(1);
        Assert.assertEquals(date1, task.getExecutionDate());
        Assert.assertEquals(1, task.getId());
    }
}
