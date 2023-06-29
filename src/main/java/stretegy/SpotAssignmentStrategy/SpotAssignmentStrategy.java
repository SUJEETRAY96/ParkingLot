package stretegy.SpotAssignmentStrategy;

import models.Gate;
import models.ParkingLot;
import models.ParkingSpot;
import models.VehicleType;

import java.util.Optional;

public interface SpotAssignmentStrategy {

    public Optional<ParkingSpot> findSpot(VehicleType vehicleType, ParkingLot parkingLot, Gate gate);
}
