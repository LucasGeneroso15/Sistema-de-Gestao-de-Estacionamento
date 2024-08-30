package model.dao.impl;

import db.DB;
import db.exception.DbException;
import model.dao.VagaDao;
import model.entities.Vaga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VagaDaoJDBC implements VagaDao {

    private Connection conn;

    public VagaDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Vaga obj, Integer tamanhoVaga) {
        /*
        PreparedStatement st = null;
        try {
            for (int i = 0; i < tamanhoVaga; i++){
                st = conn.prepareStatement(
                        "INSERT INTO vagas "
                                + "(numero_vaga, reservada, status) "
                                + "VALUES "
                                + "(?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);

                st.setInt(1, obj.getNumeroVaga());
                st.setBoolean(2, obj.getReservada());
                st.setBoolean(3, obj.getStatus());

                int rowsAffected = st.executeUpdate();
                if (rowsAffected > 0){
                    ResultSet rs = st.getGeneratedKeys();
                    if(rs.next()){
                        int id = rs.getInt(1);
                        obj.setIdVaga(id);
                    }
                    DB.closeResultSet(rs);
                }else{
                    throw new DbException("Unexpected error! No rows affected!");
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }*/
    }

    @Override
    public void update(Vaga obj) {
        /*
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE seller "
                    + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                    + "WHERE Id = ?");

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());
            st.setInt(6, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }

         */
    }

    @Override
    public void deleteById(Integer id) {
        /*PreparedStatement st = null;
        try {

            st = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");

            st.setInt(1, id);

            st.executeUpdate();

        }catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }*/
    }


    @Override
    public List<Vaga> findAll() {
        return List.of();
    }

    @Override
    public List<Integer> vagasDisponiveis(Integer tamanhoVaga) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Integer> vagas = new ArrayList<>();
        try {
            st = conn.prepareStatement(
                    "SELECT numero_vaga FROM vagas " +
                            "WHERE reservada = FALSE AND status = TRUE " +
                            "ORDER BY numero_vaga ASC");
            rs = st.executeQuery();

            int count = 0;
            int lastNumeroVaga = -1;

            while (rs.next()) {

                int numeroVaga = rs.getInt("numero_vaga");

                if (lastNumeroVaga == -1 || numeroVaga == lastNumeroVaga + 1) {
                    vagas.add(numeroVaga);
                    count++;
                    if (count == tamanhoVaga) {
                        break;
                    }
                } else {
                    vagas.clear();
                    count = 0;
                }
                lastNumeroVaga = numeroVaga;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return vagas;
    }

    @Override
    public void updateVagas(List<Integer> numerosVagas, Boolean reservada, Boolean status) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE vagas " +
                            "SET reservada = ?, status = ? " +
                            "WHERE numero_vaga = ?");

            for (int numeroVaga : numerosVagas) {
                st.setBoolean(1, reservada);
                st.setBoolean(2, status);
                st.setInt(3, numeroVaga);
                st.addBatch();
            }

            int[] rowsAffected = st.executeBatch();
            for (int count : rowsAffected) {
                if (count == 0) {
                    throw new DbException("No rows affected. Check if the vaga exists.");
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
}


