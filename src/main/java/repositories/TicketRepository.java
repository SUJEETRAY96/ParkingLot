package repositories;

import models.Ticket;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class TicketRepository {
    private Map<Long, Ticket> tickets = new TreeMap<Long,Ticket>();

    long lastSaveId = 0l;

    public Optional<Ticket> saveTicket(Ticket ticket){
        ticket.setId(lastSaveId+1);
        lastSaveId +=1;
        if(tickets.containsKey(ticket)){
            return Optional.of(ticket);
        }
        return Optional.empty();


    }
}
