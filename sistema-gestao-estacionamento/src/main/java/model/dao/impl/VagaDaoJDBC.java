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
    public List<Integer> vagasDisponiveisCadastrados(Integer tamanhoVaga) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Integer> vagas = new ArrayList<>();
        try {
            st = conn.prepareStatement(
                    "SELECT numero_vaga FROM vagas " +
                            "WHERE reservada = TRUE AND status = TRUE " +
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
    public void atualizarVagas(List<Integer> numerosVagas, Boolean reservada, Boolean status) {
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

    @Override
    public void atualizarStatusVagasComuns(int numeroVaga) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE vagas SET status = TRUE " +
                        "WHERE numero_vaga = ?");

            st.setObject(1, numeroVaga);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizarStatusVagasCadastradas(int numeroVaga, String categoriaVeiculo) {
        PreparedStatement st = null;
        try {
            if (categoriaVeiculo.equalsIgnoreCase("MENSALISTA")) {
                st = conn.prepareStatement(
                        "UPDATE vagas SET status = TRUE " +
                                "WHERE numero_vaga = ?");

            } else if (categoriaVeiculo.equalsIgnoreCase("CAMINHAO_ENTREGA")) {
                st = conn.prepareStatement(
                        "UPDATE vagas SET status = TRUE " +
                                "WHERE numero_vaga = ?");
            } else {
                return;
            }
            st.setInt(1, numeroVaga);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
}


