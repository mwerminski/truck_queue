package Controller;

import Queue.VehicleQueues;
import SysSim.Gate;


public interface QueueStrategy {
    void manageQueues(VehicleQueues queues, Gate entryGate);
}
