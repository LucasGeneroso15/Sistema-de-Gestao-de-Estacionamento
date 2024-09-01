package model.dao;

import model.entities.Vaga;

import java.util.List;

public interface VagaDao {
    List<Integer> vagasDisponiveis(Integer tamanhoVaga);
    List<Integer> vagasDisponiveisCadastrados(Integer tamanhoVaga);
    void atualizarVagas(List<Integer> numerosVagas, Boolean reservada, Boolean status);
    void atualizarStatusVagasComuns(int numeroVaga);
    void atualizarStatusVagasCadastradas(int numeroVaga, String categoriaVeiculo);
}
