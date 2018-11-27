package br.org.catolicasc.dao;

import br.org.catolicasc.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class InvoiceProductsDao extends BaseDao<InvoiceProducts>{

    private InvoiceProductsDao() {
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
        invoiceProduct.setProduct(ProductDao.getNewInstance().getById(rs.getInt("id_product")));
        invoiceProduct.setIdInvoiceEntrie(rs.getInt("id_invoiceEntrie"));
        return invoiceProduct;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, InvoiceProducts obj) throws SQLException {
        pstmt.setInt(1, obj.getQuantity());
        pstmt.setFloat(2, obj.getCostValue());
        pstmt.setFloat(3, obj.getTotal());
        if(obj.getProduct() != null) pstmt.setInt(4, obj.getProduct().getId());
        else pstmt.setNull(4,Types.INTEGER);
        pstmt.setInt(5, obj.getIdInvoiceEntrie());
    }
}
