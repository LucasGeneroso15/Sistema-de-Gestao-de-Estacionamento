package model.dao;

import model.entities.Veiculo;

import java.util.List;

public interface VeiculoDao {
    void gerenciarEntradaSaida(Veiculo obj);
    void cadastrarVeiculo(Veiculo veiculo);
    void update(Veiculo obj);
    void deleteById(Integer id);
    Veiculo procurarPlacaMensalista(String placa);
    void atualizarMensalista(String placa, Integer numeroVaga);
    Veiculo procurarPlacaCaminhao(String placa);
    void atualizarCaminhao(String placa, Integer numeroVaga);
    Boolean procurarPlacaServPub(String placa);
    Veiculo procurarVeiculoPlaca(String placa);
    void removerVeiculoServicoPublico(String placa);
    List<Veiculo> findAll();
}
