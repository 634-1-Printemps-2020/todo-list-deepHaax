package ch.hesge.cours634.todolist.tasks;

public class InvalidDateException extends RuntimeException {

    public InvalidDateException() {
        super();
    }

    public InvalidDateException(String s) {
        super(s);
    }
}
