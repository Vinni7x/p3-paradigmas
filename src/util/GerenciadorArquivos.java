package src.util;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import src.factory.VeiculoFactory;
import src.model.Veiculo;

/**
 * Classe responsável pelo armazenamento persistente dos veículos
 * Implementando o padrão DAO
 */
public class GerenciadorArquivos {
    private static final String NOME_ARQUIVO = "veiculos.txt";
    
    public static void salvarVeiculos(List<Veiculo> veiculos) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Veiculo veiculo : veiculos) {
                writer.println(veiculo.toFileString());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }
    
    public static List<Veiculo> carregarVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();
        File arquivo = new File(NOME_ARQUIVO);
        
        if (!arquivo.exists()) {
            return veiculos; // Retorna lista vazia se arquivo não existe
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Veiculo veiculo = parsearLinha(linha);
                if (veiculo != null) {
                    veiculos.add(veiculo);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar arquivo: " + e.getMessage());
        }
        
        return veiculos;
    }
    
    private static Veiculo parsearLinha(String linha) {
        try {
            String[] partes = linha.split(";");
            if (partes.length != 5) {
                return null;
            }
            
            String tipo = partes[0];
            String modelo = partes[1];
            String placa = partes[2];
            int ano = Integer.parseInt(partes[3]);
            double valorDiaria = Double.parseDouble(partes[4]);
            
            // Usar o factory para criar o veículo
            return VeiculoFactory.criarVeiculo(tipo, modelo, placa, ano, valorDiaria);
            
        } catch (NumberFormatException e) {
            System.err.println("Erro ao parsear linha: " + linha);
        }
        
        return null;
    }
}