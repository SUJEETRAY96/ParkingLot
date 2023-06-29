import controllers.TicketController;
import models.Gate;
import models.ParkingLot;
import models.ParkingSpot;
import models.VehicleType;
import repositories.*;
import services.TicketService;
import stretegy.SpotAssignmentStrategy.RandomSpotAssignmentStrategy;
import stretegy.SpotAssignmentStrategy.SpotAssignmentStrategy;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        GateRepository gateRepository = new GateRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        SpotAssignmentStrategy spotAssignmentStrategy = new RandomSpotAssignmentStrategy();
        TicketRepository ticketRepository = new TicketRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();

        TicketService service = new TicketService(gateRepository,
                spotAssignmentStrategy,vehicleRepository,ticketRepository,parkingLotRepository);
        TicketController controller = new TicketController(service);

        System.out.println("Application has started");

    }
}