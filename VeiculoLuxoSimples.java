public class VeiculoLuxoSimples extends VeiculoSimples {
    
    public VeiculoLuxoSimples(String modelo, String placa, double valorDiaria) {
        super(modelo, placa, valorDiaria);
    }
    
    @Override
    public double calcularAluguel(int dias) {
        // Luxo: valor + 20% de taxa
        return (valorDiaria * dias) * 1.2;
    }
    
    // Método específico para mostrar especialização
    public double getTaxa() {
        return 0.2; // 20%
    }
}