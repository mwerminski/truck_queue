package Exceptions;

public class FullQueueException extends Exception{
    public FullQueueException(){
        super("The queue is full");
    }
    public FullQueueException(Integer Id){
        super(String.format("The queue ID: %d is full", Id));
    }
}
