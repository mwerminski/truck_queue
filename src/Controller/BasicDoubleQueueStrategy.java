package Controller;

import Exceptions.EmptyQueueException;
import Exceptions.FullQueueException;
import Exceptions.NoEmptyGateException;
import Queue.VehicleQueues;
import SysSim.Gate;
import SysSim.SystemLogger;

public final class BasicDoubleQueueStrategy implements QueueStrategy{
    @Override
    public final void manageQueues(VehicleQueues queues, Gate entryGate) {
        moveVehiclesToGates(queues);
        moveVehicleToQueue(queues, entryGate);
        updateVehiclesStatusAndEstTime(queues);
    }

    private final void moveVehiclesToGates(VehicleQueues queues){
        for(Integer queueId = 0; queueId < queues.getQueuesQuantity(); queueId++){
            if(queues.getVehicleQueue(queueId).isGateEmpty() && !queues.getVehicleQueue(queueId).isFull())
                try {
                    queues.getVehicleQueue(queueId).addVehicleToGate();
                }
                catch(NoEmptyGateException | EmptyQueueException e){
                    SystemLogger.logException(e);
                }
        }
    }

    private final void moveVehicleToQueue(VehicleQueues queues, Gate entryGate){
        if(entryGate.getVehicle() != null){
            for(Integer queueId = 0; queueId < queues.getQueuesQuantity(); queueId++){
                if(!queues.getVehicleQueue(queueId).isFull()){
                    try {
                        queues.getVehicleQueue(queueId).addVehicleToQueue(entryGate.getVehicle());
                    }
                    catch (FullQueueException e){
                        SystemLogger.logException(e);
                    }
                    finally {
                        entryGate.setVehicle(null);
                    }
                    break;
                }
            }
        }
    }

    private final void updateVehiclesStatusAndEstTime(VehicleQueues queues){
        for(Integer queueId = 0; queueId < queues.getQueuesQuantity(); queueId++){
            queues.getVehicleQueue(queueId).updateVehiclesStatus();
            queues.getVehicleQueue(queueId).updateVehiclesEstTime();
        }
    }

}
