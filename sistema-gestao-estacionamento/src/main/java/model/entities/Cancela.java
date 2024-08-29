package model.entities;

import model.entities.enums.CategoriaVeiculo;
import model.exception.CancelaException;

import java.time.LocalDate;
import java.util.*;

public class Cancela {
    private int numero;

    public Cancela(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public static void entradaCancela(int numero){
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("\nCATEGORIAS DE VEÍCULO: 1 - AVULSO, 2 - MENSALISTA, 3 - CAMINHAO DE ENTREGA, 4 - SERVICO PUBLICO");
        System.out.print("Entre com a categoria do veículo ( 1 - 4 ): ");
        int categoria;
        try {
            categoria = sc.nextInt();
            if (categoria < 1 || categoria > 4) {
                throw new CancelaException("\nESCOLHA UMA CATEGORIA ENTRE 1 E 4.\n");
            }

            String categoriaVeiculo = categoria == 1 ? "AVULSO" : categoria == 2 ? "MENSALISTA" : categoria == 3 ? "CAMINHAO_ENTREGA" : "SERVICO_PUBLICO" ;

            if(categoriaVeiculo.equalsIgnoreCase("AVULSO")){
                System.out.println();
                System.out.println("\n****************************");
                System.out.print("Entre com o tipo de veículo (Ex. Carro de passeio, Moto): ");
                sc.nextLine();
                String tipoVeiculo = sc.nextLine();

                if(verificarEntrada(numero, categoriaVeiculo, tipoVeiculo)){
                    System.out.println("\n****************************");
                    System.out.print("Entre com a placa do veículo: ");
                    String placa = sc.nextLine();

                    Estacionamento estacionamento = new Estacionamento();

                    if(!tipoVeiculo.equalsIgnoreCase("moto")){

                    }else{

                    }

                    System.out.println("Ticket gerado com sucesso!");
                }
            } else if (categoriaVeiculo.equalsIgnoreCase("MENSALISTA")){

            } else if (categoriaVeiculo.equalsIgnoreCase("CAMINHAO_ENTREGA")) {

            }else{

            }

        } catch (InputMismatchException e) {
            sc.next();
            System.out.println("\nESCOLHA UMA CATEGORIA ENTRE 1 E 4.\n");
        } catch (CancelaException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean verificarEntrada(int numero, String categoriaVeiculo, String tipoVeiculo){

        if(categoriaVeiculo.equalsIgnoreCase("AVULSO")){
            if (tipoVeiculo.toUpperCase().equals("MOTO")) {
                if (numero == 5) {
                    return true;
                } else {
                    throw new CancelaException("O VEÍCULO ('MOTO') SÓ PODERÁ ACESSAR PELA CANCELA '5', POR FAVOR RETORNE!");
                }
            }else{
                    return true;
            }
        }else if (categoriaVeiculo.equalsIgnoreCase("MENSALISTA")){
            if (tipoVeiculo.toUpperCase().equals("MOTO")) {
                if (numero == 5) {
                    return true;
                } else {
                    throw new CancelaException("O VEÍCULO ('MOTO') SÓ PODERÁ ACESSAR PELA CANCELA '5', POR FAVOR RETORNE!");
                }
            }else{
                return true;
            }
        } else if (categoriaVeiculo.equalsIgnoreCase("CAMINHAO_ENTREGA")) {
            if (numero != 1) {
                throw new CancelaException("OS VEÍCULOS DE ENTREGA SÓ PODEM ACESSAR PELA CANCELA '1', POR FAVOR RETORNE!");
            }else{
                return true;
            }
        } else if (categoriaVeiculo.equalsIgnoreCase("SERVICO_PUBLICO")){
            return true;
        }else {
            return false;
        }

}

    public static boolean verificarSaida(int cancelaNumero, Veiculo veiculo) {
        return true;
    }

}

