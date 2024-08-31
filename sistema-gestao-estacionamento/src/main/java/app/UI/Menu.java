package app.UI;

import model.entities.Cancela;
import model.entities.Ticket;
import model.entities.Veiculo;
import model.entities.enums.CategoriaVeiculo;
import model.exception.CancelaException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void mostrarMenu(Scanner sc) {
        int op = 0;

        do {
            System.out.println("****** Sistema de Gestão de Estacionamento ******");
            System.out.println("Escolha a opção desejada: ");
            System.out.println(" 1 - Acessar o Estacionamento");
            System.out.println(" 2 - Sair do Estacionamento");
            System.out.println(" 3 - Cadastrar Veículo Mensalista");
            System.out.println(" 4 - Finalizar");
            System.out.print(">>> ");

            try {
                op = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("\nOpção Inválida! Por favor, escolha uma opção válida.\n");
                continue;
            }

            switch (op) {
                case 1:
                    System.out.println("****************************");
                    System.out.println("NOTA 1.: OS VEÍCULOS DE ENTREGA SÓ PODEM ACESSAR PELA CANCELA NÚMERO 1.");
                    System.out.println("NOTA 2.: OS VEÍCULOS DO TIPO 'MOTO' SÓ PODERÃO ACESSAR PELA CANCELA NÚMERO 5.");
                    System.out.println();

                    int cancela;
                    System.out.print("Escolha a cancela ( 1 - 5 ): ");

                    try {
                        cancela = sc.nextInt();
                        if (cancela < 1 || cancela > 5) {
                            throw new CancelaException("\nAs cancelas de entrada são de 1 a 5!\n");
                        }
                    } catch (InputMismatchException e) {
                        sc.next();
                        System.out.println("\nAs cancelas de entrada são de 1 a 5!\n");
                        continue;
                    } catch (CancelaException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    Cancela.entradaCancela(cancela);
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    System.out.println("****************************");
                    System.out.println("NOTA 1.: OS VEÍCULOS DO TIPO 'MOTO' SÓ PODERÃO SAIR PELA CANCELA NÚMERO 10.");
                    System.out.println();

                    System.out.print("Escolha a cancela ( 6 - 10 ): ");

                    try {
                        cancela = sc.nextInt();
                        if (cancela < 6 || cancela > 10) {
                            throw new CancelaException("\nAs cancelas de saída são de 6 a 10!\n");
                        }
                    } catch (InputMismatchException e) {
                        sc.next();
                        System.out.println("\nAs cancelas de saída são de 6 a 10!\n");
                        continue;
                    } catch (CancelaException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    Cancela.saidaCancela(cancela);
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("****************************");
                    System.out.println("CADASTRO DE VEICULOS");
                    System.out.println();
                    System.out.println("CATEGORIAS DE VEÍCULO: 1 - MENSALISTA, 2 - CAMINHAO DE ENTREGA");
                    System.out.print("\nEntre com a categoria de veiculo que deseja cadastrar ( 1 ou 2 ): ");
                    int categoria;
                    try {
                        categoria = sc.nextInt();
                        if (categoria < 1 || categoria > 2) {
                            throw new CancelaException("\nCategoria inválida! Escolha uma categoria dentro das opções.\n");
                        }
                    } catch (InputMismatchException e) {
                        sc.next();
                        System.out.println("\nCategoria inválida! Escolha uma categoria dentro das opções.\n");
                        continue;
                    } catch (CancelaException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    Veiculo.cadastrarVeiculo(categoria);
                    System.out.println("Veículo cadastrado com sucesso!");
                    break;
                case 4:
                    System.out.println("Finalizando o sistema...");
                    break;
                default:
                    System.out.println("Opção não encontrada! Por favor, escolha uma opção entre 1 e 4.");
                    break;
            }
        } while (op != 4);

        sc.close();
    }
}
