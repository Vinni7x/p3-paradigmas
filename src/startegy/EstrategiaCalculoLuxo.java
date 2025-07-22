package src.startegy;
/**
 * Implementação da estratégia de cálculo para veículos de luxo
 * Parte do padrão Strategy
 */
public class EstrategiaCalculoLuxo implements EstrategiaCalculo {
    
    private static final double TAXA_LUXO = 0.15; // 15% de taxa adicional
    private static final double SEGURO_PREMIUM = 25.0; // Seguro premium por dia
    
    @Override
    public double calcularAluguel(int dias, double valorDiaria) {
        // Veículos de luxo têm taxa adicional e seguro premium
        double valorBase = valorDiaria * dias;
        double taxaLuxo = valorBase * TAXA_LUXO;
        double seguro = SEGURO_PREMIUM * dias;
        
        return valorBase + taxaLuxo + seguro;
    }
    
    /**
     * Calcula o valor do seguro premium
     * 
     * @param dias Quantidade de dias de aluguel
     * @return Valor do seguro premium
     */
    public double calcularSeguroPremium(int dias) {
        return SEGURO_PREMIUM * dias;
    }
    
    /**
     * Retorna a taxa de luxo aplicada
     * 
     * @return Taxa de luxo (percentual)
     */
    public double getTaxaLuxo() {
        return TAXA_LUXO;
    }
} 