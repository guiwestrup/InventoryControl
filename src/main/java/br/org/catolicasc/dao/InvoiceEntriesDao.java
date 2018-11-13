package br.org.catolicasc.dao;

import br.org.catolicasc.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceEntriesDao extends BaseDao<InvoiceEntries> {

    private InvoiceEntriesDao() {
        super(
                true,
                "invoice_entries",
                new String[]{
                        "number_invoice",
                        "total_value",
                        "id_vendor",
                        "id_user",
                        "status"
                },
                new String[]{
                        "VARCHAR(255)",
                        "DECIMAL(10,2)",
                        "INT",
                        "INT",
                        "ENUM(" + Utils.getStrings(Status.class) + ")",
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
        invoiceEntries.setTotalValue(rs.getFloat("total_value"));
        invoiceEntries.setVendor(VendorDao.getNewInstance().getById(rs.getInt("id_vendor")));
        invoiceEntries.setUser(UserDao.getNewInstance().getById(rs.getInt("id_user")));
        invoiceEntries.setStatus(Status.valueOf(rs.getString("status")));
        return invoiceEntries;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, InvoiceEntries obj) throws SQLException {
        pstmt.setString(1,obj.getNumberInvoice());
        pstmt.setFloat(2,obj.getTotalValue());
        if(obj.getVendor() != null) pstmt.setInt(3,obj.getVendor().getId());
        if(obj.getUser() != null) pstmt.setInt(4,obj.getUser().getId());
        pstmt.setString(5,obj.getStatus().toString());
    }
}
