package src.model;
import src.startegy.EstrategiaCalculoLuxo;

public class VeiculoLuxo extends Veiculo {
    
    public VeiculoLuxo(String modelo, String placa, int ano, double valorDiaria) {
        super(modelo, placa, ano, valorDiaria);
        this.estrategiaCalculo = new EstrategiaCalculoLuxo();
    }
    
    /**
     * Obtém o calculador de luxo configurado para este veículo
     * 
     * @return A estratégia de cálculo para veículos de luxo
     */
    public EstrategiaCalculoLuxo getCalculadorLuxo() {
        return (EstrategiaCalculoLuxo) estrategiaCalculo;
    }
    
    /**
     * Calcula o valor do seguro premium para o período especificado
     * 
     * @param dias Quantidade de dias
     * @return Valor do seguro premium
     */
    public double calcularSeguroPremium(int dias) {
        return getCalculadorLuxo().calcularSeguroPremium(dias);
    }
    
    /**
     * Obtém a taxa de luxo aplicada
     * 
     * @return Percentual da taxa de luxo
     */
    public double getTaxaLuxo() {
        return getCalculadorLuxo().getTaxaLuxo();
    }
}