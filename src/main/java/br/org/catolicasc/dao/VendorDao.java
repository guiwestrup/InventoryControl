package br.org.catolicasc.dao;

import br.org.catolicasc.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendorDao extends BaseDao<Vendor>{

    private VendorDao() {
        super(
                false,
                "vendor",
                new String[]{
                        "company",
                        "trade",
                        "cnpj",
                        "address",
                        "phone",
                        "email",
                        "zipcode",
                        "city",
                        "state",
                        "id_category",
                        "status"
                },
                new String[]{
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "ENUM(" + Utils.getStrings(State.class) + ")",
                        "INT",
                        "ENUM(" + Utils.getStrings(Status.class) + ")",
                },
                "FOREIGN KEY (id_category) REFERENCES category(id)"
        );
    }

    public static VendorDao getNewInstance(){
        return new VendorDao();
    }

    @Override
    public Vendor getObjFromRs(ResultSet rs) throws SQLException {
        Vendor vendor = new Vendor();
        vendor.setCompany(rs.getString("company"));
        vendor.setTrade(rs.getString("trade"));
        vendor.setCNPJ(rs.getString("cnpj"));
        vendor.setAddress(rs.getString("address"));
        vendor.setPhone(rs.getString("phone"));
        vendor.setEmail(rs.getString("email"));
        vendor.setZipcode(rs.getString("zipcode"));
        vendor.setCity(rs.getString("city"));
        vendor.setState(State.valueOf(rs.getString("state")));
        vendor.setCategory(CategoryDao.getNewInstance().getById(rs.getInt("id_category")));
        vendor.setStatus(Status.valueOf(rs.getString("status")));
        return vendor;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, Vendor obj) throws SQLException {
        pstmt.setString(1,obj.getCompany());
        pstmt.setString(2,obj.getTrade());
        pstmt.setString(3,obj.getCNPJ());
        pstmt.setString(4,obj.getAddress());
        pstmt.setString(5,obj.getPhone());
        pstmt.setString(6,obj.getEmail());
        pstmt.setString(7,obj.getZipcode());
        pstmt.setString(8,obj.getCity());
        pstmt.setString(9,obj.getState().toString());
        if(obj.getCategory() != null) pstmt.setInt(10, obj.getCategory().getId());
        pstmt.setString(11,obj.getStatus().toString());
    }
}
