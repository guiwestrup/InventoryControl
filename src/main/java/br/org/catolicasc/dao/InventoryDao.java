package br.org.catolicasc.dao;

import br.org.catolicasc.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDao extends BaseDao<Inventory>{

    protected InventoryDao() {
        super(
                true,
                "inventory",
                new String[]{
                        "quantity",
                        "id_product",
                        "insertWithDraw",
                        "status"
                },
                new String[]{
                        "INT",
                        "INT",
                        "ENUM("+Utils.getStrings(InsertWithdraw.class)+")",
                        "ENUM("+Utils.getStrings(Status.class)+")"
                },
                "FOREIGN KEY (id_product) REFERENCES product(id)"
        );
    }

    @Override
    public Inventory getObjFromRs(ResultSet rs) throws SQLException {
        Inventory inventory = new Inventory();
        inventory.setId(rs.getInt("id"));
        inventory.setQuantity(rs.getInt("quantity"));
        inventory.setProduct(ProductDao.getNewInstance().getById(rs.getInt("id_product")));
        inventory.setInsertWithdraw(InsertWithdraw.valueOf(rs.getString("insertWithDraw")));
        inventory.setStatus(Status.valueOf(rs.getString("status")));
        return inventory;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, Inventory obj) throws SQLException {
        pstmt.setInt(1, obj.getQuantity());
        if(obj.getProduct() != null) pstmt.setInt(2, obj.getQuantity());
        pstmt.setString(3, obj.getInsertWithdraw().toString());
        pstmt.setString(3, obj.getStatus().toString());
    }
}
