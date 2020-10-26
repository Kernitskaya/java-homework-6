package ru.tickets.domain.comparators;

import ru.tickets.domain.Ticket;

import java.util.Comparator;

public class TicketFlightTimeComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket o1, Ticket o2) {
        return o1.getFlightTime() - o2.getFlightTime();
    }
}
