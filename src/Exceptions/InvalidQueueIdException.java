package Exceptions;

public class InvalidQueueIdException extends Exception{
    public InvalidQueueIdException(){
        super("Invalid queue ID");
    }
    public InvalidQueueIdException(Integer Id){
        super(String.format("Invalid queue ID(%d)", Id));
    }
}
