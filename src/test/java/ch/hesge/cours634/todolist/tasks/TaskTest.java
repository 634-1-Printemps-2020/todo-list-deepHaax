package ch.hesge.cours634.todolist.tasks;


import ch.hesge.cours634.todolist.people.User;
import org.junit.Assert;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskTest {

    @Test
    public void constructor() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date nextMonth = sdf.parse("30/03/2020");
        Task task = new Task(1, new User(1, "Flavio"), "Description", nextMonth);

        Assert.assertEquals(task.getOwner().getName(), "Flavio");
        Assert.assertEquals(task.getDescription(), "Description");
        Assert.assertEquals(task.getExecutionDate(), nextMonth);
    }

    @Test(expected = InvalidDateException.class)
    public void noNullExecutionDate() {
        new Task(1, new User(1, "name"), "description", null);
    }

    @Test(expected = InvalidDateException.class)
    public void noDateExecutionDateBeforeToday() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date yesterday = sdf.parse("25/02/2020");
        new Task(1, new User(1, "name"), "description", yesterday);
    }

    @Test(expected = InvalidOwnerException.class)
    public void noNullOwner() {
        new Task(1, null, "description", new Date());
    }

    @Test
    public void getSetOwner() {
        Task task = getNewTask();

        task.setOwner(new User(1, "Flavio"));
        Assert.assertEquals("Flavio", task.getOwner().getName());
    }

    @Test(expected = InvalidOwnerException.class)
    public void noSetNullOwner() {
        Task task = getNewTask();
        task.setOwner(null);
    }

    @Test
    public void getSetDescription() {
        Task task = getNewTask();

        task.setDescription("Task description");
        Assert.assertEquals("Task description", task.getDescription());
    }

    @Test
    public void getSetDate() throws ParseException {
        Task task = getNewTask();

        Date today = new Date();
        task.setExecutionDate(today);
        Assert.assertEquals(today, task.getExecutionDate());
    }

    @Test(expected = InvalidDateException.class)
    public void noSetExecutionDateBeforeToday() throws ParseException {
        Task task = getNewTask();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final Date date = sdf.parse("25/02/2020");

        task.setExecutionDate(date);
    }

    @Test
    public void getSetStatus() {
        Task task = getNewTask();

        task.setStatus(TaskStatus.OPEN);
        Assert.assertEquals(TaskStatus.OPEN, task.getStatus());
    }

    @Test
    public void getSetResolution() {
        Task task = getNewTask();

        task.setResolution(TaskResolution.DONE);
        Assert.assertEquals(TaskResolution.DONE, task.getResolution());
    }

    public Task getNewTask() {
        return new Task(1, new User(1, "Flavio"), "Description", new Date());
    }

}
