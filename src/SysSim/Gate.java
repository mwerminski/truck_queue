package SysSim;

import Vehicle.Vehicle;

public final class Gate {
    private Vehicle vehicle;
    private Integer timeleft;

    public Gate(){
        timeleft = 0;
        vehicle = null;
    }

    public final void setVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public final void setTime(Integer time){
        timeleft = time;
    }

    public final Vehicle getVehicle(){
        return vehicle;
    }

    public final Integer getTime(){
        return timeleft;
    }

    public final String toString(){
        if(vehicle == null) return String.format("Gate - vehicleID: [Empty] timeleft: [%d] vehicleStatus: [None]", timeleft);
        return String.format("Gate - vehicleID: [%s] timeleft: [%d] vehicleStatus: [%s]", vehicle.getId().toString(), timeleft, vehicle.getStatus().name());
    }
}
