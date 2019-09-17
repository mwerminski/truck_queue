import Controller.AdvancedDoubleQueueStrategy;
import Controller.BasicDoubleQueueStrategy;
import Exceptions.NoEmptyGateException;
import SysSim.BorderCrossingSystem;
import SysSim.SystemLogger;
import Vehicle.Truck;

public class Simulation {
    public final static void start(){
        try {
                firstExample();
        }
        catch (NoEmptyGateException e){
            SystemLogger.logException(e);
        }
    }
    private static void firstExample() throws NoEmptyGateException{
        BorderCrossingSystem borderCrossingSystem = new BorderCrossingSystem( new AdvancedDoubleQueueStrategy());

        borderCrossingSystem.systemStatus();
        for(int i = 0; i < 10; i++){
            borderCrossingSystem.arrive(new Truck(12-i));
            borderCrossingSystem.step();
        }
        borderCrossingSystem.step();
        borderCrossingSystem.step();
        borderCrossingSystem.systemStatus();
        borderCrossingSystem.arrive(new Truck(40));
        borderCrossingSystem.step();
        borderCrossingSystem.systemStatus();
        borderCrossingSystem.step();
        borderCrossingSystem.systemStatus();
    }
}
