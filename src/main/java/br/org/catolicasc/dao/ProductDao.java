package br.org.catolicasc.dao;

import br.org.catolicasc.model.Product;
import br.org.catolicasc.model.UnitType;
import br.org.catolicasc.model.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ProductDao extends BaseDao<Product> {

    private ProductDao() {
        super(
                "product",
                new String[]{
                        "name",
                        "cean",
                        "unit",
                        "id_category",
                        "marca",
                        "cost_value"
                },
                new String[]{
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "ENUM(" + Utils.getStrings(UnitType.class) + ")",
                        "int",
                        "VARCHAR(255)",
                        "FLOAT"
                },
                "FOREIGN KEY (id_category) REFERENCES category(id)"
        );
    }

    public static ProductDao getNewInstance(){
        return new ProductDao();
    }

    @Override
    public Product getObjFromRs(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setCean(rs.getString("cean"));
        product.setCategoria(CategoryDao.getNewInstance().getById(rs.getInt("id_category")));
        product.setMarca(rs.getString("marca"));
        product.setCostValue(rs.getFloat("cost_value"));
        try {
            product.setUnit(UnitType.valueOf(rs.getString("unit")));
        }catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, Product obj) throws SQLException {
        pstmt.setString(1,obj.getName());
        pstmt.setString(2,obj.getCean());
        if (obj.getUnit() != null) pstmt.setString(3,obj.getUnit().toString());
        else pstmt.setNull(5,Types.VARCHAR);
        if (obj.getCategoria() != null) pstmt.setInt(4,obj.getCategoria().getId());
        else pstmt.setNull(5,Types.INTEGER);
        pstmt.setString(5,obj.getMarca());
        pstmt.setFloat(6,obj.getCostValue());

    }
}
