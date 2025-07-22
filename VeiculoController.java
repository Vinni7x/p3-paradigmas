import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para gerenciar operações com veículos
 * Implementando o padrão MVC separando a lógica de negócio da interface
 */
public class VeiculoController {
    private List<Veiculo> veiculos;
    private List<VeiculoObserver> observers = new ArrayList<>();
    
    public VeiculoController() {
        // Carregar veículos do armazenamento persistente
        this.veiculos = GerenciadorArquivos.carregarVeiculos();
    }
    
    /**
     * Adiciona um observador para ser notificado de alterações no modelo
     */
    public void adicionarObserver(VeiculoObserver observer) {
        observers.add(observer);
    }
    
    /**
     * Remove um observador
     */
    public void removerObserver(VeiculoObserver observer) {
        observers.remove(observer);
    }
    
    /**
     * Notifica todos os observadores sobre mudanças nos dados
     */
    private void notificarObservers() {
        for (VeiculoObserver observer : observers) {
            observer.atualizar(veiculos);
        }
    }
    
    /**
     * Adiciona um novo veículo ao modelo e persiste os dados
     */
    public boolean adicionarVeiculo(Veiculo veiculo) {
        if (veiculo == null) {
            return false;
        }
        
        // Verificar se já existe veículo com a mesma placa
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(veiculo.getPlaca())) {
                return false;
            }
        }
        
        // Adicionar à lista
        veiculos.add(veiculo);
        
        // Persistir dados
        boolean sucesso = persistirDados();
        
        // Notificar observers se sucesso
        if (sucesso) {
            notificarObservers();
        }
        
        return sucesso;
    }
    
    /**
     * Remove um veículo da lista e persiste os dados
     */
    public boolean removerVeiculo(Veiculo veiculo) {
        boolean removido = veiculos.remove(veiculo);
        
        if (removido) {
            boolean sucesso = persistirDados();
            
            // Notificar observers se sucesso
            if (sucesso) {
                notificarObservers();
            }
            
            return sucesso;
        }
        
        return false;
    }
    
    /**
     * Remove um veículo pelo índice na lista
     */
    public boolean removerVeiculoPorIndice(int indice) {
        if (indice < 0 || indice >= veiculos.size()) {
            return false;
        }
        
        veiculos.remove(indice);
        boolean sucesso = persistirDados();
        
        // Notificar observers se sucesso
        if (sucesso) {
            notificarObservers();
        }
        
        return sucesso;
    }
    
    /**
     * Retorna a lista de veículos
     */
    public List<Veiculo> getVeiculos() {
        return new ArrayList<>(veiculos); // Retorna uma cópia para não expor a lista interna
    }
    
    /**
     * Obtém um veículo pelo índice
     */
    public Veiculo getVeiculoPorIndice(int indice) {
        if (indice < 0 || indice >= veiculos.size()) {
            return null;
        }
        return veiculos.get(indice);
    }
    
    /**
     * Persistir dados em armazenamento permanente
     */
    private boolean persistirDados() {
        try {
            GerenciadorArquivos.salvarVeiculos(veiculos);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao salvar veículos: " + e.getMessage());
            return false;
        }
    }
} 