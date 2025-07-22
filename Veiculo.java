public abstract class Veiculo {
    protected String modelo;
    protected String placa;
    protected int ano;
    protected double valorDiaria;
    protected EstrategiaCalculo estrategiaCalculo;
    
    public Veiculo(String modelo, String placa, int ano, double valorDiaria) {
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
        this.valorDiaria = valorDiaria;
    }
    
    /**
     * Calcula o aluguel utilizando a estratégia configurada
     */
    public double calcularAluguel(int dias) {
        if (estrategiaCalculo == null) {
            throw new IllegalStateException("Estratégia de cálculo não configurada");
        }
        return estrategiaCalculo.calcularAluguel(dias, valorDiaria);
    }
    
    /**
     * Define a estratégia de cálculo a ser utilizada
     */
    public void setEstrategiaCalculo(EstrategiaCalculo estrategiaCalculo) {
        this.estrategiaCalculo = estrategiaCalculo;
    }
    
    /**
     * Retorna a estratégia de cálculo configurada
     */
    public EstrategiaCalculo getEstrategiaCalculo() {
        return estrategiaCalculo;
    }
    
    // Getters e Setters
    public String getModelo() {
        return modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public String getPlaca() {
        return placa;
    }
    
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    public int getAno() {
        return ano;
    }
    
    public void setAno(int ano) {
        this.ano = ano;
    }
    
    public double getValorDiaria() {
        return valorDiaria;
    }
    
    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s (%d) - R$ %.2f/dia", 
                           getClass().getSimpleName(), modelo, ano, valorDiaria);
    }
    
    // Método para serializar os dados para arquivo
    public String toFileString() {
        return String.format("%s;%s;%s;%d;%.2f", 
                           getClass().getSimpleName(), modelo, placa, ano, valorDiaria);
    }
}