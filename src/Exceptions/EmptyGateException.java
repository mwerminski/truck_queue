package Exceptions;

public class EmptyGateException extends Exception{
    public EmptyGateException(){
        super("The gate is empty");
    }
    public EmptyGateException(String gateType){
        super(String.format("The %s gate is empty", gateType));
    }
}
