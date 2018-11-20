package br.org.catolicasc.dao;

import br.org.catolicasc.model.Product;
import br.org.catolicasc.model.UnitType;
import br.org.catolicasc.model.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao extends BaseDao<Product> {

    private ProductDao() {
        super(
                "product",
                new String[]{
                        "name",
                        "cean",
                        "unit",
                        "id_category",
                        "marca"
                },
                new String[]{
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "ENUM(" + Utils.getStrings(UnitType.class) + ")",
                        "int",
                        "VARCHAR(255)"
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
        product.setUnit(UnitType.valueOf(rs.getString("unit")));
        product.setCategoria(CategoryDao.getNewInstance().getById(rs.getInt("id_category")));
        product.setMarca(rs.getString("marca"));
        return product;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, Product obj) throws SQLException {
        pstmt.setString(1,obj.getName());
        pstmt.setString(2,obj.getCean());
        pstmt.setString(3,obj.getUnit().toString());
        pstmt.setInt(4,obj.getCategoria().getId());
        pstmt.setString(5,obj.getMarca());

    }
}
