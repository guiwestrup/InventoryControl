package br.org.catolicasc.ui;

import br.org.catolicasc.dao.CategoryDao;
import br.org.catolicasc.model.Category;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewCategoryUI {
    private JFormattedTextField nomeText;
    private JPanel newCategoryMain;
    private JButton salvarButton;
    private JButton voltarButton;
    private JFrame newCategoryFrame;

    public NewCategoryUI() {
        JFrame newCategoryFrame = new JFrame("");
        newCategoryFrame.setContentPane(newCategoryMain);
        newCategoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newCategoryFrame.pack();
        newCategoryFrame.setLocationRelativeTo(null);
        newCategoryFrame.setVisible(true);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCategoryFrame.dispose();
            }
        });

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nomeText.equals("")){
                    JOptionPane.showMessageDialog(null,"o nome da categoria não pode ficar em branco!");
                }
                else{
                    Category category = new Category(nomeText.getText());
                    System.out.println(category);
                    int a = CategoryDao.getNewInstance().modify(category);
                    if(a>0){
                        JOptionPane.showMessageDialog(null,"Categoria cadastrada com sucesso");
                        newCategoryFrame.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Algum problema na inserção.");
                    }
                }

            }
        });
    }


}
