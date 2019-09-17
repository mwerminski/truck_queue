import Controller.AdvancedDoubleQueueStrategy;
import Exceptions.NoEmptyGateException;
import SysSim.BorderCrossingSystem;
import SysSim.SystemLogger;
import Vehicle.Truck;

public class Simulation {
    public final static void start(){
        BorderCrossingSystem borderCrossingSystem = new BorderCrossingSystem( new AdvancedDoubleQueueStrategy());
        Truck truck = new Truck(2);
        Truck truck1 = new Truck(3);
        try {
            borderCrossingSystem.systemStatus();
            borderCrossingSystem.arrive(truck);
//            borderCrossingSystem.arrive(truck1);
            borderCrossingSystem.systemStatus();
            borderCrossingSystem.step();
            borderCrossingSystem.systemStatus();
            borderCrossingSystem.step();
            borderCrossingSystem.systemStatus();
            borderCrossingSystem.step();
            borderCrossingSystem.systemStatus();
            borderCrossingSystem.step();
            borderCrossingSystem.systemStatus();
            borderCrossingSystem.step();
            borderCrossingSystem.systemStatus();
            borderCrossingSystem.step();
            borderCrossingSystem.systemStatus();

//        borderCrossingSystem.arrive(truck1);
//        System.out.println(borderCrossingSystem.vehicleWaitingTime(0));
//        borderCrossingSystem.step();
//        System.out.println(borderCrossingSystem.vehicleWaitingTime(0));
//        borderCrossingSystem.systemStatus();
        }
        catch (NoEmptyGateException e){
            SystemLogger.logException(e);
        }
    }
}
