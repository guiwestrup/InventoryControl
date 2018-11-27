package br.org.catolicasc.ui;

import br.org.catolicasc.dao.VendorDao;
import br.org.catolicasc.model.State;
import br.org.catolicasc.model.Vendor;
import com.sun.codemodel.internal.JOp;
import jdk.nashorn.internal.scripts.JO;

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


    public RegisterVendorUI() {
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
                if(razaoText.equals("") || fantasiaText.equals("") || cnpjText.equals("")){
                    JOptionPane.showMessageDialog(null, "Razão, fantasia e cnpj são obrigatórios!");
                }
                else{
                    Vendor ven2 = new Vendor(razaoText.getText(),fantasiaText.getText(),cnpjText.getText(),enderecoText.getText(),null,emailText.getText(),cepText.getText(),cidadeText.getText(),State.valueOf(stateComboBox.getSelectedItem().toString()));
                    if(isEdit){
                        ven2.setId(ven.getId());
                    }
                    ven = ven2;
                    int a = VendorDao.getNewInstance().modify(ven);
                    if(a>0){
                        if(!isEdit){
                            JOptionPane.showMessageDialog(null,"Fornecedor cadastrado com sucesso.");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"problema na insercão do fornecedor.");
                    }
                }
            }
        });
    }

    public void setVendorToEdit(Vendor v){
        isEdit = true;
        ven = v;
        clearInputs();
        if(v!=null){
            razaoText.setText(v.getCompany());
            fantasiaText.setText(v.getTrade());
            cnpjText.setText(v.getCNPJ());
            enderecoText.setText(v.getAddress());
            cidadeText.setText(v.getCity());
            cepText.setText(v.getZipcode());
            emailText.setText(v.getEmail());
            for (int i = 0; i < stateComboBox.getItemCount(); i++) {
                if(stateComboBox.getItemAt(i).equals(v.getState())){
                    stateComboBox.setSelectedIndex(i);
                    break;
                }
            }
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
