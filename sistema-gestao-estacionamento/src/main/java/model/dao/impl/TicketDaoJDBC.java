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
    public Ticket findById(Integer id) {
        return null;
    }
}
