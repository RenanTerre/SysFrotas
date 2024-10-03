public class Carro extends Veiculo {
    private int numeroPortas;

    public Carro(String marca, String modelo, int ano, String placa, int numeroPortas) {
        super(marca, modelo, ano, placa);
        this.numeroPortas = numeroPortas;
    }

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    @Override
public String toString() {
    return "Carro: " +
           "Marca: " + getMarca() + ", " +
           "Modelo: " + getModelo() + ", " +
           "Ano: " + getAno() + ", " +
           "Placa: " + getPlaca() + ", " +
           "Número de Portas: " + numeroPortas;
}

}
