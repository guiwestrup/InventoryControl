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
    private JPasswordField senhaText;
    private JFormattedTextField loginText;
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
                loginText.setText("");
                senhaText.setText("");
                loginText.requestFocus(true);
            }
        });

        loginText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    executaLogin();
                }
            }
        });
        senhaText.addKeyListener(new KeyAdapter() {
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
        String login = loginText.getText();
        String senha = senhaText.getText();
        if(login.equals("") && senha.equals("")){
            JOptionPane.showMessageDialog(null, "Senha e login precisam ser preenchidos!");
            loginText.requestFocus(true);
        }
        else{
            List<User> listUser =  UserDao.getNewInstance().getAllWithWhere("name='" + loginText.getText() + "'");
            if(!listUser.isEmpty() && listUser.get(0).getPassword().equals(senha)){
                new Main(listUser.get(0).getName());
                this.close();
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

    public void close(){
        System.out.println("cheguei");
        loginFrame.dispose();
    }

}
