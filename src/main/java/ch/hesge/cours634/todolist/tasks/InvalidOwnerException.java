package ch.hesge.cours634.todolist.tasks;

public class InvalidOwnerException extends RuntimeException {

    public InvalidOwnerException(){
        super();
    }

    public InvalidOwnerException(String s) {
        super(s);
    }

}
