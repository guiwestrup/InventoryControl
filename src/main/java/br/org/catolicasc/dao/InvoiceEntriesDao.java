package br.org.catolicasc.dao;

import br.org.catolicasc.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceEntriesDao extends BaseDao<InvoiceEntries> {

    private InvoiceEntriesDao() {
        super(
                "invoice_products",
                new String[]{
                        "quantity",
                        "costValue",
                        "total",
                        "id_product",
                        "id_invoiceEntrie"
                },
                new String[]{
                        "INT",
                        "DECIMAL(10,2)",
                        "DECIMAL(10,2)",
                        "INT",
                        "INT"
                },
                "FOREIGN KEY (id_product) REFERENCES product(id)," +
                        "FOREIGN KEY (id_invoiceEntrie) REFERENCES invoice_entries(id)"
        );
    }

    public static InvoiceEntriesDao getNewInstance(){
        return new InvoiceEntriesDao();
    }

    @Override
    public InvoiceEntries getObjFromRs(ResultSet rs) throws SQLException {
        InvoiceEntries invoiceEntries = new InvoiceEntries();
        invoiceEntries.setId(rs.getInt("id"));
        invoiceEntries.setNumberInvoice(rs.getString("number_invoice"));
        invoiceEntries.setDescription(rs.getString("description"));
        invoiceEntries.setTotalValue(rs.getFloat("total_value"));
        invoiceEntries.setVendor(VendorDao.getNewInstance().getById(rs.getInt("id_vendor")));
        invoiceEntries.setUser(UserDao.getNewInstance().getById(rs.getInt("id_user")));

        invoiceEntries.setListProducts(InvoiceProductsDao.getNewInstance().getAllWithWhere("id_invoice_entries=" + invoiceEntries.getId()));

        return invoiceEntries;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, InvoiceEntries obj) throws SQLException {
        pstmt.setString(1,obj.getNumberInvoice());
        pstmt.setString(2,obj.getDescription());
        pstmt.setFloat(3,obj.getTotalValue());
        if(obj.getVendor() != null) pstmt.setInt(4,obj.getVendor().getId());
        if(obj.getUser() != null) pstmt.setInt(5,obj.getUser().getId());

        for (InvoiceProducts o: obj.getListProducts()) {
            InvoiceProductsDao.getNewInstance().modify(o);
        }
    }
}
