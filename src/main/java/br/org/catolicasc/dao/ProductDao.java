package br.org.catolicasc.dao;

import br.org.catolicasc.model.Product;
import br.org.catolicasc.model.UnitType;
import br.org.catolicasc.model.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao extends BaseDao<Product> {

    protected ProductDao() {
        super(
                true,
                "product",
                new String[]{
                        "name",
                        "cean",
                        "ncmcode",
                        "aliquota",
                        "cfop",
                        "sittributaria",
                        "codcest",
                        "unit"
                },
                new String[]{
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "INT",
                        "INT",
                        "INT",
                        "ENUM(" + Utils.getStrings(UnitType.class) + ")",
                },
                "");
    }

    @Override
    public Product getObjFromRs(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setCean(rs.getString("cean"));
        product.setNcmcode(rs.getString("ncmcode"));
        product.setAliquota(rs.getString("aliquota"));
        product.setCFOP(rs.getInt("cfpo"));
        product.setSittributaria(rs.getInt("sittributaria"));
        product.setCodcest(rs.getInt("codcest"));
        product.setUnit(UnitType.valueOf(rs.getString("unit")));
        return product;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, Product obj) throws SQLException {
        pstmt.setString(1,obj.getName());
        pstmt.setString(2,obj.getCean());
        pstmt.setString(3,obj.getNcmcode());
        pstmt.setString(4,obj.getAliquota());
        pstmt.setInt(5,obj.getCFOP());
        pstmt.setInt(6,obj.getSittributaria());
        pstmt.setInt(7,obj.getCodcest());
        pstmt.setString(8,obj.getUnit().toString());
    }
}
