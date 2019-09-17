package Queue;

import Exceptions.EmptyQueueException;
import Exceptions.FullQueueException;
import Exceptions.NoEmptyGateException;
import Vehicle.Vehicle;

public interface VehicleQueue {
    void addVehicleToQueue(Vehicle vehicle) throws FullQueueException;
    boolean isFull();
    Integer getId();
    void updateVehiclesStatus();
    void updateVehiclesEstTime();
    Integer getWaitingTime();
    Vehicle getVehicleAtPosition(Integer position);
    void setVehicleAtPostion(Integer position, Vehicle vehicle);
    Integer getGateTimeleft();
    String getGateInfo();
    Integer getQueueSize();
    void dispatchVehicle();
    boolean isGateEmpty();
    void addVehicleToGate() throws NoEmptyGateException, EmptyQueueException;
}
