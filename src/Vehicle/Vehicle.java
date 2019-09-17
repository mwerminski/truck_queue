package Vehicle;

public abstract class Vehicle {
    static private Integer vehicleIdCounter = 0;
    final Integer weight;
    final Integer id;
    Integer estimatedTime;
    Status  status;

    public Vehicle(){
        weight = 1;
        id = vehicleIdCounter;
        vehicleIdCounter += 1;
        estimatedTime = 0;
        status = Status.ARRIVING;
    }

    public Vehicle(Integer weight){
        this.weight = weight;
        id = vehicleIdCounter;
        vehicleIdCounter += 1;
        estimatedTime = 0;
        status = Status.ARRIVING;
    }

    abstract public Integer getWeight();
    abstract public Integer getId();
    abstract public Integer getEstimatedTime();
    abstract public void setEstimatedTime(Integer time);
    abstract public void setStatus(Status status);
    abstract public Status getStatus();
    abstract public String toString();

}
