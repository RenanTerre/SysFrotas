import java.util.List;
import java.util.Scanner;

public class ViewCadVeiculo {
    private static ServiceVeiculo service = new ServiceVeiculo();
    static Scanner input = new Scanner(System.in);

    public static void limparTela() {
        System.out.println("\033[H\033[2J");
    }

    public static void aguardarEnter() {
        System.out.print("Pressione Enter para voltar ao Menu Inicial ");
        input.nextLine();
    }

    private static int inputNumerico(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.print(mensagem);
        do {
            String valorStr = input.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("ERRO. Valor informado deve ser um número Inteiro");
                System.out.println("Digite um número dentro das Opções acima: ");
            }
        } while (!entradaValida);
        return valor;
    }

    private static String inputString(String mensagem) {
        System.out.print(mensagem);
        return input.nextLine();
    }

    public static void main(String[] args) {
        String menu = """
                SISTEMA DE GERENCIAMENTO DE FROTAS
                Menu de Opções:
                1 - Cadastrar novo Veículo;
                2 - Listar todos Veículos cadastrados;
                3 - Pesquisar Veículo pela placa;
                4 - Remover Veículo;
                0 - Sair;
                Digite a opção desejada:  """;
        int opcao;
        do {
            limparTela();
            opcao = inputNumerico(menu);
            switch (opcao) {
                case 0:
                    System.out.println("VOLTE SEMPRE!!!");
                    break;
                case 1:
                    cadastrarVeiculo();
                    break;
                case 2:
                    listarVeiculos();
                    break;
                case 3:
                    pesquisarVeiculo();
                    break;
                case 4:
                    removerVeiculo();
                    break;
                default:
                    System.out.println("Opção Inválida!!!");
                    aguardarEnter();
                    break;
            }
        } while (opcao != 0);
    }

    private static void cadastrarVeiculo() {
        limparTela();

        System.out.println("CADASTRO DE VEÍCULO");
        System.out.println("Qual o tipo de veículo");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");

        int tipo = inputNumerico("Digite a opção desejada: ");

        if (tipo == 1) {
            String marca = inputString("Digite a marca do carro: ");
            String modelo = inputString("Digite o modelo do carro: ");
            int ano = inputNumerico("Digite o ano do carro: ");
            String placa = inputString("Digite a placa do carro: ");
            int numPortas = inputNumerico("Digite o número de portas: ");

            boolean resultadoCadastro = service.cadastrarCarro(marca, modelo, ano, placa, numPortas);
            exibirMensagemCadastro(resultadoCadastro);

        } else if (tipo == 2) {
            String marca = inputString("Digite a marca da moto: ");
            String modelo = inputString("Digite o modelo da moto: ");
            int ano = inputNumerico("Digite o ano da moto: ");
            String placa = inputString("Digite a placa da moto: ");
            int cilindradas = inputNumerico("Digite a cilindrada da moto: ");

            int partidaEletricaOpcao = inputNumerico("A moto possui partida elétrica: 1-Sim, 2-Não: ");
            boolean partidaEletrica = (partidaEletricaOpcao == 1);

            boolean resultadoCadastro = service.cadastrarMoto(marca, modelo, ano, placa, cilindradas, partidaEletrica);
            exibirMensagemCadastro(resultadoCadastro);

        } else {
            System.out.println("Opção inválida! Retornando ao menu principal.");
        }

        aguardarEnter();
    }

    private static void exibirMensagemCadastro(boolean sucesso) {
        if (!sucesso) {
            System.out.println("NÃO FOI POSSÍVEL CADASTRAR O VEÍCULO.");
        } else {
            System.out.println("Veículo cadastrado com sucesso!");
        }
    }

    private static void listarVeiculos() {
        limparTela();
        System.out.println("LISTA DE VEÍCULOS CADASTRADOS");

        List<Veiculo> veiculos = service.listarVeiculos();
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo veiculo = veiculos.get(i);
            String tipoVeiculo = veiculo instanceof Carro ? "Carro" : "Moto";
            String numPortas = veiculo instanceof Carro ? "Número de Portas: " + ((Carro) veiculo).getNumeroPortas()
                    : "";
            String cilindradas = veiculo instanceof Moto
                    ? "Cilindradas: " + ((Moto) veiculo).getCilindradas() + "cc"
                    : "";
            String partidaEletrica = veiculo instanceof Moto
                    ? "Part. Elétrica: " + (((Moto) veiculo).isPartidaEletrica() ? "Sim" : "Não")
                    : "";

            String veiculoInfo = String.format(
                    "Veículo %d - %s - Marca: %s - Modelo: %s - Ano: %d - Placa: %s - %s%s%s",
                    i + 1, tipoVeiculo, veiculo.getMarca(), veiculo.getModelo(), veiculo.getAno(), veiculo.getPlaca(),
                    numPortas.isEmpty() ? "" : numPortas + " - ", cilindradas.isEmpty() ? "" : cilindradas + " - ",
                    partidaEletrica.isEmpty() ? "" : partidaEletrica);

            System.out.println(veiculoInfo);
        }

        aguardarEnter();
    }

    private static void pesquisarVeiculo() {
        limparTela();
        String placa = inputString("Digite a placa do veículo que deseja buscar: ");
        Veiculo veiculo = service.buscarVeiculoPorPlaca(placa);
        if (veiculo != null) {
            int numeroCadastro = 1;
            System.out.println("Veículo " + numeroCadastro + " - " + veiculo);
        } else {
            System.out.println("Veículo não encontrado.");
        }
        aguardarEnter();
    }

    private static void removerVeiculo() {
        limparTela();
        String placa = inputString("Digite a placa do veículo que deseja remover: ");
        boolean sucesso = service.removerVeiculo(placa);
        if (!sucesso) {
            System.out.println("Veículo não encontrado.");
        }
        aguardarEnter();
    }
}
