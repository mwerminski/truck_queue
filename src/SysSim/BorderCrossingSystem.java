package SysSim;
import Controller.Controller;
import Controller.QueueStrategy;
import Exceptions.EmptyGateException;
import Exceptions.NoEmptyGateException;
import Exceptions.NoVehicleInMapException;
import Queue.ExemplaryQueue;
import Queue.VehicleQueues;
import Vehicle.Vehicle;
import java.util.HashMap;

public final class BorderCrossingSystem extends QueueSystem {

    public BorderCrossingSystem(QueueStrategy strategy){
        entryGate = new Gate();
        vehiclesMap = new HashMap<Integer, Vehicle>();
        queues = new VehicleQueues();
        queuesController = new Controller(strategy);
        //system for 2 queues
        queues.addQueue(new ExemplaryQueue());
        queues.addQueue(new ExemplaryQueue());
    }

    @Override
    public void step() {
        SystemLogger.logInfo("step");
        queuesController.manageQueues(queues, entryGate);
        queuesController.dispatchVehicles(queues);
        updateVehicleMap();
    }

    @Override
    public void arrive(Vehicle vehicle) throws NoEmptyGateException{
        if(entryGate.getVehicle() == null){
            entryGate.setVehicle(vehicle);
            vehiclesMap.put(vehicle.getId(), vehicle);
        }
        else throw new NoEmptyGateException("entry");
    }

    @Override
    public String systemStatus() {
        StringBuilder sysStatus = new StringBuilder();
        sysStatus.append(String.format("\nQuantity of queues: %d ",queues.getQueuesQuantity()));

        if(entryGate.getVehicle() != null){
            sysStatus.append("\nEntry gate: ["+entryGate.getVehicle()+"]");

        }

        for(Integer q = 0; q < queues.getQueuesQuantity(); q++){
            Integer queueSize = queues.getVehicleQueue(q).getQueueSize();
            sysStatus.append("\nQueue " + q.toString() + " Wehicles: " + queueSize.toString());

            for(Integer position = 1; position <= queueSize; position++){
                sysStatus.append("\nposition ["+position.toString()+"]"  + queues.getVehicleQueue(q).getVehicleAtPosition(position));
            }

            sysStatus.append("\n"+queues.getVehicleQueue(q).getGateInfo());
        }
        System.out.println(sysStatus);
        return sysStatus.toString();
    }

    @Override
    public Integer vehicleWaitingTime(Integer vehicleId) throws NoVehicleInMapException{
        SystemLogger.logInfo(String.format("checking vehicle(%d) waiting time",vehicleId));
        if (vehiclesMap.get(vehicleId) != null) return vehiclesMap.get(vehicleId).getEstimatedTime();
        else throw new NoVehicleInMapException(vehicleId);
    }

}
