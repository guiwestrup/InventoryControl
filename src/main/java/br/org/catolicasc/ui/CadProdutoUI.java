package br.org.catolicasc.ui;

import br.org.catolicasc.dao.CategoryDao;
import br.org.catolicasc.dao.ProductDao;
import br.org.catolicasc.model.Category;
import br.org.catolicasc.model.Product;
import br.org.catolicasc.model.UnitType;
import javafx.collections.FXCollections;
import sun.tools.jconsole.Plotter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CadProdutoUI {
    private JPanel cadastroProdutoMain;
    private JFormattedTextField nameText;
    private JComboBox<String> categoryCombobox;
    private JTextField ceanText;
    private JButton limparButton;
    private JButton salvarButton;
    private JTextField marcaText;
    private JComboBox<UnitType> unityCombobox;
    private JButton fecharButton;
    private JFrame cadProdutoFrame;

    public CadProdutoUI() {
        //populando lista de categorias
        List<Category> listCategory = getCategories();
        listCategory.forEach((temp) ->{
            categoryCombobox.addItem(temp.getName());
        });
        //Iterando os tipos de unidades
        for (UnitType uni : UnitType.values()){
            unityCombobox.addItem(uni);
        }

        JFrame cadProdutoFrame = new JFrame("Cadastro de Produto");
        cadProdutoFrame.setContentPane(cadastroProdutoMain);
        cadProdutoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cadProdutoFrame.pack();
        cadProdutoFrame.setLocationRelativeTo(null);
        cadProdutoFrame.setVisible(true);
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameText.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Necessário inserir nome do produto");
                }
                else{
                    System.out.println(listCategory.get(categoryCombobox.getSelectedIndex()));
                    UnitType unity = (UnitType) unityCombobox.getSelectedItem();
                    System.out.println(unity);
                    Product product = new Product(nameText.getText(),ceanText.getText(),marcaText.getText(),listCategory.get(categoryCombobox.getSelectedIndex()), unity);
                    int retorno = ProductDao.getNewInstance().modify(product);
                    if(retorno>0){
                        int confirm = JOptionPane.showConfirmDialog(cadProdutoFrame.getContentPane(),"Deseja cadastrar outro produto?","Confirmação",JOptionPane.YES_NO_OPTION);
                        System.out.println(confirm);
                        if(confirm == JOptionPane.YES_NO_OPTION){
                            cleanInputs();
                        }
                        else{
                            cadProdutoFrame.dispose();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Erro na inserção " + retorno);
                    }
                }
            }
        });
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanInputs();
            }
        });

        fecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadProdutoFrame.dispose();
            }
        });
    }

    private void cleanInputs() {
        nameText.setText("");
        ceanText.setText("");
        marcaText.setText("");
        nameText.requestFocus(true);
    }

    private List<Category> getCategories(){
        return CategoryDao.getNewInstance().getAllWithWhere("status>=0");
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
