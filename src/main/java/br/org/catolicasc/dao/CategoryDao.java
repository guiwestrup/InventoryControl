package br.org.catolicasc.dao;

import br.org.catolicasc.model.Category;
import br.org.catolicasc.model.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDao extends BaseDao<Category>{

    private CategoryDao(){
        super(
                "category",
                new String[]{
                        "name"
                },
                new String[]{
                        "VARCHAR(255)"
                },
                ""
        );
    }

    public static CategoryDao getNewInstance(){
        return new CategoryDao();
    }

    @Override
    public Category getObjFromRs(ResultSet rs) throws SQLException {
        Category obj  = new Category();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("name"));
        return obj;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, Category obj) throws SQLException {
        pstmt.setString(1, obj.getName());
    }
}
