import java.util.ArrayList;
import java.util.List;

public class ServiceVeiculo {
    private List<Veiculo> veiculos;

    public ServiceVeiculo() {
        this.veiculos = new ArrayList<>();
    }

    public boolean cadastrarCarro(String marca, String modelo, int ano, String placa, int numeroPortas) {
        if (buscarVeiculoPorPlaca(placa) != null) {
            System.out.println("ERRO: Já existe um veículo cadastrado com essa placa.");
            return false;
        }

        if (marca.isBlank() || modelo.isBlank() || placa.isBlank() || ano <= 0 || numeroPortas <= 0) {
            System.out.println("ERRO: Dados inválidos. Verifique se todos os campos foram preenchidos corretamente.");
            return false;
        }

        Carro novoCarro = new Carro(marca, modelo, ano, placa, numeroPortas);
        veiculos.add(novoCarro);
        return true;
    }

    public boolean cadastrarMoto(String marca, String modelo, int ano, String placa, int cilindradas, boolean partidaEletrica) {
        if (buscarVeiculoPorPlaca(placa) != null) {
            System.out.println("ERRO: Já existe um veículo cadastrado com essa placa.");
            return false;
        }

        if (marca.isBlank() || modelo.isBlank() || placa.isBlank() || ano <= 0 || cilindradas <= 0) {
            System.out.println("ERRO: Dados inválidos. Verifique se todos os campos foram preenchidos corretamente.");
            return false;
        }

        Moto novaMoto = new Moto(marca, modelo, ano, placa, cilindradas, partidaEletrica);  
        veiculos.add(novaMoto);
        return true;
    }

    public Veiculo buscarVeiculoPorPlaca(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    public List<Veiculo> listarVeiculos() {
        return veiculos;
    }

    public boolean removerVeiculo(String placa) {
        Veiculo veiculo = buscarVeiculoPorPlaca(placa);
        if (veiculo != null) {
            veiculos.remove(veiculo);
            System.out.println("Veículo removido com sucesso!");  
            return true;
        } else {
            System.out.println("ERRO: Veículo não encontrado.");
            return false;
        }
    }
}
