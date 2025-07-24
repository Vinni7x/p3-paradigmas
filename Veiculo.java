public abstract class Veiculo {
    protected String modelo;
    protected String placa;
    protected int ano;
    protected double valorDiaria;
    
    public Veiculo(String modelo, String placa, int ano, double valorDiaria) {
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
        this.valorDiaria = valorDiaria;
    }
    
    // Método abstrato que deve ser implementado pelas subclasses
    public abstract double calcularAluguel(int dias);
    
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
        return String.format("%s - %s (%d) - %s- R$ %.2f/dia", 
                           getClass().getSimpleName(), modelo, ano, placa, valorDiaria);
    }
    
    // Método para serializar os dados para arquivo
    public String toFileString() {
        return String.format("%s;%s;%s;%d;%.2f", 
                           getClass().getSimpleName(), modelo, placa, ano, valorDiaria);
    }
}
