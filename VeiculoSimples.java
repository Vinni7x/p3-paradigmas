public abstract class VeiculoSimples {
    protected String modelo;
    protected String placa;
    protected double valorDiaria;
    
    public VeiculoSimples(String modelo, String placa, double valorDiaria) {
        this.modelo = modelo;
        this.placa = placa;
        this.valorDiaria = valorDiaria;
    }
    
    // Método abstrato - POLIMORFISMO obrigatório
    public abstract double calcularAluguel(int dias);
    
    // Getters básicos
    public String getModelo() { return modelo; }
    public String getPlaca() { return placa; }
    public double getValorDiaria() { return valorDiaria; }
    
    @Override
    public String toString() {
        return modelo + " - " + placa + " - R$ " + valorDiaria;
    }
    
    // Para salvar no arquivo
    public String paraArquivo() {
        return getClass().getSimpleName() + ";" + modelo + ";" + placa + ";" + valorDiaria;
    }
}