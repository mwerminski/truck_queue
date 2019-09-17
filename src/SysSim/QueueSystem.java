package SysSim;

import Controller.Controller;
import Exceptions.NoEmptyGateException;
import Exceptions.NoVehicleInMapException;
import Queue.VehicleQueues;
import Vehicle.Vehicle;
import Vehicle.Status;
import java.util.HashMap;

public abstract class QueueSystem {
    VehicleQueues queues;
    Gate entryGate;
    HashMap<Integer, Vehicle> vehiclesMap;
    Controller queuesController;

    public abstract void step();
    public abstract void arrive(Vehicle vehicle) throws NoEmptyGateException;
    public abstract String systemStatus();
    public abstract Integer vehicleWaitingTime(Integer vehicleId) throws NoVehicleInMapException;

    void updateVehicleMap(){
        for(HashMap.Entry<Integer, Vehicle> vehicle : vehiclesMap.entrySet()){
            if(vehicle.getValue().getStatus() == Status.LEAVING){
                vehiclesMap.remove(vehicle);
            }
        }

    }
}
