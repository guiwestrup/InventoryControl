package br.org.catolicasc.dao;

import br.org.catolicasc.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class InventoryDao extends BaseDao<Inventory>{

    protected InventoryDao() {
        super(
                "inventory",
                new String[]{
                        "quantity",
                        "id_product",
                        "insertWithDraw"
                },
                new String[]{
                        "INT",
                        "INT",
                        "ENUM("+Utils.getStrings(InsertWithdraw.class)+")"
                },
                "FOREIGN KEY (id_product) REFERENCES product(id)"
        );
    }

    public static InventoryDao getNewInstance(){
        return new InventoryDao();
    }

    @Override
    public Inventory getObjFromRs(ResultSet rs) throws SQLException {
        Inventory inventory = new Inventory();
        inventory.setId(rs.getInt("id"));
        inventory.setQuantity(rs.getInt("quantity"));
        inventory.setProduct(ProductDao.getNewInstance().getById(rs.getInt("id_product")));
        try {
            inventory.setInsertWithdraw(InsertWithdraw.valueOf(rs.getString("insertWithDraw")));
        }catch (Exception e){
            e.printStackTrace();
        }
        return inventory;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, Inventory obj) throws SQLException {
        pstmt.setInt(1, obj.getQuantity());
        if(obj.getProduct() != null) pstmt.setInt(2, obj.getProduct().getId());
        else pstmt.setNull(2,Types.INTEGER);
        if (obj.getInsertWithdraw() != null) pstmt.setString(3, obj.getInsertWithdraw().toString());
        else pstmt.setNull(3,Types.VARCHAR);
    }
}
