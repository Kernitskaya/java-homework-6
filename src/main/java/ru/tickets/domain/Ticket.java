package ru.tickets.domain;

public class Ticket implements Comparable<Ticket> {
    private int id;
    private int cost;
    private String departure;
    private String destination;
    private int flightTime;

    public Ticket(int id, int cost, String departure, String destination, int flightTime) {
        this.id = id;
        this.cost = cost;
        this.departure = departure;
        this.destination = destination;
        this.flightTime = flightTime;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public int getFlightTime() {
        return flightTime;
    }

    @Override
    public int compareTo(Ticket o) {
        return getCost() - o.getCost();
    }
}
