package br.org.catolicasc.dao;

import br.org.catolicasc.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceProductsDao extends BaseDao<InvoiceProducts>{

    private InvoiceProductsDao() {
        super(
                true,
                "invoice_products",
                new String[]{
                        "quantity",
                        "costValue",
                        "total",
                        "id_invoice_entries",
                        "id_product",
                        "status"
                },
                new String[]{
                        "INT",
                        "DECIMAL(10,2)",
                        "DECIMAL(10,2)",
                        "INT",
                        "INT",
                        "ENUM(" + Utils.getStrings(Status.class) + ")"
                },
                "FOREIGN KEY (id_invoice_entries) REFERENCES invoice_entries(id)," +
                        "FOREIGN KEY (id_product) REFERENCES product(id)"
        );
    }

    public static InvoiceProductsDao getNewInstance(){
        return new InvoiceProductsDao();
    }

    @Override
    public InvoiceProducts getObjFromRs(ResultSet rs) throws SQLException {
        InvoiceProducts invoiceProduct = new InvoiceProducts();
        invoiceProduct.setId(rs.getInt("id"));
        invoiceProduct.setQuantity(rs.getInt("quantity"));
        invoiceProduct.setCostValue(rs.getFloat("costValue"));
        invoiceProduct.setTotal(rs.getFloat("total"));
        invoiceProduct.setInvoiceEntries(InvoiceEntriesDao.getNewInstance().getById(rs.getInt("id_invoice_entries")));
        invoiceProduct.setProduct(ProductDao.getNewInstance().getById(rs.getInt("id_product")));
        invoiceProduct.setStatus(Status.valueOf(rs.getString("status")));
        return invoiceProduct;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, InvoiceProducts obj) throws SQLException {
        pstmt.setInt(1, obj.getQuantity());
        pstmt.setFloat(2, obj.getCostValue());
        pstmt.setFloat(3, obj.getTotal());
        pstmt.setInt(4, obj.getInvoiceEntries().getId());
        pstmt.setInt(5, obj.getProduct().getId());
        pstmt.setString(6, obj.getStatus().toString());
    }
}
