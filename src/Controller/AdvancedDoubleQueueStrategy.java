package Controller;

import Exceptions.EmptyQueueException;
import Exceptions.FullQueueException;
import Exceptions.NoEmptyGateException;
import Queue.VehicleQueue;
import Queue.VehicleQueues;
import SysSim.Gate;
import SysSim.SystemLogger;

public final class AdvancedDoubleQueueStrategy implements QueueStrategy{

    @Override
    public void manageQueues(VehicleQueues queues, Gate entryGate) {
        moveVehiclesToGates(queues);
        moveVehicleToFasterQueue(queues, entryGate);
        optimizeQueues(queues);
        updateVehiclesStatusAndEstTime(queues);
    }

    private final void moveVehiclesToGates(VehicleQueues queues) {
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

    private final void moveVehicleToFasterQueue(VehicleQueues queues, Gate entryGate){
        if(entryGate.getVehicle() != null){
            VehicleQueue tempQueue = queues.getVehicleQueue(0).getWaitingTime() < queues.getVehicleQueue(1).getWaitingTime()
                    ? queues.getVehicleQueue(0) : queues.getVehicleQueue(1);
            try {
                tempQueue.addVehicleToQueue(entryGate.getVehicle());
            }
            catch(FullQueueException e){
                SystemLogger.logException(e);
            }
            finally {
                entryGate.setVehicle(null);
            }
        }
    }

    private final void optimizeQueues(VehicleQueues queues){
        final VehicleQueue tempFirstQueue = queues.getVehicleQueue(0);
        final VehicleQueue tempSecondQueue = queues.getVehicleQueue(1);

        final float meanTime = ((tempFirstQueue.getWaitingTime().floatValue() + tempSecondQueue.getWaitingTime().floatValue())/2);
        final float deltaTime = Math.abs(tempFirstQueue.getWaitingTime().floatValue() - tempSecondQueue.getWaitingTime().floatValue());
        final float treshhold = 30f;

        if((100 * deltaTime / meanTime) > treshhold ){
            final int shorterQueueId = tempFirstQueue.getQueueSize() < tempSecondQueue.getQueueSize() ? 0 : 1;
            final int lowerLoadedQueueId = tempFirstQueue.getWaitingTime() < tempSecondQueue.getWaitingTime() ? 0 : 1;
            final int moreLoadedQueueId = tempFirstQueue.getWaitingTime() > tempSecondQueue.getWaitingTime() ? 0 : 1;

            for(Integer vehiclePosition = 2; vehiclePosition < queues.getVehicleQueue(shorterQueueId).getQueueSize(); vehiclePosition++){
                if(queues.getVehicleQueue(moreLoadedQueueId).getVehicleAtPosition(vehiclePosition).getWeight() >
                    queues.getVehicleQueue(lowerLoadedQueueId).getVehicleAtPosition(vehiclePosition).getWeight()){

                        queues.switchVehicles(vehiclePosition, lowerLoadedQueueId, moreLoadedQueueId);
                        optimizeQueues(queues);
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
