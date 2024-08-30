package model.entities;

import model.entities.enums.CategoriaVeiculo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Veiculo implements Serializable {
    private Integer idVeiculo;
    private String placa;
    private String tipo;
    private String categoriaVeiculo;
    private Double valorPagar;
    private Integer tamanhoVaga;

    public Veiculo(Integer idVeiculo, String placa, String tipo, String categoriaVeiculo, Integer tamanhoVaga) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.tipo = tipo;
        this.categoriaVeiculo = categoriaVeiculo;
        this.tamanhoVaga = tamanhoVaga;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoriaVeiculo() {
        return categoriaVeiculo;
    }

    public void setCategoriaVeiculo(String categoriaVeiculo) {
        this.categoriaVeiculo = categoriaVeiculo;
    }

    public Double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(Double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public int getTamanhoVaga() {
        return tamanhoVaga;
    }

    public void setTamanhoVaga(Integer tamanhoVaga) {
        this.tamanhoVaga = tamanhoVaga;
    }

    private double valorPagar(){
        //calcular baseado no tipo;
        return 5.00;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return idVeiculo == veiculo.idVeiculo && Objects.equals(placa, veiculo.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVeiculo, placa);
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "idVeiculo=" + idVeiculo +
                ", placa='" + placa + '\'' +
                ", tipo='" + tipo + '\'' +
                ", categoriaVeiculo=" + categoriaVeiculo +
                ", tamanhoVaga=" + tamanhoVaga +
                '}';
    }
}
