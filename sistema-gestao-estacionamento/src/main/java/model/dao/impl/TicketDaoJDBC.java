package model.dao.impl;

import db.DB;
import db.exception.DbException;
import model.dao.TicketDao;
import model.entities.Ticket;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketDaoJDBC implements TicketDao {

    private Connection conn;

    public TicketDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Ticket obj) {

    }

    @Override
    public void update(Ticket obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void novoTicket(Ticket obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO ticket "
                            + "(placa_veiculo, hora_entrada, cancela_entrada, numero_vaga) "
                            + "VALUES "
                            + "(?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getPlacaVeiculo());
            st.setObject(2,obj.getHoraEntrada());
            st.setInt(3, obj.getCancelaEntrada());
            st.setInt(4, obj.getVagaEscolhida());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }else{
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Ticket buscarTicketPorPlaca(String placa) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM ticket " +
                        "WHERE LOWER(placa_veiculo) = LOWER(?) AND hora_saida IS NULL");

            st.setString(1, placa);

            ResultSet rs = st.executeQuery();

            if(rs.next()){
                return new Ticket (
                        rs.getInt("id_ticket"),
                        rs.getObject("hora_entrada" , LocalDateTime.class),
                        rs.getObject("hora_saida", LocalDateTime.class),
                        rs.getInt("cancela_entrada"),
                        rs.getInt("cancela_saida"),
                        rs.getInt("numero_vaga"),
                        rs.getDouble("valor_pagar")

                );
            }
            DB.closeResultSet(rs);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        }finally {
            DB.closeStatement(st);
        }
        return null;
    }

    @Override
    public void atualizarTicket(Ticket ticket) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE ticket SET hora_saida = ?, cancela_saida = ?, valor_pagar = ? " +
                        "WHERE id_ticket = ?");

            st.setObject(1, ticket.getHoraSaida());
            st.setInt(2, ticket.getCancelaSaida());
            st.setDouble(3, ticket.getValorPago());
            st.setInt(4, ticket.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Ticket findById(Integer id) {
        return null;
    }
}
