package model.dao;

import model.entities.Vaga;

import java.util.List;

public interface VagaDao {
    void insert(Vaga obj, Integer tamanhoVaga);
    void update(Vaga obj);
    void deleteById(Integer id);
    List<Integer> vagasDisponiveis(Integer tamanhoVaga);
    List<Integer> vagasDisponiveisCadastrados(Integer tamanhoVaga);
    void atualizarVagas(List<Integer> numerosVagas, Boolean reservada, Boolean status);
    void atualizarStatusVagasComuns(int numeroVaga);
    void atualizarStatusVagasCadastradas(int numeroVaga, String categoriaVeiculo);
    List<Vaga> findAll();
}
