package Exceptions;

public class InvalidPositionException extends RuntimeException{
    public InvalidPositionException(){
        super("Invalid position");
    }
    public InvalidPositionException(Integer position){
        super(String.format("Invalid position (%d)", position));
    }
}
