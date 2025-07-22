/**
 * Interface que define a estratégia de cálculo do valor de aluguel
 * Implementando o padrão Strategy
 */
public interface EstrategiaCalculo {
    /**
     * Calcula o valor do aluguel com base na quantidade de dias
     * 
     * @param dias Quantidade de dias de aluguel
     * @param valorDiaria Valor base da diária
     * @return O valor total calculado
     */
    double calcularAluguel(int dias, double valorDiaria);
} 