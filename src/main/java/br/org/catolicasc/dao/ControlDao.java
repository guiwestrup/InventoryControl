package br.org.catolicasc.dao;

import br.org.catolicasc.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ControlDao extends BaseDao<Control>{
    private String description;
    private int quatity;
    private float value;
    //TODO definir qual date
    private Date date;
    private InvoiceEntries invoiceEntries;
    private Product product;
    private User user;
    private Type type;
    private InsertWithdraw insertwithdraw;
    private Status status;

    private ControlDao() {
        super(
                true,
                "control",
                new String[]{
                        "description",
                        "quantity",
                        "value",
                        "date",
                        "id_invoice_entries",
                        "id_product",
                        "id_user",
                        "type",
                        "insertwithdraw",
                        "status"
                },
                new String[]{
                        "VARCHAR(255)",
                        "INT",
                        //TODO verificar o tamanho do float
                        "DECIMAL(15,6)",
                        "DATE",
                        "INT",
                        "INT",
                        "INT",
                        "ENUM(" + Utils.getStrings(Type.class) + ")",
                        "ENUM(" + Utils.getStrings(InsertWithdraw.class) + ")",
                        "ENUM(" + Utils.getStrings(Status.class) + ")"
                },
                ""
        );
    }

    public static ControlDao getNewInstance(){
        return new ControlDao();
    }

    @Override
    public Control getObjFromRs(ResultSet rs) throws SQLException {
        Control control = new Control();
        control.setId(rs.getInt("id"));
        control.setDescription(rs.getString("description"));
        control.setQuatity(rs.getInt("quantity"));
        control.setValue(rs.getFloat("value"));
        control.setDate(rs.getDate("date"));
        control.setInvoiceEntries(InvoiceEntriesDao.getNewInstance().getById(rs.getInt("id_invoice_entries")));
        control.setProduct(ProductDao.getNewInstance().getById(rs.getInt("id_product")));
        control.setUser(UserDao.getNewInstance().getById(rs.getInt("id_user")));
        control.setType(Type.valueOf(rs.getString("type")));
        control.setInsertwithdraw(InsertWithdraw.valueOf(rs.getString("insertwithdraw")));
        control.setStatus(Status.valueOf(rs.getString("status")));
        return control;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, Control obj) throws SQLException {
        pstmt.setString(1, obj.getDescription());
        pstmt.setInt(2, obj.getQuatity());
        pstmt.setFloat(3, obj.getValue());
        //TODO tem que verificar se isso funciona (sql.Date.valueOf)
        pstmt.setDate(4, java.sql.Date.valueOf(obj.getDate().toString()));
        pstmt.setInt(5, obj.getInvoiceEntries().getId());
        pstmt.setInt(6, obj.getProduct().getId());
        pstmt.setInt(7, obj.getUser().getId());
        pstmt.setString(8, obj.getType().toString());
        pstmt.setString(9, obj.getInsertwithdraw().toString());
        pstmt.setString(10, obj.getStatus().toString());
    }
}
