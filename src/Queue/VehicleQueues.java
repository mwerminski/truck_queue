package Queue;

import Exceptions.InvalidPositionException;
import Vehicle.Vehicle;

import java.util.LinkedList;
import java.util.List;

public final class VehicleQueues<T extends VehicleQueue> {
    private List<T> vehicleQueues;

    public VehicleQueues(){
        vehicleQueues = new LinkedList<>();
    }

    public final void addQueue(T queue){
        vehicleQueues.add(queue);
    }

    public final VehicleQueue getVehicleQueue(Integer id){
        return vehicleQueues.get(id);
    }

    public final Integer getQueuesQuantity(){
        return vehicleQueues.size();
    }

    public final void switchVehicles(Integer position, Integer firstQueueId, Integer secondQueueId){
        VehicleQueue tempFirstQueue = vehicleQueues.get(firstQueueId);
        VehicleQueue tempSecondQueue = vehicleQueues.get(secondQueueId);

        if(tempFirstQueue.getQueueSize() > position && tempSecondQueue.getQueueSize() > position && position > 0){
            Vehicle tempVehicle = tempFirstQueue.getVehicleAtPosition(position);
            tempFirstQueue.setVehicleAtPostion(position, tempSecondQueue.getVehicleAtPosition(position));
            tempSecondQueue.setVehicleAtPostion(position, tempVehicle);
        }
        else throw new InvalidPositionException();
    }


}
