package Exceptions;

public class EmptyQueueException extends Exception{
    public EmptyQueueException(){
        super("The queue is empty");
    }
    public EmptyQueueException(Integer Id){
        super(String.format("The queue ID: %d is empty", Id));
    }
}
