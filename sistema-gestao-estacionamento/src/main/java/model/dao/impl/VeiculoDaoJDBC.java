package model.dao.impl;

import db.DB;
import db.exception.DbException;
import model.dao.VeiculoDao;
import model.entities.Ticket;
import model.entities.Veiculo;

import java.sql.*;
import java.util.List;

public class VeiculoDaoJDBC implements VeiculoDao {

    private Connection conn;

    public VeiculoDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Veiculo obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO entrada_saida "
                            + "(placa, categoria, tipo, tamanho_vaga) "
                            + "VALUES "
                            + "(?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getPlaca());
            st.setString(2, obj.getCategoriaVeiculo());
            st.setString(3, obj.getTipo());
            st.setInt(4, obj.getTamanhoVaga());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setIdVeiculo(id);
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
    public void update(Veiculo obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Veiculo findByPlace(String placa) {
        return null;
    }

    @Override
    public List<Veiculo> findAll() {
        return List.of();
    }
}
