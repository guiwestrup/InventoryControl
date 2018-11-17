package br.org.catolicasc.ui;

import br.org.catolicasc.dao.UserDao;
import br.org.catolicasc.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Login {
    private JPasswordField textSenha;
    private JFormattedTextField textLogin;
    private JButton connectButton;
    private JButton clearButton;
    private JCheckBox rememberInfo;
    private JPanel mainPanel;
    private JLabel marcaLabel;
    private JFrame loginFrame;
    public Login() {
        loginFrame = new JFrame("Sistema de Inventário");
        rememberInfo.setText("Lembrar dados");
        marcaLabel.setText("WS Prosystem");
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executaLogin();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textLogin.setText("");
                textSenha.setText("");
                textLogin.requestFocus(true);
            }
        });

        textLogin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    executaLogin();
                }
            }
        });
        textSenha.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    executaLogin();
                }
            }
        });

    }

    public static void main(String[] args) {
        new Login().createUIComponents();
    }


    private void executaLogin(){
        String login = textLogin.getText();
        String senha = textSenha.getText();
        if(login.equals("") && senha.equals("")){
            JOptionPane.showMessageDialog(null, "Senha e login precisam ser preenchidos!");
            textLogin.requestFocus(true);
        }
        else{
            List<User> listUser =  UserDao.getNewInstance().getAllWithWhere("name='" + textLogin.getText() + "'");
            if(!listUser.isEmpty() && listUser.get(0).getPassword().equals(senha)){
                new Main(listUser.get(0).getName());
                loginFrame.dispose();
            }else{
                JOptionPane.showMessageDialog(null,"não encontrado");
            }

        }
    }


    private void createUIComponents() {
        loginFrame.setContentPane(new Login().mainPanel);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

}
