package model.dao;

import model.entities.Vaga;

import java.util.List;

public interface VagaDao {
    void insert(Vaga obj, Integer tamanhoVaga);
    void update(Vaga obj);
    void deleteById(Integer id);
    List<Integer> vagasDisponiveis(Integer tamanhoVaga);
    void updateVagas(List<Integer> numerosVagas, Boolean reservada, Boolean status);
    List<Vaga> findAll();
}
