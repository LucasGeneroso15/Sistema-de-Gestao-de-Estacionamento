package model.dao;

import model.entities.Veiculo;

public interface VeiculoDao {
    void gerenciarEntradaSaida(Veiculo obj);
    void cadastrarVeiculo(Veiculo veiculo);
    Veiculo procurarPlacaMensalista(String placa);
    void atualizarMensalista(String placa, Integer numeroVaga);
    Veiculo procurarPlacaCaminhao(String placa);
    void atualizarCaminhao(String placa, Integer numeroVaga);
    Boolean procurarPlacaServPub(String placa);
    Veiculo procurarVeiculoPlaca(String placa);
    void removerVeiculoServicoPublico(String placa);
}
