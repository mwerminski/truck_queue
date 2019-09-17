package Exceptions;

public class NoVehicleException extends Exception{
    public NoVehicleException(){
        super("Vehicle not found");
    }
    public NoVehicleException(Integer Id){
        super(String.format("Vehicle (ID: %d) not found", Id));
    }
}
