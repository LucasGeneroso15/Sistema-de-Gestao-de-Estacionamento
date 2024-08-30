package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class Ticket implements Serializable {
    private Integer id;
    private String placaVeiculo;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private Integer cancelaEntrada;
    private Integer cancelaSaida;
    private Integer vagaEscolhida;
    private Double valorPago;

    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Ticket(){
    }

    public Ticket(Integer id, String placaVeiculo, LocalDateTime horaEntrada, Integer cancelaEntrada, Integer vagaEscolhida) {
        this.id = id;
        this.placaVeiculo = placaVeiculo;
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

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
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
        return Objects.equals(id, ticket.id) && Objects.equals(placaVeiculo, ticket.placaVeiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placaVeiculo);
    }

    @Override
    public String toString() {
        return "TICKET {" +
                "id = " + id +
                ", hora Entrada = " + (horaEntrada != null ? horaEntrada.format(fmt) : "N/A") +
                ", hora Saida = " + (horaSaida != null ? horaSaida.format(fmt) : "N/A") +
                ", cancela Entrada =" + cancelaEntrada +
                ", cancela Saida =" + cancelaSaida +
                ", vaga Ocupada = " + vagaEscolhida +
                ", valor Pago=" + valorPago +
                '}';
    }
}
