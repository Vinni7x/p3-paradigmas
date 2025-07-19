# Sistema de Locadora de Carros

Sistema desenvolvido em Java com interface gráfica Swing para gerenciamento de uma locadora de carros.

## Funcionalidades

✅ **Interface Gráfica com Swing**: Interface construída manualmente usando javax.swing  
✅ **Cadastro de Veículos**: Adicionar veículos com modelo, placa, ano e valor da diária  
✅ **Listagem de Veículos**: Visualizar todos os veículos cadastrados  
✅ **Exclusão de Veículos**: Remover veículos da lista  
✅ **Tipos de Veículos**: Popular e Luxo com comportamentos diferentes  
✅ **Herança e Polimorfismo**: Classes com métodos sobrescritos reais  
✅ **Persistência em Arquivo**: Dados salvos em arquivo texto (veiculos.txt)  
✅ **Collections**: Uso de ArrayList<Veiculo> para armazenamento em memória  
✅ **Cálculo de Aluguel**: Calcular valor do aluguel com regras específicas por tipo  

## Estrutura do Projeto

- `Veiculo.java` - Classe abstrata base
- `VeiculoPopular.java` - Classe para veículos populares (com desconto para locações longas)
- `VeiculoLuxo.java` - Classe para veículos de luxo (com taxa adicional e seguro premium)
- `GerenciadorArquivos.java` - Classe para persistência de dados
- `SistemaLocadoraGUI.java` - Interface gráfica principal

## Diferenças entre os Tipos de Veículos

### Veículo Popular
- **Desconto**: 5% de desconto para locações de 7 dias ou mais
- **Método específico**: `isEconomico()` - verifica se o valor da diária é inferior a R$ 80,00

### Veículo de Luxo
- **Taxa adicional**: 15% sobre o valor base
- **Seguro premium**: R$ 25,00 por dia
- **Métodos específicos**: 
  - `calcularSeguroPremium(int dias)` - calcula o valor do seguro
  - `getTaxaLuxo()` - retorna a taxa de luxo aplicada

## Como Compilar e Executar

### Pré-requisitos
- Java JDK 8 ou superior instalado
- Terminal/Prompt de comando

### Compilação
```bash
javac *.java
```

### Execução
```bash
java SistemaLocadoraGUI
```

## Como Usar

1. **Adicionar Veículo**:
   - Preencha os campos: Modelo, Placa, Ano, Valor Diária
   - Selecione o tipo (Popular ou Luxo)
   - Clique em "Adicionar"

2. **Remover Veículo**:
   - Selecione um veículo na lista
   - Clique em "Remover"
   - Confirme a remoção

3. **Calcular Aluguel**:
   - Selecione um veículo na lista
   - Digite o número de dias
   - Clique em "Calcular Aluguel"
   - O valor será exibido considerando as regras específicas do tipo de veículo

## Persistência de Dados

Os dados são automaticamente salvos no arquivo `veiculos.txt` no formato:
```
TipoVeiculo;Modelo;Placa;Ano;ValorDiaria
```

Exemplo:
```
VeiculoPopular;Civic;ABC-1234;2020;65.00
VeiculoLuxo;BMW X5;XYZ-9876;2022;250.00
```

## Características Técnicas

- **Herança**: VeiculoPopular e VeiculoLuxo herdam de Veiculo
- **Polimorfismo**: Método `calcularAluguel(int dias)` implementado de forma diferente em cada subclasse
- **Encapsulamento**: Atributos protegidos com getters e setters
- **Collections**: ArrayList<Veiculo> para gerenciamento em memória
- **Interface Gráfica**: Swing com layout organizado em painéis
- **Tratamento de Exceções**: Validações de entrada e tratamento de erros
- **Persistência**: Leitura e escrita em arquivo texto

## Validações Implementadas

- Campos obrigatórios não podem estar vazios
- Ano deve estar entre 1900 e 2030
- Valor da diária deve ser positivo
- Placa não pode ser duplicada
- Número de dias para cálculo deve ser positivo