package model.entities;

import model.dao.DaoFactory;
import model.dao.TicketDao;
import model.dao.VagaDao;
import model.dao.VeiculoDao;
import model.exception.CancelaException;
import model.util.CalcularPagamento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Cancela {
    private int numero;
    static VagaDao vagaDao = DaoFactory.createVagaDao();
    static VeiculoDao veiculoDao = DaoFactory.createVeiculoDao();
    static TicketDao ticketDao = DaoFactory.createTicketDao();

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

                    int tamanhoVaga = tipoVeiculo.equalsIgnoreCase("moto") ? 1 : 2;
                    List<Integer> vagasDisponiveis = vagaDao.vagasDisponiveis(tamanhoVaga);
                    Ticket novoTicket;

                    if (vagasDisponiveis.size() < tamanhoVaga) {
                        System.out.println("Nenhuma vaga disponível.");
                    } else {
                        List<Integer> atualizarVagas = vagasDisponiveis.subList(0, tamanhoVaga);
                        vagaDao.atualizarVagas(atualizarVagas, false, false);

                        int numeroVaga = atualizarVagas.get(0);

                        novoTicket = new Ticket(null, placa, LocalDateTime.now(), numero, numeroVaga);
                        ticketDao.novoTicket(novoTicket);
                        veiculoDao.gerenciarEntradaSaida(new Veiculo(null, placa, tipoVeiculo, categoriaVeiculo, tamanhoVaga));

                        System.out.println("\nTicket gerado com sucesso: " + novoTicket);

                        if(tamanhoVaga == 1)
                            System.out.println("Vaga: " + numeroVaga);
                        else
                            System.out.println("Vagas: " + numeroVaga + " " + String.valueOf (numeroVaga + 1));
                    }
                }
            } else if (categoriaVeiculo.equalsIgnoreCase("MENSALISTA")){
                System.out.println();
                System.out.println("\n****************************");
                System.out.print("Entre com o tipo de veículo (Ex. Carro de passeio, Moto): ");
                sc.nextLine();
                String tipoVeiculo = sc.nextLine();

                if(verificarEntrada(numero, categoriaVeiculo, tipoVeiculo)){
                    System.out.println("\n****************************");
                    System.out.print("Entre com a placa do veículo: ");
                    String placa = sc.nextLine();

                    Veiculo v1 = veiculoDao.procurarPlacaMensalista(placa);

                    if(v1 == null){
                        System.out.println("Veiculo não encontrado! Por favor, retorne e preencha o cadastro prévio.");
                    }else{
                        int tamanhoVaga = tipoVeiculo.equalsIgnoreCase("moto") ? 1 : 2;
                        List<Integer> vagasCadastrados = vagaDao.vagasDisponiveisCadastrados(tamanhoVaga);

                        if (vagasCadastrados.size() < tamanhoVaga) {
                            System.out.println("Nenhuma vaga disponível.");
                        } else {
                            List<Integer> atualizarVagas = vagasCadastrados.subList(0, tamanhoVaga);
                            vagaDao.atualizarVagas(atualizarVagas, true, false);
                            int numeroVaga = atualizarVagas.get(0);

                            veiculoDao.atualizarMensalista(placa ,numeroVaga);
                            veiculoDao.gerenciarEntradaSaida(v1);

                            System.out.println("\nVeiculo: "+ v1 + " encontrado com sucesso."  );
                            System.out.println("Acesso liberado!");
                            if(tamanhoVaga == 1)
                                System.out.println("Vaga: " + numeroVaga);
                            else
                                System.out.println("Vagas: " + numeroVaga + " " + String.valueOf (numeroVaga + 1));
                        }
                    }
                }
            } else if (categoriaVeiculo.equalsIgnoreCase("CAMINHAO_ENTREGA")) {
                String tipoVeiculo = "CAMINHAO_ENTREGA";
                if(verificarEntrada(numero, categoriaVeiculo, tipoVeiculo)){
                    System.out.println("\n****************************");
                    System.out.print("Entre com a placa do veículo: ");
                    sc.nextLine();
                    String placa = sc.nextLine();

                    Veiculo v1 = veiculoDao.procurarPlacaCaminhao(placa);

                    if(v1 == null){
                        System.out.println("Veiculo não encontrado! Por favor, retorne e preencha o cadastro prévio.");
                    }else{
                        int tamanhoVaga = 4;
                        List<Integer> vagasCadastrados = vagaDao.vagasDisponiveis(tamanhoVaga);

                        if (vagasCadastrados.size() < tamanhoVaga) {
                            System.out.println("Nenhuma vaga disponível.");
                        } else {
                            List<Integer> atualizarVagas = vagasCadastrados.subList(0, tamanhoVaga);
                            vagaDao.atualizarVagas(atualizarVagas, false, false);

                            int numeroVaga = atualizarVagas.get(0);

                            veiculoDao.atualizarCaminhao(placa ,numeroVaga);
                            veiculoDao.gerenciarEntradaSaida(v1);

                            System.out.println("\nVeiculo: "+ v1 + " encontrado com sucesso."  );
                            System.out.println("Acesso liberado!");

                            System.out.println("Vagas: " + numeroVaga + ", " + String.valueOf (numeroVaga + 1) + ", " + String.valueOf (numeroVaga + 2) + ", " + String.valueOf (numeroVaga + 3));
                        }
                    }
                }
            }else{
                String tipoVeiculo = "SERVICO_PUBLICO";
                if(verificarEntrada(numero, categoriaVeiculo, tipoVeiculo)) {
                    System.out.println("\n****************************");
                    System.out.print("Entre com a placa do veículo: ");
                    sc.nextLine();
                    String placa = sc.nextLine();

                    boolean placaRegistrada = veiculoDao.procurarPlacaServPub(placa);

                    if (!placaRegistrada) {
                        System.out.println("\nAcesso Liberado!");
                        veiculoDao.gerenciarEntradaSaida(new Veiculo(null, placa, categoriaVeiculo, tipoVeiculo));
                    } else {
                        System.out.println("\nPlaca já registrada no local. Por favor, confira se o veículo ainda não saiu.");
                    }
                }
            }
        } catch (InputMismatchException e) {
            sc.next();
            System.out.println("\nESCOLHA UMA CATEGORIA ENTRE 1 E 4.\n");
        } catch (CancelaException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saidaCancela(int numero){
        Scanner sc = new Scanner(System.in);

        System.out.println("\n****************************");
        System.out.print("Entre com a placa do veículo: ");
        String placa = sc.nextLine();

        Veiculo v1 = veiculoDao.procurarVeiculoPlaca(placa);
        try {
            if (v1 != null) {
                if (v1.getCategoriaVeiculo().equalsIgnoreCase("AVULSO")) {

                    Ticket ticket = ticketDao.buscarTicketPorPlaca(placa);

                    if (ticket != null) {
                        if (verificarSaida(numero, v1.getCategoriaVeiculo(), v1.getTipo())) {
                            System.out.print("Entre com a hora de saída (yyyy/MM/dd HH:mm): ");
                            String horaSaidaStr = sc.nextLine();
                            LocalDateTime horaSaida = LocalDateTime.parse(horaSaidaStr, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
                            ticket.setHoraSaida(horaSaida);
                            ticket.setCancelaSaida(numero);

                            double valorPago = CalcularPagamento.calcularValor(ticket, v1.getCategoriaVeiculo());
                            ticket.setValorPago(valorPago);

                            ticketDao.atualizarTicket(ticket);

                            System.out.println("Valor a pagar: " + ticket.getValorPago());

                            List<Integer> vagasOcupadas = new ArrayList<>();
                            vagasOcupadas.add(ticket.getVagaEscolhida());

                            for (int i = 0; i < v1.getTamanhoVaga(); i++) {
                                vagasOcupadas.add(ticket.getVagaEscolhida() + i);
                            }

                            vagaDao.atualizarVagas(vagasOcupadas, false, true);

                            System.out.println("...");
                            System.out.println("\nPagamento processado! Tenha uma ótima viagem!");
                        }
                    } else {
                        System.out.println("Ticket não encontrado para a placa mencionada.");
                    }
                } else if (v1.getCategoriaVeiculo().equalsIgnoreCase("MENSALISTA")) {
                    if (verificarSaida(numero, v1.getCategoriaVeiculo(), v1.getTipo())) {

                        Veiculo v2 = veiculoDao.procurarPlacaMensalista(placa);

                        Vaga.atualizarStatusVagas(v2.getNumeroVaga(), v2.getCategoriaVeiculo());

                        List<Integer> vagasOcupadas = new ArrayList<>();
                        vagasOcupadas.add(v2.getNumeroVaga());

                        for (int i = 0; i < v2.getTamanhoVaga(); i++) {
                            vagasOcupadas.add(v2.getNumeroVaga() + i);
                        }

                        vagaDao.atualizarVagas(vagasOcupadas, true, true);

                        System.out.println("...");
                        System.out.println("\nPlano Mensalista!");
                        System.out.println("\nSaída processada! Tenha uma ótima viagem!");
                    }
                } else if (v1.getCategoriaVeiculo().equalsIgnoreCase("CAMINHAO_ENTREGA")) {
                    if (verificarSaida(numero, v1.getCategoriaVeiculo(), v1.getTipo())) {

                        Veiculo v2 = veiculoDao.procurarPlacaCaminhao(placa);

                        Vaga.atualizarStatusVagas(v2.getNumeroVaga(), v2.getCategoriaVeiculo());

                        List<Integer> vagasOcupadas = new ArrayList<>();
                        vagasOcupadas.add(v2.getNumeroVaga());

                        for (int i = 0; i < v2.getTamanhoVaga(); i++) {
                            vagasOcupadas.add(v2.getNumeroVaga() + i);
                        }

                        vagaDao.atualizarVagas(vagasOcupadas, false, true);

                        System.out.println("...");
                        System.out.println("\nCaminhão previamente cadastrado!.");
                        System.out.println("\nSaída processada! Tenha uma ótima viagem!");
                    }
                } else {
                    boolean placaRegistrada = veiculoDao.procurarPlacaServPub(placa);
                    if (!placaRegistrada) {
                        System.out.println("\nPlaca não registrada no local. Por favor, confira se o veículo já saiu.");
                    } else {
                        veiculoDao.removerVeiculoServicoPublico(placa);
                        System.out.println("...");
                        System.out.println("\nVeículo de Serviço Publico saiu do local!");
                    }
                }
            } else {
                System.out.println("Veículo não encontrado! Por favor, verifique a placa.");
            }
        } catch (CancelaException e) {
            throw new CancelaException(e.getMessage());
        }catch (InputMismatchException e){
            throw new InputMismatchException("Verifique se as informações digitadas estão corretas.");
        }
    }

    public static boolean verificarEntrada(int numero, String categoriaVeiculo, String tipoVeiculo){

        if(categoriaVeiculo.equalsIgnoreCase("AVULSO")){
            if (tipoVeiculo.equalsIgnoreCase("MOTO")) {
                if (numero == 5) {
                    return true;
                } else {
                    throw new CancelaException("O VEÍCULO ('MOTO') SÓ PODERÁ ACESSAR PELA CANCELA '5', POR FAVOR RETORNE!");
                }
            }else{
                    return true;
            }
        }else if (categoriaVeiculo.equalsIgnoreCase("MENSALISTA")){
            if (tipoVeiculo.equalsIgnoreCase("MOTO")) {
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

    public static boolean verificarSaida(int numero, String categoriaVeiculo, String tipoVeiculo) {

        if(categoriaVeiculo.equalsIgnoreCase("AVULSO")){
            if (tipoVeiculo.equalsIgnoreCase("MOTO")) {
                if (numero == 10) {
                    return true;
                } else {
                    throw new CancelaException("O VEÍCULO ('MOTO') SÓ PODERÁ SAIR PELA CANCELA '10', POR FAVOR RETORNE!");
                }
            }else{
                return true;
            }
        }else if (categoriaVeiculo.equalsIgnoreCase("MENSALISTA")){
            if (tipoVeiculo.equalsIgnoreCase("MOTO")) {
                if (numero == 10) {
                    return true;
                } else {
                    throw new CancelaException("O VEÍCULO ('MOTO') SÓ PODERÁ SAIR PELA CANCELA '10', POR FAVOR RETORNE!");
                }
            }else{
                return true;
            }
        } else if (categoriaVeiculo.equalsIgnoreCase("CAMINHAO_ENTREGA")) {
                return true;
        } else if (categoriaVeiculo.equalsIgnoreCase("SERVICO_PUBLICO")){
            return true;
        }else {
            return false;
        }
    }

}

