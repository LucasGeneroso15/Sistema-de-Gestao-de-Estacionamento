package model.dao;

import model.entities.Veiculo;

import java.util.List;

public interface VeiculoDao {
    void gerenciarEntradaSaida(Veiculo obj);
    void update(Veiculo obj);
    void deleteById(Integer id);
    Veiculo procurarPlacaMensalista(String placa);
    Veiculo procurarPlacaCaminhao(String placa);
    Boolean procurarPlacaServPub(String placa);
    List<Veiculo> findAll();
}
