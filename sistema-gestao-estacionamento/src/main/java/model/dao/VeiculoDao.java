package model.dao;

import model.entities.Veiculo;

import java.util.List;

public interface VeiculoDao {
    void insert(Veiculo obj);
    void update(Veiculo obj);
    void deleteById(Integer id);
    Veiculo findByPlace(String placa);
    List<Veiculo> findAll();
}
