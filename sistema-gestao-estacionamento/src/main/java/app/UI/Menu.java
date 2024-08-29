package app.UI;

import model.entities.Cancela;
import model.entities.Ticket;
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
                System.out.println("\nOPÇÃO INVÁLIDA! POR FAVOR, ESCOLHA OUTRA OPÇÃO.\n");
                continue;
            }

            switch (op) {
                case 1:
                    System.out.println();
                    System.out.println("****************************");
                    System.out.println("NOTA 1.: OS VEÍCULOS DE ENTREGA SÓ PODEM ACESSAR PELA CANCELA NÚMERO 1.");
                    System.out.println("NOTA 2.: OS VEÍCULOS DO TIPO 'MOTO' SÓ PODERÃO ACESSAR PELA CANCELA NÚMERO 5.");
                    System.out.println();

                    int cancela;
                    System.out.print("Escolha a cancela ( 1 - 5 ): ");

                    try {
                        cancela = sc.nextInt();
                        if (cancela < 1 || cancela > 5) {
                            throw new CancelaException("\nAS CANCELAS DE ACESSO SÃO DE 1 A 5.\n");
                        }
                    } catch (InputMismatchException e) {
                        sc.next();
                        System.out.println("\nAS CANCELAS DE ACESSO SÃO DE 1 A 5.\n");
                        continue;
                    } catch (CancelaException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    Cancela.entradaCancela(cancela);



                    System.out.println();
                    break;
                case 2:
                    System.out.println("Saída do Estacionamento ainda não implementada.");
                    break;
                case 3:
                    System.out.println("Cadastro de veículo mensalista ainda não implementado.");
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
