import java.util.List;

/**
 * Interface Observer para notificar sobre mudanças na lista de veículos
 * Parte da implementação do padrão Observer
 */
public interface VeiculoObserver {
    /**
     * Método chamado quando há alterações na lista de veículos
     * 
     * @param veiculos Lista atualizada de veículos
     */
    void atualizar(List<Veiculo> veiculos);
} 