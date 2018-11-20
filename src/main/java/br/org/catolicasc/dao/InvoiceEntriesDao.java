package br.org.catolicasc.dao;

import br.org.catolicasc.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceEntriesDao extends BaseDao<InvoiceEntries> {

    private InvoiceEntriesDao() {
        super(
                "invoice_entries",
                new String[]{
                        "number_invoice",
                        "description",
                        "total_value",
                        "id_vendor",
                        "id_user"
                },
                new String[]{
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "DECIMAL(10,2)",
                        "INT",
                        "INT"
                },
                "FOREIGN KEY (id_vendor) REFERENCES vendor(id)," +
                        "FOREIGN KEY (id_user) REFERENCES user(id)"
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
