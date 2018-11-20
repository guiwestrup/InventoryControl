package br.org.catolicasc.ui;

import br.org.catolicasc.dao.CategoryDao;
import br.org.catolicasc.model.Category;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CategoriesUI {
    private JButton novoButton;
    private JButton apagarButton;
    private JButton voltarButton;
    private JTable table1;
    private DefaultTableModel categoriesTableModel;
    private JPanel categoriesMain;
    private JButton editarButton;
    private JFrame categoriesFrame;

    public CategoriesUI() {
        JFrame categoriesFrame = new JFrame("Busca de Categoria");
        categoriesFrame.setContentPane(categoriesMain);
        categoriesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        categoriesFrame.pack();
        categoriesFrame.setLocationRelativeTo(null);
        categoriesFrame.setVisible(true);

        CategoriesUI that = this;

        String[] colunas = { "Id", "Nome" };
        // cria uma model
        categoriesTableModel = new DefaultTableModel(new Object[][]{}, colunas);
        // seta o model na tabela
        table1.setModel(categoriesTableModel);
        findCategories();

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                categoriesFrame.dispose();
            }
        });
        novoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewCategoryUI(that);
            }
        });
        apagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = (int)table1.getValueAt(table1.getSelectedRow(), 0);
                if(id != 0){
                    int r = CategoryDao.getNewInstance().deleteById(id);
                    if(r == 0){
                        JOptionPane.showMessageDialog(null, "erro ao deletar");
                    }else{
                        findCategories();
                    }
                }
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = (int)table1.getValueAt(table1.getSelectedRow(), 0);
                if(id != 0){
                    NewCategoryUI c = new NewCategoryUI(that);
                    c.setCategaryToEdit(CategoryDao.getNewInstance().getById(id));
                }
            }
        });
    }

    public void findCategories(){
        // cria uma lista e coloca valores
        List<Category> listC = CategoryDao.getNewInstance().getAll();
        categoriesTableModel.setRowCount(0); // limpa a tabela
        for (Category p: listC) {
            // seguindo a ordem das colunas
            categoriesTableModel.addRow(
                    new Object[]{
                            p.getId(),
                            p.getName()
                    }
            );
        }
    }

    public static void main(String[] args) {
        new CategoriesUI();
    }
}
