package Exceptions;

public class NoVehicleInMapException extends Exception{
    public NoVehicleInMapException(){
        super("Vehicle not found in Map");
    }
    public NoVehicleInMapException(Integer Id){
        super(String.format("Vehicle (ID: %d) not found in Map", Id));
    }
}
