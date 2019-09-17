package Vehicle;

public class Truck extends Vehicle{

    public Truck(){
        super();
    }

    public Truck(Integer weight){
        super(weight);
    }

    @Override
    public Integer getWeight() {
        return weight;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    @Override
    public void setEstimatedTime(Integer time) {
        estimatedTime = time;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("Truck ID: %d Estimated time: %d Status: %s", id, estimatedTime, status.name());
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }
}
