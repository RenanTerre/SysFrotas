public class Moto extends Veiculo {
    private boolean partidaEletrica;
    private int cilindradas;

    public Moto(String marca, String modelo, int ano, String placa, int cilindradas, boolean partidaEletrica) {
        super(marca, modelo, ano, placa);
        this.cilindradas = cilindradas;
        this.partidaEletrica = partidaEletrica;
    }

    public boolean isPartidaEletrica() {
        return partidaEletrica;
    }

    public void setPartidaEletrica(boolean partidaEletrica) {
        this.partidaEletrica = partidaEletrica;
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(int cilindradas) {
        this.cilindradas = cilindradas;
    }

    @Override
public String toString() {
    return "Moto: " +
           "Marca: " + getMarca() + ", " +
           "Modelo: " + getModelo() + ", " +
           "Ano: " + getAno() + ", " +
           "Placa: " + getPlaca() + ", " +
           "Cilindradas: " + cilindradas + ", " +
           "Partida Elétrica: " + (partidaEletrica ? "Sim" : "Não");
}
}
