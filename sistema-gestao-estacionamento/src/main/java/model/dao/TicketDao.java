package model.dao;

import model.entities.Ticket;

public interface TicketDao {
    void novoTicket(Ticket obj);
    Ticket buscarTicketPorPlaca(String placa);
    void atualizarTicket(Ticket ticket);
}
