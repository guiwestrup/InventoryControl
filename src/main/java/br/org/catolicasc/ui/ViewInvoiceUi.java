package br.org.catolicasc.ui;

import br.org.catolicasc.dao.InvoiceEntriesDao;
import br.org.catolicasc.model.InvoiceEntries;
import br.org.catolicasc.model.InvoiceProducts;
import br.org.catolicasc.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewInvoiceUi {
    private JTextField idNotaText;
    private JTextField nomeFornecedorText;
    private JTextField totalNotaText;
    private JTable produtosTable;
    private DefaultTableModel produtosTableModel;
    private JButton voltarButton;
    private JPanel mainPanel;

    public ViewInvoiceUi(int idInvoiceEntrie) {
        JFrame viewInvoiceFrame = new JFrame("Visualizar dados da Nota");
        viewInvoiceFrame.setContentPane(mainPanel);
        viewInvoiceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewInvoiceFrame.pack();
        viewInvoiceFrame.setLocationRelativeTo(null);
        viewInvoiceFrame.setVisible(true);

        InvoiceEntries invoiceEntries = InvoiceEntriesDao.getNewInstance().getById(idInvoiceEntrie);

        idNotaText.setText(String.valueOf(invoiceEntries.getId()));
        nomeFornecedorText.setText(invoiceEntries.getVendor().getTrade());
        totalNotaText.setText(String.valueOf(invoiceEntries.getTotalValue()));

        String [] colunas = {"Id","Nome","Quantidade","Valor de Custo"};

        produtosTableModel = new DefaultTableModel(new Object[][]{}, colunas);

        produtosTable.setModel(produtosTableModel);

        List<InvoiceProducts> listP = invoiceEntries.getListProducts();

        for(InvoiceProducts in: listP){
            produtosTableModel.addRow(
                    new Object[]{
                            in.getProduct().getId(),
                            in.getProduct().getName(),
                            in.getQuantity(),
                            String.valueOf(in.getProduct().getCostValue())
                    }
            );
        }



    }

    public static void main(String[] args) {
        new ViewInvoiceUi(2);
    }

}
