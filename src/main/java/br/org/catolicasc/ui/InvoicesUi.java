package br.org.catolicasc.ui;

import br.org.catolicasc.dao.InvoiceEntriesDao;
import br.org.catolicasc.dao.VendorDao;
import br.org.catolicasc.model.InvoiceEntries;
import br.org.catolicasc.model.Vendor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static br.org.catolicasc.dao.InvoiceEntriesDao.*;

public class InvoicesUi {
    private JTextField buscaText;
    private JButton voltarButton;
    private JButton verButton;
    private JPanel mainPanel;
    private JTable invoicesTable;
    private DefaultTableModel invoicesTableModel;
    public InvoicesUi() {
        JFrame invoicesFrame = new JFrame("Notas");
        invoicesFrame.setContentPane(mainPanel);
        invoicesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        invoicesFrame.pack();
        invoicesFrame.setLocationRelativeTo(null);
        invoicesFrame.setVisible(true);

        String[] colunasInvoices = {"Id","Total","Fornecedor"};

        invoicesTableModel = new DefaultTableModel(new Object[][]{}, colunasInvoices);

        invoicesTable.setModel(invoicesTableModel);
        findInvoices();




        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invoicesFrame.dispose();
            }
        });
        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewInvoiceUi(Integer.parseInt(invoicesTableModel.getValueAt(invoicesTable.getSelectedRow(),0).toString()));
            }
        });
    }

    private void findInvoices(){
        List<InvoiceEntries> listI = getNewInstance().getAllWithWhere(" id like '%" + buscaText.getText() + "%'");
        invoicesTableModel.setRowCount(0);
        for(InvoiceEntries e: listI){
            invoicesTableModel.addRow(
                    new Object[]{
                            e.getId(),
                            e.getTotalValue(),
                            e.getVendor().getTrade()
                    }
            );
        }
    }
}
