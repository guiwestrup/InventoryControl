package br.org.catolicasc.ui;

import br.org.catolicasc.dao.ProductDao;
import br.org.catolicasc.model.Category;
import br.org.catolicasc.model.Product;
import br.org.catolicasc.model.UnitType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class PesquisaProdutosUi {
    private JTextField textField1;

    // sempre deve haver um "DefaultTableModel" para o Jtable (pois é onde controla os campos e etc)
    private JTable productsTable;
    DefaultTableModel productsTableModel;

    private JPanel pesquisaProdutosMain;
    private JButton fecharButton;
    private JButton editarButton;
    private JButton apagarButton;

    public PesquisaProdutosUi() {
        JFrame pesquisaProdutosFrame = new JFrame("Pesquisa Produtos");
        pesquisaProdutosFrame.setContentPane(pesquisaProdutosMain);
        pesquisaProdutosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pesquisaProdutosFrame.pack();
        pesquisaProdutosFrame.setLocationRelativeTo(null);
        pesquisaProdutosFrame.setVisible(true);

        PesquisaProdutosUi that = this;


        String[] colunas = { "Id", "Nome", "Marca", "Categoria", "Unidade", "Cean", "Valor de Custo"};
        // cria uma model
        productsTableModel = new DefaultTableModel(new Object[][]{}, colunas);
        // seta o model na tabela
        productsTable.setModel(productsTableModel);
        findProd();

        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                findProd();
            }
        });
        fecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisaProdutosFrame.dispose();
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = (int)productsTable.getValueAt(productsTable.getSelectedRow(), 0);
                if(id != 0){
                    CadProdutoUI c = new CadProdutoUI();
                    c.setProductToEdit(that, ProductDao.getNewInstance().getById(id));
                }
            }
        });
        apagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = (int)productsTable.getValueAt(productsTable.getSelectedRow(), 0);
                if(id != 0){
                    int r = ProductDao.getNewInstance().deleteById(id);
                    if(r == 0){
                        JOptionPane.showMessageDialog(null, "erro ao deletar");
                    }else{
                        findProd();
                    }
                }
            }
        });
    }

    public void findProd(){
        // cria uma lista e coloca valores
        List<Product> listP = ProductDao.getNewInstance().getAllWithWhere(" name like '%" + textField1.getText() + "%'");
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

    public static void main(String[] args) {
        new PesquisaProdutosUi();
    }
}
