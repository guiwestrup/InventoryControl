package br.org.catolicasc.ui;

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
    private JButton novoUsuárioButton;
    private JButton buscaUsuáriosButton;

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
    }

    public static void main(String[] args) {

    }

}
