package br.org.catolicasc.ui;

import br.org.catolicasc.model.State;
import br.org.catolicasc.model.Vendor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterVendorUI {
    private JTextField razaoText;
    private JTextField fantasiaText;
    private JTextField cnpjText;
    private JTextField cidadeText;
    private JTextField cepText;
    private JTextField emailText;
    private JComboBox stateComboBox;
    private JButton salvarButton;
    private JButton limparButton;
    private JButton voltarButton;
    private JPanel mainPanel;
    private JTextField enderecoText;
    private boolean isEdit;
    private Vendor ven;
    public RegisterVendorUI(VendorUi v) {
        ven = new Vendor();
        JFrame RegisterVendorFrame = new JFrame("");
        RegisterVendorFrame.setContentPane(mainPanel);
        RegisterVendorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        RegisterVendorFrame.pack();
        RegisterVendorFrame.setLocationRelativeTo(null);
        RegisterVendorFrame.setVisible(true);

        for(State s: State.values()){
            stateComboBox.addItem(s);
        }

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterVendorFrame.dispose();
            }
        });
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInputs();
            }
        });
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void setVendorToEdit(Vendor v){
        isEdit = true;
        ven = v;
        clearInputs();
        if(v!=null){

        }

    }

    private void clearInputs(){
        razaoText.setText("");
        fantasiaText.setText("");
        cnpjText.setText("");
        cidadeText.setText("");
        cepText.setText("");
        emailText.setText("");
    }
}
