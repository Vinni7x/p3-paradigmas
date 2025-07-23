public class VeiculoPopular extends Veiculo {
    
    public VeiculoPopular(String modelo, String placa, int ano, double valorDiaria) {
        super(modelo, placa, ano, valorDiaria);
    }
    
    @Override
    public double calcularAluguel(int dias) {
        // Veículos populares têm desconto para locações longas
        double valor = valorDiaria * dias;
        
        // Desconto de 5% para locações de 7 dias ou mais
        if (dias >= 7) {
            valor *= 0.95;
        }
        
        return valor;
    }
    
}
