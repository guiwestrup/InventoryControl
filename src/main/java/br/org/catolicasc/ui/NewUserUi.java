package br.org.catolicasc.ui;

import br.org.catolicasc.dao.UserDao;
import br.org.catolicasc.model.Role;
import br.org.catolicasc.model.State;
import br.org.catolicasc.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewUserUi {
    private JTextField loginText;
    private JPasswordField senhaText;
    private JLabel SENHA;
    private JPasswordField confirmaText;
    private JTextField enderecoText;
    private JTextField cidadeText;
    private JButton salvarButton;
    private JButton limparButton;
    private JButton voltarButton;
    private JPanel newUserMain;
    private JComboBox estadoCombo;
    private JComboBox nivelCombo;

    private boolean isEdit;
    private User user;
    public NewUserUi(UserUi u){
        user = new User();
        JFrame newUserFrame = new JFrame("");
        newUserFrame.setContentPane(newUserMain);
        newUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newUserFrame.pack();
        newUserFrame.setLocationRelativeTo(null);
        newUserFrame.setVisible(true);

        for (State uni : State.values()){
            estadoCombo.addItem(uni);
        }

        for (Role ro : Role.values()){
            nivelCombo.addItem(ro);
        }

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginText.equals("") && senhaText.equals("")){
                    JOptionPane.showMessageDialog(null,"LOGIN e SENHA não podem ficar em branco!");
                }
                else if(senhaText.getText().equals(confirmaText.getText())){
                    if(!isEdit){
                        State estado = (State) estadoCombo.getSelectedItem();
                        Role role = (Role) nivelCombo.getSelectedItem();
                        user = new User(loginText.getText(),senhaText.getText(),enderecoText.getText(),cidadeText.getText(),estado,role);
                    }
                    int a = UserDao.getNewInstance().modify(user);
                    if(a>0){
                        if(!isEdit){
                            JOptionPane.showMessageDialog(null,"Usuário cadastrado com sucesso");
                        }
                        newUserFrame.dispose();
                        u.findUsers();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Senha e confirmação precisam ser iguais!");
                }
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginText.setText("");
                senhaText.setText("");
                confirmaText.setText("");
                enderecoText.setText("");
                cidadeText.setText("");

            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newUserFrame.dispose();
            }
        });
    }

    public void setUserToEdit(User u){
        isEdit = true;
        user = u;
        //TODO SETAR ESTADOS
        loginText.setText(u.getName());
        enderecoText.setText(u.getAddress());
        cidadeText.setText(u.getCity());
        estadoCombo.setSelectedItem(u.getState());
        nivelCombo.setSelectedItem(u.getRole());
    }

}
