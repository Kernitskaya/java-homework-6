package ru.tickets.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tickets.domain.Ticket;
import ru.tickets.domain.comparators.TicketFlightTimeComparator;
import ru.tickets.domain.comparators.TicketPriceComparator;
import ru.tickets.repository.TicketRepository;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    TicketRepository ticketRepository = new TicketRepository();
    Ticket barcelona = new Ticket(1, 600, "MSC", "BRS", 10);
    Ticket barcelona2 = new Ticket(2, 500, "MSC", "BRS", 40);
    Ticket barcelona3 = new Ticket(3, 200, "MSC", "BRS", 30);
    Ticket barcelona4 = new Ticket(4, 450, "MSC", "BRS", 5);
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
    void testPriceSorted() {
        Comparator<Ticket> priceComparator = new TicketPriceComparator();
        Ticket[] tickets = manager.findAll("MSC", "BRS", priceComparator);
        assertEquals(tickets.length, 4);
        Ticket mostCheap = tickets[0];
        assertEquals(mostCheap.getId(), 3);
        assertEquals(mostCheap.getCost(), 200);
        assertEquals(mostCheap.getDeparture(), "MSC");
        assertEquals(mostCheap.getDestination(), "BRS");
        assertEquals(mostCheap.getFlightTime(), 30);
    }

    @Test
    void testFlightTimeSorted() {
        Comparator<Ticket> flightTimeComparator = new TicketFlightTimeComparator();
        Ticket[] tickets = manager.findAll("MSC", "BRS", flightTimeComparator);
        assertEquals(tickets.length, 4);
        Ticket mostCheap = tickets[0];
        assertEquals(mostCheap.getId(), 4);
        assertEquals(mostCheap.getCost(), 450);
        assertEquals(mostCheap.getDeparture(), "MSC");
        assertEquals(mostCheap.getDestination(), "BRS");
        assertEquals(mostCheap.getFlightTime(), 5);
    }

    @Test
    public void testFindInEmptyRepository() {
        Comparator<Ticket> flightTimeComparator = new TicketFlightTimeComparator();
        TicketRepository repository = new TicketRepository();
        manager = new TicketManager(repository);
        Ticket[] tickets = manager.findAll("MSC", "BRS", flightTimeComparator);
        assertEquals(tickets.length, 0);
    }

    @Test
    public void testFindInOneItemRepository() {
        Comparator<Ticket> flightTimeComparator = new TicketFlightTimeComparator();
        TicketRepository repository = new TicketRepository();
        Ticket ticket = new Ticket(1, 600, "MSC", "BRS", 10);
        repository.save(ticket);
        manager = new TicketManager(repository);
        Ticket[] tickets = manager.findAll("MSC", "BRS", flightTimeComparator);
        assertEquals(tickets.length, 1);
    }

}