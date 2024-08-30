package model.dao;

import model.entities.Ticket;

public interface TicketDao {
    void insert(Ticket obj);
    void update(Ticket obj);
    void deleteById(Integer id);
    void novoTicket(Ticket obj);
    Ticket findById(Integer id);
}
