import java.io.*;
import java.util.*;

public class ArquivoSimples {
    
    public static void salvar(ArrayList<VeiculoSimples> lista) {
        try {
            PrintWriter writer = new PrintWriter("dados.txt");
            for (VeiculoSimples v : lista) {
                writer.println(v.paraArquivo());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar");
        }
    }
    
    public static ArrayList<VeiculoSimples> carregar() {
        ArrayList<VeiculoSimples> lista = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("dados.txt"));
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String[] partes = linha.split(";");
                
                String tipo = partes[0];
                String modelo = partes[1];
                String placa = partes[2];
                double valor = Double.parseDouble(partes[3]);
                
                if (tipo.equals("VeiculoPopularSimples")) {
                    lista.add(new VeiculoPopularSimples(modelo, placa, valor));
                } else {
                    lista.add(new VeiculoLuxoSimples(modelo, placa, valor));
                }
            }
            sc.close();
        } catch (Exception e) {
            // Se não conseguir carregar, retorna lista vazia
        }
        return lista;
    }
}