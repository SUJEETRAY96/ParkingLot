package controllers;

import dto.GenerateTicketRequestDto;
import dto.GenerateTicketResponseDto;
import dto.ResponseStatus;
import exceptions.InvalidGateIdException;
import exceptions.ParkingSpotNotAvailableException;
import models.Ticket;
import models.VehicleType;
import services.TicketService;

import java.util.Optional;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto request) throws InvalidGateIdException {
        String vehicleNumber = request.getVehicleNumber();
        Long gateId = request.getGateId();
        VehicleType vehicleType = request.getVehicleType();
        GenerateTicketResponseDto responseDto = new GenerateTicketResponseDto();
        Ticket ticket = new Ticket();
        try{
            ticket = ticketService.generateTicket(vehicleNumber,gateId,vehicleType);
        }catch (InvalidGateIdException | ParkingSpotNotAvailableException ex){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMessage("Gate Id is invalid");
            return responseDto;
        }


        responseDto.setTicketId(ticket.getId());
        responseDto.setOperatorName(ticket.getOperator().getName());
        responseDto.setSpotNumber(ticket.getParkingSpot().getNumber());
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);

        return responseDto;
    }
}
