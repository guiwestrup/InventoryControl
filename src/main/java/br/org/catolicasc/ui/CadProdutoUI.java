package br.org.catolicasc.ui;

import br.org.catolicasc.dao.ProductDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadProdutoUI {
    private JPanel cadastroProdutoMain;
    private JFormattedTextField nameText;
    private JComboBox categoryCombobox;
    private JSpinner quantitySpinner;
    private JTextField ceanText;
    private JButton limparButton;
    private JButton salvarButton;
    private JTextField marcaText;
    private JComboBox unityCombobox;
    private JButton fecharButton;
    private JFrame cadProdutoFrame;

    public CadProdutoUI() {
        JFrame cadProdutoFrame = new JFrame("Cadastro de Produto");
        cadProdutoFrame.setContentPane(cadastroProdutoMain);
        cadProdutoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cadProdutoFrame.pack();
        cadProdutoFrame.setLocationRelativeTo(null);
        cadProdutoFrame.setVisible(true);
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = (Integer) quantitySpinner.getValue();
                if(nameText.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Necessário inserir nome do produto");
                }
                else if(quantity <= 0){
                    JOptionPane.showMessageDialog(null, "Quantidade é necessário ser maior que 0");
                }
                else{

                }
                //JOptionPane.showMessageDialog(null,quantitySpinner.getValue());
            }
        });
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameText.setText("");
                quantitySpinner.setValue(0);
                ceanText.setText("");
                marcaText.setText("");
                nameText.requestFocus(true);
            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
