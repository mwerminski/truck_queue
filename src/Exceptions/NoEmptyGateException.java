package Exceptions;

public class NoEmptyGateException extends Exception{
    public NoEmptyGateException(){
        super("The gate is not empty");
    }
    public NoEmptyGateException(String gateType){
        super(String.format("The %s gate is not empty", gateType));
    }
}
