import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SistemaSimples extends JFrame {
    private ArrayList<VeiculoSimples> veiculos;
    private DefaultListModel<VeiculoSimples> listModel;
    
    private JTextField txtModelo, txtPlaca, txtValor, txtDias;
    private JComboBox<String> cmbTipo;
    private JList<VeiculoSimples> lista;
    private JLabel lblResultado;
    
    public SistemaSimples() {
        setTitle("Locadora Simples");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        veiculos = ArquivoSimples.carregar();
        listModel = new DefaultListModel<>();
        for (VeiculoSimples v : veiculos) {
            listModel.addElement(v);
        }
        
        criarInterface();
    }
    
    private void criarInterface() {
        // Painel superior - cadastro
        JPanel painelCadastro = new JPanel(new GridLayout(4, 2));
        
        painelCadastro.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        painelCadastro.add(txtModelo);
        
        painelCadastro.add(new JLabel("Placa:"));
        txtPlaca = new JTextField();
        painelCadastro.add(txtPlaca);
        
        painelCadastro.add(new JLabel("Valor/dia:"));
        txtValor = new JTextField();
        painelCadastro.add(txtValor);
        
        painelCadastro.add(new JLabel("Tipo:"));
        cmbTipo = new JComboBox<>(new String[]{"Popular", "Luxo"});
        painelCadastro.add(cmbTipo);
        
        // Painel de botões
        JPanel painelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnRemover = new JButton("Remover");
        
        btnAdicionar.addActionListener(e -> adicionar());
        btnRemover.addActionListener(e -> remover());
        
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnRemover);
        
        // Lista de veículos
        lista = new JList<>(listModel);
        
        // Painel inferior - cálculo
        JPanel painelCalculo = new JPanel();
        painelCalculo.add(new JLabel("Dias:"));
        txtDias = new JTextField(5);
        painelCalculo.add(txtDias);
        
        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.addActionListener(e -> calcular());
        painelCalculo.add(btnCalcular);
        
        lblResultado = new JLabel("Resultado aqui");
        painelCalculo.add(lblResultado);
        
        // Montagem
        add(painelCadastro, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(new JScrollPane(lista), BorderLayout.EAST);
        add(painelCalculo, BorderLayout.SOUTH);
    }
    
    private void adicionar() {
        String modelo = txtModelo.getText();
        String placa = txtPlaca.getText();
        String valorStr = txtValor.getText();
        String tipo = (String) cmbTipo.getSelectedItem();
        
        if (modelo.isEmpty() || placa.isEmpty() || valorStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }
        
        try {
            double valor = Double.parseDouble(valorStr);
            
            VeiculoSimples veiculo;
            if (tipo.equals("Popular")) {
                veiculo = new VeiculoPopularSimples(modelo, placa, valor);
            } else {
                veiculo = new VeiculoLuxoSimples(modelo, placa, valor);
            }
            
            veiculos.add(veiculo);
            listModel.addElement(veiculo);
            ArquivoSimples.salvar(veiculos);
            
            txtModelo.setText("");
            txtPlaca.setText("");
            txtValor.setText("");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Valor inválido!");
        }
    }
    
    private void remover() {
        int index = lista.getSelectedIndex();
        if (index >= 0) {
            veiculos.remove(index);
            listModel.removeElementAt(index);
            ArquivoSimples.salvar(veiculos);
        }
    }
    
    private void calcular() {
        int index = lista.getSelectedIndex();
        if (index < 0) {
            lblResultado.setText("Selecione um veículo");
            return;
        }
        
        try {
            int dias = Integer.parseInt(txtDias.getText());
            VeiculoSimples veiculo = veiculos.get(index);
            double valor = veiculo.calcularAluguel(dias);
            
            String resultado = "R$ " + valor;
            
            // USO dos métodos específicos
            if (veiculo instanceof VeiculoPopularSimples) {
                VeiculoPopularSimples popular = (VeiculoPopularSimples) veiculo;
                if (popular.isBarato()) {
                    resultado += " (Barato!)";
                }
            } else if (veiculo instanceof VeiculoLuxoSimples) {
                VeiculoLuxoSimples luxo = (VeiculoLuxoSimples) veiculo;
                resultado += " (Taxa: " + (luxo.getTaxa() * 100) + "%)";
            }
            
            lblResultado.setText(resultado);
            
        } catch (Exception e) {
            lblResultado.setText("Dias inválido");
        }
    }
    
    public static void main(String[] args) {
        new SistemaSimples().setVisible(true);
    }
}