package ru.tickets.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tickets.domain.Ticket;
import ru.tickets.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    TicketRepository ticketRepository = new TicketRepository();
    Ticket barcelona = new Ticket(1, 600, "MSC", "BRS", 10);
    Ticket barcelona2 = new Ticket(2, 500, "MSC", "BRS", 10);
    Ticket barcelona3 = new Ticket(3, 200, "MSC", "BRS", 10);
    Ticket barcelona4 = new Ticket(4, 450, "MSC", "BRS", 10);
    Ticket cuba = new Ticket(5, 450, "MSC", "CUB", 10);
    Ticket fromSpb = new Ticket(5, 450, "SPB", "CUB", 10);
    TicketManager manager;

    @BeforeEach
    void setup() {
        ticketRepository.save(barcelona);
        ticketRepository.save(barcelona2);
        ticketRepository.save(barcelona3);
        ticketRepository.save(barcelona4);
        ticketRepository.save(cuba);
        ticketRepository.save(fromSpb);
        manager = new TicketManager(ticketRepository);
    }

    @Test
    void testSorted() {
        Ticket[] tickets = manager.findAll("MSC", "BRS");
        assertEquals(tickets.length, 4);
        Ticket mostCheap = tickets[0];
        assertEquals(mostCheap.getId(), 3);
        assertEquals(mostCheap.getCost(), 200);
        assertEquals(mostCheap.getDeparture(), "MSC");
        assertEquals(mostCheap.getDestination(), "BRS");
        assertEquals(mostCheap.getFlightTime(), 10);
    }

    @Test
    public void testFindInEmptyRepository() {
        TicketRepository repository = new TicketRepository();
        manager = new TicketManager(repository);
        Ticket[] tickets = manager.findAll("MSC", "BRS");
        assertEquals(tickets.length, 0);
    }

    @Test
    public void testFindInOneItemRepository() {
        TicketRepository repository = new TicketRepository();
        Ticket ticket = new Ticket(1, 600, "MSC", "BRS", 10);
        repository.save(ticket);
        manager = new TicketManager(repository);
        Ticket[] tickets = manager.findAll("MSC", "BRS");
        assertEquals(tickets.length, 1);
    }

}