package models;

public class Vehicle extends BaseModel{

    private String vehicleNumbers;
    private VehicleType vehicleType;

    public String getVehicleNumbers() {
        return vehicleNumbers;
    }

    public void setVehicleNumbers(String vehicleNumbers) {
        this.vehicleNumbers = vehicleNumbers;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
