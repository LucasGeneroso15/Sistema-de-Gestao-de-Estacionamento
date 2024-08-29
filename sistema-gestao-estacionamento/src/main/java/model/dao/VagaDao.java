package model.dao;

import model.entities.Vaga;

import java.util.List;

public interface VagaDao {
    void insert(Vaga obj);
    void update(Vaga obj);
    void deleteById(Integer id);
    Vaga findByNumber(Integer numVaga);
    List<Vaga> findAll();
}
