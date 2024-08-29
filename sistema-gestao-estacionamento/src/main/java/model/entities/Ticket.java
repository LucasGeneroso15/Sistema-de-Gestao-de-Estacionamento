package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Ticket implements Serializable {
    private Integer id;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private Integer cancelaEntrada;
    private Integer cancelaSaida;
    private Integer vagaEscolhida;
    private Double valorPago;

    public Ticket(Integer id, LocalDateTime horaEntrada, Integer cancelaEntrada, Integer vagaEscolhida) {
        this.id = id;
        this.horaEntrada = horaEntrada;
        this.cancelaEntrada = cancelaEntrada;
        this.vagaEscolhida = vagaEscolhida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Integer getCancelaEntrada() {
        return cancelaEntrada;
    }

    public void setCancelaEntrada(Integer cancelaEntrada) {
        this.cancelaEntrada = cancelaEntrada;
    }

    public Integer getCancelaSaida() {
        return cancelaSaida;
    }

    public void setCancelaSaida(Integer cancelaSaida) {
        this.cancelaSaida = cancelaSaida;
    }

    public Integer getVagaEscolhida() {
        return vagaEscolhida;
    }

    public void setVagaEscolhida(Integer vagaEscolhida) {
        this.vagaEscolhida = vagaEscolhida;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", horaEntrada=" + horaEntrada +
                ", horaSaida=" + horaSaida +
                ", cancelaEntrada=" + cancelaEntrada +
                ", cancelaSaida=" + cancelaSaida +
                ", vagaEscolhida=" + vagaEscolhida +
                ", valorPago=" + valorPago +
                '}';
    }
}
