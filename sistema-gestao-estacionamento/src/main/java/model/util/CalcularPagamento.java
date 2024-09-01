package model.util;

import model.entities.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;

public class CalcularPagamento {

    private static final double VALOR_MINUTO = 0.10;

    private static final double COBRANCA_MINIMA = 5.00;

    private static final double TAXA_MENSALISTA = 250.00;

    public static double calcularValor(Ticket ticket, String categoria) {
        String categoriaVeiculo = categoria;
        LocalDateTime horaEntrada = ticket.getHoraEntrada();
        LocalDateTime horaSaida = ticket.getHoraSaida();

        if (horaSaida == null || horaEntrada == null) {
            throw new IllegalArgumentException("Horário de entrada ou saída não pode ser nulo.");
        }

        long minutos = Duration.between(horaEntrada, horaSaida).toMinutes();

        double valorPagar;

        switch (categoriaVeiculo.toUpperCase()) {
            case "AVULSO":
                valorPagar = minutos * VALOR_MINUTO;

                if (valorPagar < COBRANCA_MINIMA) {
                    valorPagar = COBRANCA_MINIMA;
                }
                break;
            case "MENSALISTA":
                valorPagar = TAXA_MENSALISTA;
                break;
            case "SERVICO_PUBLICO":
                valorPagar = 0.00;
                break;
            default:
                throw new IllegalArgumentException("Categoria de veículo desconhecida");
        }
        return valorPagar;
    }
}

