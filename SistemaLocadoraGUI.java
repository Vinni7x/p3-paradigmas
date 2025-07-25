 import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SistemaLocadoraGUI extends JFrame {
    private List<Veiculo> veiculos;
    private DefaultListModel<Veiculo> listModel;

    // Componentes da interface
    private JTextField txtModelo;
    private JTextField txtPlaca;
    private JTextField txtAno;
    private JTextField txtValorDiaria;
    private JComboBox<String> cmbTipoVeiculo;
    private JList<Veiculo> lstVeiculos;
    private JButton btnAdicionar;
    private JButton btnRemover;
    private JButton btnAlterar;
    private JButton btnCalcularAluguel;
    private JTextField txtDias;
    private JLabel lblResultadoCalculo;
    private boolean modoEdicao = false;
    private int indiceEdicao = -1;

    public SistemaLocadoraGUI() {
        initializeComponents();
        setupLayout();
        setupEventListeners();
        carregarDados();
    }

    private void initializeComponents() {
        // Configuração da janela principal
        setTitle("Sistema de Locadora de Carros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Inicializar dados
        veiculos = new ArrayList<>();
        listModel = new DefaultListModel<>();

        // Campos de entrada
        txtModelo = new JTextField(20);
        txtPlaca = new JTextField(20);
        txtAno = new JTextField(20);
        txtValorDiaria = new JTextField(20);

        // ComboBox para tipo de veículo
        cmbTipoVeiculo = new JComboBox<>(new String[]{"Popular", "Luxo"});

        // Lista de veículos
        lstVeiculos = new JList<>(listModel);
        lstVeiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Botões
        btnAdicionar = new JButton("Adicionar");
        btnRemover = new JButton("Remover");
        btnAlterar = new JButton("Alterar");
        btnCalcularAluguel = new JButton("Calcular Aluguel");

        // Campo para dias de aluguel
        txtDias = new JTextField(10);
        lblResultadoCalculo = new JLabel("Selecione um veículo e informe os dias");
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        // Painel principal com margem
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de cadastro (lado esquerdo)
        JPanel cadastroPanel = createCadastroPanel();

        // Painel de lista (lado direito)
        JPanel listaPanel = createListaPanel();

        // Painel de cálculo (parte inferior)
        JPanel calculoPanel = createCalculoPanel();

        mainPanel.add(cadastroPanel, BorderLayout.WEST);
        mainPanel.add(listaPanel, BorderLayout.CENTER);
        mainPanel.add(calculoPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createCadastroPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Cadastro de Veículo"));
        panel.setPreferredSize(new Dimension(300, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Modelo
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Modelo:"), gbc);
        gbc.gridx = 1;
        panel.add(txtModelo, gbc);

        // Placa
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Placa:"), gbc);
        gbc.gridx = 1;
        panel.add(txtPlaca, gbc);

        // Ano
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Ano:"), gbc);
        gbc.gridx = 1;
        panel.add(txtAno, gbc);

        // Valor da diária
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Valor Diária (R$):"), gbc);
        gbc.gridx = 1;
        panel.add(txtValorDiaria, gbc);

        // Tipo de veículo
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        panel.add(cmbTipoVeiculo, gbc);

        // Botões
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(btnAdicionar, gbc);

        gbc.gridy = 6;
        panel.add(btnRemover, gbc);
        gbc.gridy = 7;
        panel.add(btnAlterar, gbc);

        return panel;
    }

    private JPanel createListaPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Veículos Cadastrados"));

        JScrollPane scrollPane = new JScrollPane(lstVeiculos);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createCalculoPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(new TitledBorder("Cálculo de Aluguel"));

        panel.add(new JLabel("Dias de aluguel:"));
        panel.add(txtDias);
        panel.add(btnCalcularAluguel);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(lblResultadoCalculo);

        return panel;
    }

    private void setupEventListeners() {
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarVeiculo();
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerVeiculo();
            }
        });
        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarVeiculo();
            }
        });

        btnCalcularAluguel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularAluguel();
            }
        });
    }

    private void alterarVeiculo() {

        if (!modoEdicao) {
            // Primeiro clique: iniciar edição
            int selectedIndex = lstVeiculos.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um veículo na lista para alterar!",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Veiculo veiculo = veiculos.get(selectedIndex);
            txtModelo.setText(veiculo.getModelo());
            txtPlaca.setText(veiculo.getPlaca());
            txtAno.setText(String.valueOf(veiculo.getAno()));
            txtValorDiaria.setText(String.valueOf(veiculo.getValorDiaria()));
            cmbTipoVeiculo.setSelectedItem(
                    (veiculo instanceof VeiculoPopular) ? "Popular" : "Luxo"
            );

            modoEdicao = true;
            indiceEdicao = selectedIndex;
            btnAlterar.setText("Salvar Alterações");

        } else {
            // Segundo clique: salvar edição
            try {
                String modelo = txtModelo.getText().trim();
                String placa = txtPlaca.getText().trim();
                String anoStr = txtAno.getText().trim();
                String valorStr = txtValorDiaria.getText().trim();
                String tipo = (String) cmbTipoVeiculo.getSelectedItem();

                if (modelo.isEmpty() || placa.isEmpty() || anoStr.isEmpty() || valorStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int ano = Integer.parseInt(anoStr);
                double valorDiaria = Double.parseDouble(valorStr);

                if (ano < 1900 || ano > 2030) {
                    JOptionPane.showMessageDialog(this, "Ano deve estar entre 1900 e 2030!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (valorDiaria <= 0) {
                    JOptionPane.showMessageDialog(this, "Valor da diária deve ser positivo!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verifica se está tentando usar uma placa que já existe em outro veículo
                for (int i = 0; i < veiculos.size(); i++) {
                    if (i != indiceEdicao && veiculos.get(i).getPlaca().equalsIgnoreCase(placa)) {
                        JOptionPane.showMessageDialog(this, "Já existe outro veículo com essa placa!",
                                "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Atualiza ou recria o veículo
                Veiculo veiculoAtual = veiculos.get(indiceEdicao);

                if ((tipo.equals("Popular") && !(veiculoAtual instanceof VeiculoPopular)) ||
                        (tipo.equals("Luxo") && !(veiculoAtual instanceof VeiculoLuxo))) {

                    Veiculo novoVeiculo = tipo.equals("Popular")
                            ? new VeiculoPopular(modelo, placa, ano, valorDiaria)
                            : new VeiculoLuxo(modelo, placa, ano, valorDiaria);

                    veiculos.set(indiceEdicao, novoVeiculo);
                    listModel.set(indiceEdicao, novoVeiculo);

                } else {
                    veiculoAtual.setModelo(modelo);
                    veiculoAtual.setPlaca(placa);
                    veiculoAtual.setAno(ano);
                    veiculoAtual.setValorDiaria(valorDiaria);
                    listModel.set(indiceEdicao, veiculoAtual);
                }

                GerenciadorArquivos.salvarVeiculos(veiculos);
                limparCampos();



                JOptionPane.showMessageDialog(this, "Veículo alterado com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ano e valor da diária devem ser números válidos!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }

            // Resetar modo edição
            modoEdicao = false;
            indiceEdicao = -1;
            btnAlterar.setText("Alterar");
        }
    }
    private void limparCampos()
    {
        txtModelo.setText("");
        txtPlaca.setText("");
        txtAno.setText("");
        txtValorDiaria.setText("");
        cmbTipoVeiculo.setSelectedIndex(0);
        modoEdicao = false;
        indiceEdicao = -1;
        btnAlterar.setText("Alterar");
    }



    private void adicionarVeiculo() {
        try {
            String modelo = txtModelo.getText().trim();
            String placa = txtPlaca.getText().trim();
            String anoStr = txtAno.getText().trim();
            String valorStr = txtValorDiaria.getText().trim();
            String tipo = (String) cmbTipoVeiculo.getSelectedItem();

            // Validações
            if (modelo.isEmpty() || placa.isEmpty() || anoStr.isEmpty() || valorStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int ano = Integer.parseInt(anoStr);
            double valorDiaria = Double.parseDouble(valorStr);

            if (ano < 1900 || ano > 2030) {
                JOptionPane.showMessageDialog(this, "Ano deve estar entre 1900 e 2030!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (valorDiaria <= 0) {
                JOptionPane.showMessageDialog(this, "Valor da diária deve ser positivo!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar se a placa já existe
            for (Veiculo v : veiculos) {
                if (v.getPlaca().equalsIgnoreCase(placa)) {
                    JOptionPane.showMessageDialog(this, "Já existe um veículo com esta placa!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Criar veículo baseado no tipo
            Veiculo novoVeiculo;
            if (tipo.equals("Popular")) {
                novoVeiculo = new VeiculoPopular(modelo, placa, ano, valorDiaria);
            } else {
                novoVeiculo = new VeiculoLuxo(modelo, placa, ano, valorDiaria);
            }

            // Adicionar à lista
            veiculos.add(novoVeiculo);
            listModel.addElement(novoVeiculo);

            // Salvar no arquivo
            GerenciadorArquivos.salvarVeiculos(veiculos);

            // Limpar campos
            limparCampos();

            JOptionPane.showMessageDialog(this, "Veículo adicionado com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ano e valor da diária devem ser números válidos!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerVeiculo() {
        int selectedIndex = lstVeiculos.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um veículo para remover!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja remover este veículo?",
                "Confirmar Remoção",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            Veiculo veiculoRemovido = veiculos.remove(selectedIndex);
            listModel.removeElementAt(selectedIndex);

            // Salvar no arquivo
            GerenciadorArquivos.salvarVeiculos(veiculos);

            JOptionPane.showMessageDialog(this, "Veículo removido com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void calcularAluguel() {
        int selectedIndex = lstVeiculos.getSelectedIndex();
        if (selectedIndex == -1) {
            lblResultadoCalculo.setText("Selecione um veículo para calcular o aluguel");
            return;
        }

        try {
            String diasStr = txtDias.getText().trim();
            if (diasStr.isEmpty()) {
                lblResultadoCalculo.setText("Informe o número de dias");
                return;
            }

            int dias = Integer.parseInt(diasStr);
            if (dias <= 0) {
                lblResultadoCalculo.setText("Número de dias deve ser positivo");
                return;
            }

            Veiculo veiculo = veiculos.get(selectedIndex);
            double valor = veiculo.calcularAluguel(dias);

            lblResultadoCalculo.setText(String.format("Valor total: R$ %.2f (%d dias)", valor, dias));

        } catch (NumberFormatException ex) {
            lblResultadoCalculo.setText("Número de dias deve ser um valor válido");
        }
    }



    private void carregarDados() {
        veiculos = GerenciadorArquivos.carregarVeiculos();
        listModel.clear();
        for (Veiculo veiculo : veiculos) {
            listModel.addElement(veiculo);
        }
    }

    public static void main(String[] args) {
        // Configurar look and feel do sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Usar look and feel padrão se não conseguir configurar
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SistemaLocadoraGUI().setVisible(true);
            }
        });
    }
}
