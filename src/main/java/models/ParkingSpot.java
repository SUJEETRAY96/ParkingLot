package models;

import java.util.List;

public class ParkingSpot extends BaseModel{
    private int number;
    private List<VehicleType> sportedVehicle;

    private SpotStatus spotStatus;
    private Vehicle vehicle;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<VehicleType> getSportedVehicle() {
        return sportedVehicle;
    }

    public void setSportedVehicle(List<VehicleType> sportedVehicle) {
        this.sportedVehicle = sportedVehicle;
    }

    public SpotStatus getSpotStatus() {
        return spotStatus;
    }

    public void setSpotStatus(SpotStatus spotStatus) {
        this.spotStatus = spotStatus;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
