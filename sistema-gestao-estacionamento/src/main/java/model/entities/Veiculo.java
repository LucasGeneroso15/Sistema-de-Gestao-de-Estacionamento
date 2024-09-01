package model.entities;

import model.dao.DaoFactory;
import model.dao.VeiculoDao;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Veiculo implements Serializable {
    private Integer idVeiculo;
    private String placa;
    private String tipo;
    private String categoriaVeiculo;
    private Double valorPagar;
    private Integer tamanhoVaga;
    private Integer numeroVaga;

    private static VeiculoDao veiculoDao = DaoFactory.createVeiculoDao();


    public Veiculo(Integer idVeiculo, String placa, String tipo, String categoriaVeiculo, Integer tamanhoVaga) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.tipo = tipo;
        this.categoriaVeiculo = categoriaVeiculo;
        this.tamanhoVaga = tamanhoVaga;
    }

    public Veiculo(Integer idVeiculo, String placa, String tipo, String categoriaVeiculo, Double valorPagar, Integer tamanhoVaga) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.tipo = tipo;
        this.categoriaVeiculo = categoriaVeiculo;
        this.valorPagar = valorPagar;
        this.tamanhoVaga = tamanhoVaga;
    }

    public Veiculo(Integer idVeiculo, String placa, String tipo, String categoriaVeiculo, Integer tamanhoVaga, Integer numeroVaga) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.tipo = tipo;
        this.categoriaVeiculo = categoriaVeiculo;
        this.tamanhoVaga = tamanhoVaga;
        this.numeroVaga = numeroVaga;
    }

    public Veiculo(Integer idVeiculo, String placa, String categoriaVeiculo, String tipo){
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.categoriaVeiculo = categoriaVeiculo;
        this.tipo = tipo;
    }

    public Veiculo(String placa, String tipo, String categoriaVeiculo, Integer tamanhoVaga) {
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

    public Integer getNumeroVaga() {
        return numeroVaga;
    }

    public void setNumeroVaga(Integer numeroVaga) {
        this.numeroVaga = numeroVaga;
    }

    public static void cadastrarVeiculo(Integer categoria){
        Scanner sc = new Scanner(System.in);

        String categoriaVeiculo = categoria == 1 ? "MENSALISTA" : "CAMINHAO_ENTREGA";
        Veiculo v1;

        try {
            if (categoriaVeiculo.equalsIgnoreCase("MENSALISTA")) {
                Locale.setDefault(Locale.US);

                System.out.println();
                System.out.println("\n****************************");
                System.out.print("Entre com o tipo de veículo (Ex. Carro de passeio, Moto): ");
                String tipoVeiculo = sc.nextLine();

                System.out.println("\n****************************");
                System.out.print("Entre com a placa do veículo: ");
                String placa = sc.nextLine();

                int tamanhoVaga = tipoVeiculo.equalsIgnoreCase("moto") ? 1 : 2;

                v1 = new Veiculo(null, placa, tipoVeiculo, categoriaVeiculo, 250.00, tamanhoVaga);
                veiculoDao.cadastrarVeiculo(v1);
                System.out.println(v1);
            } else {
                System.out.println("\n****************************");
                System.out.print("Entre com a placa do veículo: ");
                String placa = sc.nextLine();

                int tamanhoVaga = 4;

                v1 = new Veiculo(null, placa, categoriaVeiculo, categoriaVeiculo, tamanhoVaga);
                veiculoDao.cadastrarVeiculo(v1);
                System.out.println(v1);
            }
        }catch (InputMismatchException e){
            throw new InputMismatchException("Verifique se as informações digitadas estão corretas!");
        }
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
        return "Veiculo { " +
                "idVeiculo = " + idVeiculo +
                ", placa = '" + placa + '\'' +
                ", tipo = '" + tipo + '\'' +
                ", categoriaVeiculo = '" + categoriaVeiculo + '\'' +
                ", valorPagar = " + valorPagar +
                ", tamanhoVaga = " + tamanhoVaga +
                ", numeroVaga = " + numeroVaga +
                '}';
    }
}
