package br.org.catolicasc.ui;

import br.org.catolicasc.dao.VendorDao;
import br.org.catolicasc.model.Vendor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VendorUi {
    private JTextField buscaText;
    private DefaultTableModel vendorsTableModel;
    private JTable vendorsTable;
    private JButton editarButton;
    private JButton voltarButton;
    private JButton novoButton;
    private JPanel mainPanel;

    public VendorUi() {
        JFrame vendorFrame = new JFrame("");
        vendorFrame.setContentPane(mainPanel);
        vendorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vendorFrame.pack();
        vendorFrame.setLocationRelativeTo(null);
        vendorFrame.setVisible(true);

        String[] colunas = {"Id","N Fantasia","Razão Social","Telefone","CNPJ","E-mail","Endereço","Cidade","CEP","Estado"};

        vendorsTableModel =  new DefaultTableModel(new Object[][]{}, colunas);

        vendorsTable.setModel(vendorsTableModel);

        findVendors();

        VendorUi that = this;

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = (int)vendorsTable.getValueAt(vendorsTable.getSelectedRow(), 0);
                if(id != 0){
                    RegisterVendorUI v = new RegisterVendorUI(that);
                    v.setVendorToEdit(VendorDao.getNewInstance().getById(id));
                }
            }
        });
    }

    private void findVendors() {
        List<Vendor> listV = VendorDao.getNewInstance().getAllWithWhere(" trade like '%" + buscaText.getText() + "%'");
        vendorsTableModel.setRowCount(0);
        for(Vendor v: listV){
            vendorsTableModel.addRow(
                new Object[]{
                    v.getId(),
                    v.getTrade(),
                    v.getCompany(),
                    v.getPhone(),
                    v.getCNPJ(),
                    v.getEmail(),
                    v.getAddress(),
                    v.getCity(),
                    v.getZipcode(),
                    v.getState()
                }
            );
        }
    }

    public static void main(String[] args) {
        new VendorUi();
    }
}


