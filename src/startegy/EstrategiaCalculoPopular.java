package src.startegy;
/**
 * Implementação da estratégia de cálculo para veículos populares
 * Parte do padrão Strategy
 */
public class EstrategiaCalculoPopular implements EstrategiaCalculo {
    
    private static final double DESCONTO_LOCACAO_LONGA = 0.05; // 5% de desconto
    
    @Override
    public double calcularAluguel(int dias, double valorDiaria) {
        double valor = valorDiaria * dias;
        
        // Desconto de 5% para locações de 7 dias ou mais
        if (dias >= 7) {
            valor *= (1 - DESCONTO_LOCACAO_LONGA);
        }
        
        return valor;
    }
    
    /**
     * Verifica se o veículo é considerado econômico
     * 
     * @param valorDiaria Valor da diária do veículo
     * @return true se for econômico, false caso contrário
     */
    public boolean isEconomico(double valorDiaria) {
        return valorDiaria < 80.0;
    }
} 