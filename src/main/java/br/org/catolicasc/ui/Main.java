package br.org.catolicasc.ui;

import br.org.catolicasc.dao.UserDao;
import br.org.catolicasc.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private JPanel mainPanel;
    private JLabel userLabel;
    private JPanel productPanel;
    private JButton novoProdutoButton;
    private JButton buscaProdutosButton;
    private JButton categoriaButton;
    private JButton fornecedorButton;
    private JButton entradaNotaButton;
    private JButton buscaUsuariosButton;
    private JButton listaDeNotasButton;

    public Main(String user){
        JFrame mainFrame = new JFrame("Sistema de inventário");
        userLabel.setText(user.toUpperCase());
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        novoProdutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadProdutoUI();
            }
        });
        categoriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CategoriesUI();
            }
        });
        buscaProdutosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PesquisaProdutosUi();
            }
        });
        entradaNotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewInvoiceUi();
            }
        });
        listaDeNotasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InvoicesUi();
            }
        });
        buscaUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserUi();
            }
        });
        fornecedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VendorUi();
            }
        });
    }

    public static void main(String[] args) {
        User s = UserDao.getNewInstance().getById(1);
        new Main(s.getName());
    }

}
