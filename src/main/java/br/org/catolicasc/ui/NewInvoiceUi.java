package br.org.catolicasc.ui;

import br.org.catolicasc.dao.ProductDao;
import br.org.catolicasc.dao.VendorDao;
import br.org.catolicasc.model.Product;
import br.org.catolicasc.model.Vendor;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class NewInvoiceUi {
    private JPanel mainPanel;
    private JTextField buscaTextField;
    private JTextField nomeTextField;
    private JFormattedTextField quantidadeFormattedTextField;
    private JFormattedTextField valorDeCustoFormattedTextField;
    private JButton confirmarButton;
    private JButton retirarButton;
    private JTable productsTable;
    private JLabel idProdutoLabel;
    private JTable selecionadosTable;
    private JTextField buscaFornecedorText;
    private JTable fornecedorTable;
    private JButton finalizarButton;
    private JTextField fornecedorIdText;
    private JTextField fornecedorNomeText;
    private JFormattedTextField totalText;
    private DefaultTableModel productsTableModel;
    private DefaultTableModel fornecedorTableModel;
    private DefaultTableModel selecionadosTableModel;
    private float total;


    public  NewInvoiceUi(){
        JFrame invoiceFrame = new JFrame("Entrada de Notas");
        invoiceFrame.setContentPane(mainPanel);
        invoiceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        invoiceFrame.pack();
        invoiceFrame.setLocationRelativeTo(null);
        invoiceFrame.setVisible(true);

        //iniciando total
        this.total = 0;
        totalText.setValue(this.total);


        //TABLE PRODUTOS
        String[] colunasProduto = { "Id", "Nome", "Marca", "Categoria", "Unidade", "Cean", "Valor de Custo"};
        // cria uma model para a table de produtos
        productsTableModel = new DefaultTableModel(new Object[][]{}, colunasProduto);
        // seta o model na tabela
        productsTable.setModel(productsTableModel);
        findProds();


        //TABLE FORNECEDOR
        String[] colunasFornecedor = { "Id", "Razão", "Nome Fantasia", "Telefone", "Cidade"};
        // cria uma model para a table de produtos
        fornecedorTableModel = new DefaultTableModel(new Object[][]{}, colunasFornecedor);
        // seta o model na tabela
        fornecedorTable.setModel(fornecedorTableModel);
        findFornecedor();


        //TABLE PARA SELEÇÃO DE PRODUTOS
        //coluna para a table selecionados
        String[] colunasSelecionados = { "Id","Nome","Quantidade","Valor de Custo"};
        // cria uma model para a table de selecionados
        selecionadosTableModel = new DefaultTableModel(new Object[][]{}, colunasSelecionados);
        selecionadosTable.setModel(selecionadosTableModel);

        productsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int idProduto = (int)productsTable.getValueAt(productsTable.getSelectedRow(), 0);
                String nomeProduto = (String)productsTable.getValueAt(productsTable.getSelectedRow(),1);
                Float custo = (Float)productsTable.getValueAt(productsTable.getSelectedRow(),6);
                idProdutoLabel.setText(String.valueOf(idProduto));
                nomeTextField.setText(nomeProduto);
                valorDeCustoFormattedTextField.setValue(custo);
            }
        });

        fornecedorTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int idFornecedor = (int)fornecedorTable.getValueAt(fornecedorTable.getSelectedRow(), 0);
                String nomeFornecedor = (String)fornecedorTable.getValueAt(fornecedorTable.getSelectedRow(),1);
                fornecedorIdText.setText(String.valueOf(idFornecedor));
                fornecedorNomeText.setText(nomeFornecedor);
            }
        });

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(quantidadeFormattedTextField.equals("")){
                    JOptionPane.showMessageDialog(null,"Quantidade não pode ficar em branco!");
                }
                else{
                    inseriSelecionadosProduto();
                    System.out.println(quantidadeFormattedTextField.getText());
                    System.out.println(valorDeCustoFormattedTextField.getText());
                    float value = Integer.parseInt(quantidadeFormattedTextField.getText()) * Float.parseFloat(valorDeCustoFormattedTextField.getText());
                    System.out.println(value);
                    atualizaTotal(value);
                }
            }
        });
        buscaTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                findProds();
            }
        });
        buscaFornecedorText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                findFornecedor();
            }
        });
        totalText.addComponentListener(new ComponentAdapter() {
        });
        retirarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(selecionadosTable.getValueAt(selecionadosTable.getSelectedRow(), 2));
                int quantity = (int)selecionadosTable.getValueAt(selecionadosTable.getSelectedRow(), 2);
                System.out.println(quantity);
                float custo = (float)selecionadosTable.getValueAt(selecionadosTable.getSelectedRow(), 3);
                selecionadosTable.removeRowSelectionInterval(selecionadosTable.getSelectedRow(),selecionadosTable.getSelectedRow());
                float totalRetirar = quantity*custo;
                atualizaTotal((totalRetirar*(-1)));
            }
        });
    }

    private void findProds(){
        // cria uma lista e coloca valores
        List<Product> listP = ProductDao.getNewInstance().getAllWithWhere(" name like '%" + buscaTextField.getText() + "%'");
        productsTableModel.setRowCount(0); // limpa a tabela
        for (Product p: listP) {
            // seguindo a ordem das colunas
            productsTableModel.addRow(
                    new Object[]{
                            p.getId(),
                            p.getName(),
                            p.getMarca(),
                            (p.getCategoria() != null ? p.getCategoria().getName() : "null"), // quando contem um OBJ, melhor usar comparador ternario para verificar se &eacute; null, pra n&atilde;o explodir
                            p.getUnit(),
                            p.getCean(),
                            p.getCostValue()
                    }
            );
        }
    }

    private void inseriSelecionadosProduto(){
        selecionadosTableModel.addRow(
                new Object[]{
                        idProdutoLabel.getText(),
                        nomeTextField.getText(),
                        quantidadeFormattedTextField.getText(),
                        valorDeCustoFormattedTextField.getText()
                }
        );
    }

    private void findFornecedor(){
        List<Vendor> listV = VendorDao.getNewInstance().getAllWithWhere("trade like '%" + buscaFornecedorText.getText() + "%'");
        fornecedorTableModel.setRowCount(0);
        for(Vendor v: listV){
            fornecedorTableModel.addRow(
                    new Object[]{
                            v.getId(),
                            v.getCompany(),
                            v.getTrade(),
                            v.getPhone(),
                            v.getCity()
                    }
            );
        }
        
    }

    private void atualizaTotal(float value){
        System.out.println(value);
        System.out.println("total" + this.total);
        this.total = this.total+value;


        totalText.setValue(this.total);
    }

    public static void main(String[] args) {
        new NewInvoiceUi();
    }
}
