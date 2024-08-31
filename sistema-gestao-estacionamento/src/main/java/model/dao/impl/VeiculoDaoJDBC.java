package model.dao.impl;

import db.DB;
import db.exception.DbException;
import model.dao.VeiculoDao;
import model.entities.Veiculo;

import java.sql.*;
import java.util.List;

public class VeiculoDaoJDBC implements VeiculoDao {

    private Connection conn;

    public VeiculoDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void gerenciarEntradaSaida(Veiculo obj) {
        PreparedStatement st = null;
        try {
            if(!obj.getCategoriaVeiculo().equalsIgnoreCase("SERVICO_PUBLICO")){
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
            }else{
                st = conn.prepareStatement(
                        "INSERT INTO entrada_saida "
                                + "(placa, categoria, tipo) "
                                + "VALUES "
                                + "(?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);

                st.setString(1, obj.getPlaca());
                st.setString(2, obj.getCategoriaVeiculo());
                st.setString(3, obj.getTipo());

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
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void cadastrarVeiculo(Veiculo veiculo) {
        PreparedStatement st = null;
        try {
            if(veiculo.getCategoriaVeiculo().equalsIgnoreCase("MENSALISTA")){
                st = conn.prepareStatement( "INSERT INTO veiculos_cadastrados " +
                        "(placa, tipo, categoria, valor_pagar, tamanho_vaga) " +
                        "VALUES (?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);

                st.setString(1, veiculo.getPlaca());
                st.setString(2, veiculo.getTipo());
                st.setString(3, veiculo.getCategoriaVeiculo());
                st.setDouble(4, veiculo.getValorPagar());
                st.setInt(5, veiculo.getTamanhoVaga());

                int rowsAffected = st.executeUpdate();

                if (rowsAffected > 0){
                    ResultSet rs = st.getGeneratedKeys();
                    if(rs.next()){
                        int id = rs.getInt(1);
                        veiculo.setIdVeiculo(id);
                    }
                    DB.closeResultSet(rs);
                }
            }else {
                st = conn.prepareStatement("INSERT INTO veiculos_cadastrados " +
                        "(placa, tipo, categoria, tamanho_vaga) " +
                        "VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);

                st.setString(1, veiculo.getPlaca());
                st.setString(2, veiculo.getTipo());
                st.setString(3, veiculo.getCategoriaVeiculo());
                st.setInt(4, veiculo.getTamanhoVaga());

                int rowsAffected = st.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet rs = st.getGeneratedKeys();
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        veiculo.setIdVeiculo(id);
                    }
                    DB.closeResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
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
    public Veiculo procurarPlacaMensalista(String placa) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM veiculos_cadastrados " +
                        "WHERE LOWER(placa) = LOWER(?) AND categoria = 'MENSALISTA'");

            st.setString(1, placa);
            rs = st.executeQuery();

            if (rs.next()) {
                return new Veiculo(
                        rs.getInt("id_veiculo"),
                        rs.getString("placa"),
                        rs.getString("tipo"),
                        rs.getString("categoria"),
                        rs.getInt("tamanho_vaga"),
                        rs.getInt("numero_vaga")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizarMensalista(String placa, Integer numeroVaga) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE veiculos_cadastrados SET numero_vaga = ? " +
                            "WHERE LOWER(placa) = LOWER(?) AND categoria = 'MENSALISTA'");

            st.setInt(1, numeroVaga);
            st.setString(2, placa);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("Nenhum veículo mensalista encontrado com a placa fornecida.");
            } else {
                System.out.println("Número da vaga atualizado com sucesso.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            // Fechar statement
            DB.closeStatement(st);
        }
    }

    @Override
    public Veiculo procurarPlacaCaminhao(String placa) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM veiculos_cadastrados " +
                            "WHERE LOWER(placa) = LOWER(?) AND categoria = 'CAMINHAO_ENTREGA'");

            st.setString(1, placa);
            rs = st.executeQuery();

            if (rs.next()) {
                return new Veiculo(
                        rs.getInt("id_veiculo"),
                        rs.getString("placa"),
                        rs.getString("tipo"),
                        rs.getString("categoria"),
                        rs.getInt("tamanho_vaga"),
                        rs.getInt("numero_vaga")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizarCaminhao(String placa, Integer numeroVaga) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE veiculos_cadastrados SET numero_vaga = ? " +
                            "WHERE LOWER(placa) = LOWER(?) AND categoria = 'CAMINHAO_ENTREGA'");

            st.setInt(1, numeroVaga);
            st.setString(2, placa);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("Nenhum caminhão de entrega encontrado com a placa fornecida.");
            } else {
                System.out.println("Número da vaga atualizado com sucesso.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public Boolean procurarPlacaServPub(String placa) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT COUNT(*) AS count FROM entrada_saida " +
                        "WHERE LOWER(placa) = LOWER(?) AND categoria = 'SERVICO_PUBLICO'");

            st.setString(1, placa);
            rs = st.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return false;
    }

    @Override
    public Veiculo procurarVeiculoPlaca(String placa) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM entrada_saida WHERE LOWER(placa) = LOWER(?)");
            st.setString(1, placa);
            rs = st.executeQuery();
            if (rs.next()) {
                return new Veiculo(
                        rs.getString("placa"),
                        rs.getString("tipo"),
                        rs.getString("categoria"),
                        rs.getInt("tamanho_vaga")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void removerVeiculoServicoPublico(String placa) {
        PreparedStatement st = null;
        try {
            String sql = "DELETE FROM entrada_saida WHERE LOWER(placa) = LOWER(?) AND categoria = 'SERVICO_PUBLICO'";
            st = conn.prepareStatement(sql);
            st.setString(1, placa);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("Nenhum veículo de serviço público encontrado para a placa fornecida.");
            } else {
                System.out.println("Veículo de serviço público removido do local com sucesso.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Veiculo> findAll() {
        return List.of();
    }
}
