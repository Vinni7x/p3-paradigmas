public class VeiculoLuxo extends Veiculo {
    private static final double TAXA_LUXO = 0.15; // 15% de taxa adicional
    private static final double SEGURO_PREMIUM = 25.0; // Seguro premium por dia
    
    public VeiculoLuxo(String modelo, String placa, int ano, double valorDiaria) {
        super(modelo, placa, ano, valorDiaria);
    }
    
    @Override
    public double calcularAluguel(int dias) {
        // Veículos de luxo têm taxa adicional e seguro premium
        double valorBase = valorDiaria * dias;
        double taxaLuxo = valorBase * TAXA_LUXO;
        double seguro = SEGURO_PREMIUM * dias;
        
        return valorBase + taxaLuxo + seguro;
    }
    
}
