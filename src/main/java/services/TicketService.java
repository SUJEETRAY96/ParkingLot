package services;

import exceptions.InvalidGateIdException;
import exceptions.ParkingSpotNotAvailableException;
import models.*;
import repositories.IGateRepository;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;
import repositories.VehicleRepository;
import stretegy.SpotAssignmentStrategy.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {

    private IGateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private SpotAssignmentStrategy spotAssignmentStrategy;
    private TicketRepository ticketRepository;
    private ParkingLotRepository parkingLotRepository;

    public TicketService(IGateRepository gateRepository,
                         SpotAssignmentStrategy spotAssignmentStrategy,
                         VehicleRepository vehicleRepository,
                         TicketRepository ticketRepository,
                         ParkingLotRepository parkingLotRepository) {
        this.gateRepository = gateRepository;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public Ticket generateTicket(String vehicleNumber,
                                 Long gateId,
                                 VehicleType vehicleType) throws InvalidGateIdException, ParkingSpotNotAvailableException {

        Optional<Gate> gateOptional = gateRepository.findGateById(gateId);
        if(gateOptional.isEmpty()){
            throw new InvalidGateIdException("Invalid Gate Id");
        }
        Gate gate = gateOptional.get();
        Operator operator = gate.getOperator();


        Vehicle vehicle;
        Optional<Vehicle> optionalVehicle = vehicleRepository.findVehicleByNumber(vehicleNumber);
        if(optionalVehicle.isEmpty()){
            vehicle = new Vehicle();
            vehicle.setVehicleNumbers(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            vehicle = vehicleRepository.save(vehicle);
        }else{
            vehicle = optionalVehicle.get();
        }

        Optional<ParkingLot> parkingLotOptional = parkingLotRepository.getParkingLotOfGate(gate);
        ParkingLot parkingLot = parkingLotOptional.get();
        Optional<ParkingSpot> parkingSpotOptional = spotAssignmentStrategy.findSpot(vehicleType,parkingLot,gate);
        ParkingSpot parkingSpot;
        if(parkingSpotOptional.isEmpty()){
            throw new ParkingSpotNotAvailableException("Spot is not available");
        }else {
            parkingSpot = parkingSpotOptional.get();
        }

        Ticket ticket = new Ticket();
        ticket.setGate(gate);
        ticket.setOperator(operator);
        ticket.setVehicle(vehicle);
        ticket.setParkingSpot(parkingSpot);
        ticket.setEntryTime(new Date());

        return ticketRepository.saveTicket(ticket).get();
    }
}
