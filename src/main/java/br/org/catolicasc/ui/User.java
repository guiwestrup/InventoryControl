package br.org.catolicasc.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User {
    private JButton button_msg;
    private JPanel panelMain;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JPasswordField passwordField1;
    private JButton salvarButton;
    private JButton cancelarButton;

    public User() {
        button_msg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Hello");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("User");
        frame.setContentPane(new User().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
