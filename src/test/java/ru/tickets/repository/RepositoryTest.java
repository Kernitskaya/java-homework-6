package ru.tickets.repository;

import org.junit.jupiter.api.Test;
import ru.tickets.domain.Ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositoryTest {
    TicketRepository repository = new TicketRepository();

    @Test
    public void testEmptyRepository() {
         Ticket[] tickets = repository.findAll();
         assertEquals(tickets.length, 0);
    }

    @Test
    public void testOneItemRepository() {
        Ticket ticket = new Ticket(1);
        repository.save(ticket);
        Ticket[] tickets = repository.findAll();
        assertEquals(tickets.length, 1);
    }
}
