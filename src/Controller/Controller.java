package Controller;

import Queue.VehicleQueues;
import SysSim.Gate;


public final class Controller {
    private final QueueStrategy strategy;

    public Controller(QueueStrategy strategy){
        this.strategy = strategy;
    }

    public final void manageQueues(VehicleQueues queues, Gate entryGate){
        strategy.manageQueues(queues, entryGate);
    }

    public final void dispatchVehicles(VehicleQueues queues){
        for(Integer queueId = 0; queueId < queues.getQueuesQuantity(); queueId++){
            queues.getVehicleQueue(queueId).dispatchVehicle();
        }
    }

}
