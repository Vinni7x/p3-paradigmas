public class VeiculoPopularSimples extends VeiculoSimples {
    
    public VeiculoPopularSimples(String modelo, String placa, double valorDiaria) {
        super(modelo, placa, valorDiaria);
    }
    
    @Override
    public double calcularAluguel(int dias) {
        // Popular: valor normal
        return valorDiaria * dias;
    }
    
    // Método específico para mostrar especialização
    public boolean isBarato() {
        return valorDiaria < 100;
    }
}