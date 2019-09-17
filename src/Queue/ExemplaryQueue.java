package Queue;

import Exceptions.*;
import SysSim.Gate;
import Vehicle.Vehicle;

import Vehicle.Status;

import java.util.LinkedList;

public final class ExemplaryQueue implements  VehicleQueue{
    private static Integer queueIdCounter = 0;
    private static final Integer TIME_COEFFICIENT = 1;
    private static final Integer VEHICLE_LIMIT = 5;
    private Integer waitingTime = 0;
    private final Integer id;
    private LinkedList<Vehicle> queue;
    private Gate exitGate;

    public ExemplaryQueue(){
        id = queueIdCounter;
        queueIdCounter += 1;
        queue = new LinkedList<Vehicle>();
        exitGate = new Gate();
    }
    @Override
    final public void addVehicleToQueue(Vehicle vehicle) throws FullQueueException{
        if(queue.size() < VEHICLE_LIMIT){
            queue.add(vehicle);
        }
        else throw new FullQueueException(id);
    }
    @Override
    final public void addVehicleToGate() throws NoEmptyGateException, EmptyQueueException{
        if (exitGate.getVehicle() == null && !queue.isEmpty()) {
            queue.getFirst().setStatus(Status.CHECKING);
            exitGate.setTime(queue.getFirst().getWeight() * TIME_COEFFICIENT);
            queue.getFirst().setEstimatedTime(exitGate.getTime());
            exitGate.setVehicle(queue.removeFirst());
        }

        else if(exitGate.getVehicle() == null) throw new NoEmptyGateException("exit");

        else throw new EmptyQueueException(id);
    }
    @Override
    final public void dispatchVehicle(){
        if(exitGate.getTime() == 0 && exitGate.getVehicle() != null) {
            exitGate.setVehicle(null);
            exitGate.setTime(0);
        }
    }
    @Override
    final public boolean isFull(){
        return queue.size() >= VEHICLE_LIMIT;
    }
    @Override
    final public Integer getId(){
        return id;
    }
    @Override
    public final void updateVehiclesStatus(){
        for(Vehicle vehicle : queue){
            if(vehicle.getStatus() != Status.WAITING)
                vehicle.setStatus(Status.WAITING);
        }

        if(exitGate.getVehicle() != null) {
            exitGate.getVehicle().setStatus(exitGate.getTime() == 0 ? Status.LEAVING : Status.CHECKING);
        }

    }
    @Override
    public final void updateVehiclesEstTime(){
        Integer time = exitGate.getTime();

        if(exitGate.getVehicle() != null && exitGate.getTime() > 0){
            exitGate.setTime(time - 1);
            exitGate.getVehicle().setEstimatedTime(time - 1);
        }

        for(Vehicle vehicle : queue){
            vehicle.setEstimatedTime(time);
            time += vehicle.getWeight()* TIME_COEFFICIENT;
        }

        waitingTime = time;
    }
    @Override
    public final Integer getWaitingTime() {
        return waitingTime;
    }
    @Override
    public final Vehicle getVehicleAtPosition(Integer position){
        if(queue.size() >= position && position > 0){
            return queue.get(position - 1);
        }
        else throw new InvalidPositionException(position);
    }
    @Override
    public final void setVehicleAtPostion(Integer position, Vehicle vehicle){
        if(queue.size() <= position && position < 1){
            queue.set(position - 1, vehicle);
        }
        else throw new InvalidPositionException(position);
    }
    @Override
    public final Integer getGateTimeleft(){
        return exitGate.getTime();
    }
    @Override
    public final String getGateInfo(){
        return "Exit "+exitGate.toString();
    }
    @Override
    public final Integer getQueueSize(){
        return queue.size();
    }
    @Override
    public final boolean isGateEmpty() {
        return exitGate.getVehicle() == null;
    }

}
