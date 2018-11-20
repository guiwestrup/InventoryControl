package br.org.catolicasc.ui;

import br.org.catolicasc.model.Category;
import br.org.catolicasc.model.Product;
import br.org.catolicasc.model.UnitType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class PesquisaProdutosUi {
    private JTextField textField1;

    // sempre deve haver um "DefaultTableModel" para o Jtable (pois é onde controla os campos e etc)
    private JTable productsTable;
    DefaultTableModel productsTableModel;

    private JPanel pesquisaProdutosMain;

    public PesquisaProdutosUi() {
        JFrame pesquisaProdutosFrame = new JFrame("Pesquisa Produtos");
        pesquisaProdutosFrame.setContentPane(pesquisaProdutosMain);
        pesquisaProdutosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pesquisaProdutosFrame.pack();
        pesquisaProdutosFrame.setLocationRelativeTo(null);
        pesquisaProdutosFrame.setVisible(true);


        String[] colunas = { "Id", "Nome", "Marca", "Categoria", "Unidade", "Cean"};
        // cria uma model
        productsTableModel = new DefaultTableModel(new Object[][]{}, colunas);
        // seta o model na tabela
        productsTable.setModel(productsTableModel);

        // cria uma lista e coloca valores
        List<Product> listP = new ArrayList<>(); // poderia ser a lista traziada do DAO, ex: listP = ProductDao.getNewInstance().getAll()
        listP.add(new Product("Mouse", "129038", "red dragon", new Category("Hardware"), UnitType.EMBALAGEM));

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
                            p.getCean()
                    }
            );
        }
    }

    public static void main(String[] args) {
        new PesquisaProdutosUi();
    }
}
