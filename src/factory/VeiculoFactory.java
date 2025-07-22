package src.factory;
import src.model.Veiculo;
import src.model.VeiculoLuxo;
import src.model.VeiculoPopular;

/**
 * Factory para criação de veículos
 * Implementando o padrão Factory Method
 */
public class VeiculoFactory {
    
    /**
     * Cria um veículo baseado no tipo especificado
     * 
     * @param tipo Tipo do veículo ("Popular" ou "Luxo")
     * @param modelo Modelo do veículo
     * @param placa Placa do veículo
     * @param ano Ano de fabricação
     * @param valorDiaria Valor da diária
     * @return Um objeto Veiculo do tipo especificado
     */
    public static Veiculo criarVeiculo(String tipo, String modelo, String placa, int ano, double valorDiaria) {
        if (tipo == null || tipo.isEmpty()) {
            return null;
        }
        
        if (tipo.equalsIgnoreCase("Popular")) {
            return new VeiculoPopular(modelo, placa, ano, valorDiaria);
        } else if (tipo.equalsIgnoreCase("Luxo")) {
            return new VeiculoLuxo(modelo, placa, ano, valorDiaria);
        }
        
        return null; // Tipo não reconhecido
    }
} 