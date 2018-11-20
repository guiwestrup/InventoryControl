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

    private boolean isEdit;
    private Category cat;

    public NewCategoryUI(CategoriesUI c) {
        cat = new Category();
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
                    if(!isEdit){
                        cat = new Category(nomeText.getText());
                    }
                    System.out.println(cat);
                    int a = CategoryDao.getNewInstance().modify(cat);
                    if(a>0){
                        if(!isEdit){
                            JOptionPane.showMessageDialog(null,"Categoria cadastrada com sucesso");
                        }
                        newCategoryFrame.dispose();
                        c.findCategories();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Algum problema na inserção.");
                    }
                }

            }
        });
    }

    public void setCategaryToEdit(Category c){
        isEdit = true;
        cat = c;
        nomeText.setText(c.getName());
    }
}
