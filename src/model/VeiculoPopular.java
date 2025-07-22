package src.model;
import src.startegy.EstrategiaCalculoPopular;

public class VeiculoPopular extends Veiculo {
    
    public VeiculoPopular(String modelo, String placa, int ano, double valorDiaria) {
        super(modelo, placa, ano, valorDiaria);
        this.estrategiaCalculo = new EstrategiaCalculoPopular();
    }
    
    /**
     * Obtém o calculador popular configurado para este veículo
     * 
     * @return A estratégia de cálculo para veículos populares
     */
    public EstrategiaCalculoPopular getCalculadorPopular() {
        return (EstrategiaCalculoPopular) estrategiaCalculo;
    }
    
    /**
     * Verifica se o veículo é considerado econômico
     * 
     * @return true se for econômico, false caso contrário
     */
    public boolean isEconomico() {
        return getCalculadorPopular().isEconomico(valorDiaria);
    }
}