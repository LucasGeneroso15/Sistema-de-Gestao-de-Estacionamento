package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Vaga implements Serializable {
    private Integer idVaga;
    private Integer numeroVaga;
    private Boolean isMensalista;
    private String placaVeiculo;
    private Integer idTicket;

    public Vaga(){
    }

    public Vaga(Integer idVaga, Integer numeroVaga, Boolean isMensalista, String placaVeiculo, Integer idTicket) {
        this.idVaga = idVaga;
        this.numeroVaga = numeroVaga;
        this.isMensalista = isMensalista;
        this.placaVeiculo = placaVeiculo;
        this.idTicket = idTicket;
    }

    public Integer getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(Integer idVaga) {
        this.idVaga = idVaga;
    }

    public Integer getNumeroVaga() {
        return numeroVaga;
    }

    public void setNumeroVaga(Integer numeroVaga) {
        this.numeroVaga = numeroVaga;
    }

    public Boolean getMensalista() {
        return isMensalista;
    }

    public void setMensalista(Boolean mensalista) {
        isMensalista = mensalista;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaga vaga = (Vaga) o;
        return idVaga == vaga.idVaga && numeroVaga == vaga.numeroVaga && Objects.equals(placaVeiculo, vaga.placaVeiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVaga, numeroVaga, placaVeiculo);
    }

    @Override
    public String toString() {
        return "Vaga{" +
                "idVaga=" + idVaga +
                ", numeroVaga=" + numeroVaga +
                ", isMensalista=" + isMensalista +
                ", placaVeiculo='" + placaVeiculo + '\'' +
                ", idTicket=" + idTicket +
                '}';
    }
}
