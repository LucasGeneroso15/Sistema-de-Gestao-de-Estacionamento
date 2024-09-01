package model.entities;

import java.io.Serializable;
import java.util.Objects;

import static model.entities.Cancela.vagaDao;

public class Vaga implements Serializable {
    private Integer idVaga;
    private Integer numeroVaga;
    private Boolean reservada;
    private Boolean status;

    public Vaga() {
    }

    public Vaga(Integer idVaga, Integer numeroVaga, Boolean reservada, Boolean status) {
        this.idVaga = idVaga;
        this.numeroVaga = numeroVaga;
        this.reservada = reservada;
        this.status = status;
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

    public Boolean getReservada() {
        return reservada;
    }

    public void setReservada(Boolean reservada) {
        this.reservada = reservada;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public static void atualizarStatusVagas(int numeroVaga, String categoriaVeiculo) {
        if (categoriaVeiculo.equalsIgnoreCase("AVULSO")) {
            vagaDao.atualizarStatusVagasComuns(numeroVaga);
        }
        else if (categoriaVeiculo.equalsIgnoreCase("MENSALISTA") || categoriaVeiculo.equalsIgnoreCase("CAMINHAO_ENTREGA")) {
            vagaDao.atualizarStatusVagasCadastradas(numeroVaga, categoriaVeiculo);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaga vaga = (Vaga) o;
        return Objects.equals(idVaga, vaga.idVaga) && Objects.equals(numeroVaga, vaga.numeroVaga) && Objects.equals(reservada, vaga.reservada) && Objects.equals(status, vaga.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVaga, numeroVaga, reservada, status);
    }

    @Override
    public String toString() {
        return "Vaga{" +
                "idVaga=" + idVaga +
                ", numeroVaga=" + numeroVaga +
                ", reservada=" + reservada +
                ", status=" + status +
                '}';
    }
}
