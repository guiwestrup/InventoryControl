package br.org.catolicasc.ui;

import org.hibernate.resource.transaction.backend.jta.internal.JtaIsolationDelegate;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class PesquisaProdutosUi {
    private JTextField textField1;
    private JTable productsTable;
    private JPanel pesquisaProdutosMain;

    public PesquisaProdutosUi() {
        JFrame pesquisaProdutosFrame = new JFrame("Pesquisa Produtos");
        pesquisaProdutosFrame.setContentPane(pesquisaProdutosMain);
        pesquisaProdutosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pesquisaProdutosFrame.pack();
        pesquisaProdutosFrame.setLocationRelativeTo(null);
        pesquisaProdutosFrame.setVisible(true);
        String[] colunas = { "Id", "Nome", "Marca", "Categoria", "Unidade", "Cean"};
        Object[][] data = {{"1","Mouse","red dragon","hardware","Caixa","129038"},{"2","Teclado","red dragon","hardware","Caixa","129038"}};

    }

    public static void main(String[] args) {
        new PesquisaProdutosUi();
    }
}
