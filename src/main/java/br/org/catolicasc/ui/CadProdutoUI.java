package br.org.catolicasc.ui;

import br.org.catolicasc.dao.CategoryDao;
import br.org.catolicasc.dao.ProductDao;
import br.org.catolicasc.model.Category;
import br.org.catolicasc.model.Product;
import br.org.catolicasc.model.UnitType;

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
    private JTextField custoText;
    private JFrame cadProdutoFrame;

    private Product prod;
    private boolean isEdit;
    private PesquisaProdutosUi PPui;

    public CadProdutoUI() {
        isEdit = false;
        prod = new Product();
        //populando lista de categorias
        List<Category> listCategory = getCategories();
        listCategory.forEach((temp) ->{
            categoryCombobox.addItem(temp.getName());
        });
        //Iterando os tipos de unidades
        for (UnitType uni : UnitType.values()){
            unityCombobox.addItem(uni);
        }

        cadProdutoFrame = new JFrame("Cadastro de Produto");
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
                    prod.setName(nameText.getText());
                    prod.setCean(ceanText.getText());
                    prod.setMarca(marcaText.getText());
                    prod.setCategoria(listCategory.get(categoryCombobox.getSelectedIndex()));
                    prod.setUnit(unity);
                    prod.setCostValue(Float.parseFloat(custoText.getText()));
                    int retorno = ProductDao.getNewInstance().modify(prod);
                    if(retorno>0){
                        if(!isEdit){
                            int confirm = JOptionPane.showConfirmDialog(cadProdutoFrame.getContentPane(),"Deseja cadastrar outro produto?","Confirmação",JOptionPane.YES_NO_OPTION);
                            System.out.println(confirm);
                            if(confirm == JOptionPane.YES_NO_OPTION){
                                cleanInputs();
                            }
                            else{
                                cadProdutoFrame.dispose();
                            }
                        }else{
                            cadProdutoFrame.dispose();
                            PPui.findProd();
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
        custoText.setText("");
        prod = new Product();
    }

    public void setProductToEdit(PesquisaProdutosUi ui, Product p){
        PPui = ui;
        isEdit = true;
        cleanInputs();
        if(p != null){
            prod = p;
            nameText.setText(prod.getName());
            ceanText.setText(prod.getCean());
            marcaText.setText(prod.getMarca());
            custoText.setText(String.valueOf(prod.getCostValue()));
            for (int i = 0; i < categoryCombobox.getItemCount(); i++) {
                if(categoryCombobox.getItemAt(i).equals(prod.getCategoria())){
                    categoryCombobox.setSelectedIndex(i);
                    break;
                }
            }

            for (int i = 0; i < unityCombobox.getItemCount(); i++) {
                if(unityCombobox.getItemAt(i).equals(prod.getUnit())){
                    unityCombobox.setSelectedIndex(i);
                    break;
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Produto não encontrado");
        }
    }

    private List<Category> getCategories(){
        return CategoryDao.getNewInstance().getAll();
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
