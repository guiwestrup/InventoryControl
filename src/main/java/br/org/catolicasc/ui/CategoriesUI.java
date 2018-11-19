package br.org.catolicasc.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoriesUI {
    private JButton novoButton;
    private JButton apagarButton;
    private JButton voltarButton;
    private JTable table1;
    private JPanel categoriesMain;
    private JFrame categoriesFrame;

    public CategoriesUI() {
        JFrame categoriesFrame = new JFrame("Busca de Categoria");
        categoriesFrame.setContentPane(categoriesMain);
        categoriesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        categoriesFrame.pack();
        categoriesFrame.setLocationRelativeTo(null);
        categoriesFrame.setVisible(true);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                categoriesFrame.dispose();
            }
        });
        novoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewCategoryUI();
            }
        });
    }

}
