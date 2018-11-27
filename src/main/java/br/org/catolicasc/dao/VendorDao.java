package br.org.catolicasc.dao;

import br.org.catolicasc.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class VendorDao extends BaseDao<Vendor>{

    private VendorDao() {
        super(
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
                        "state"
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
                        "ENUM(" + Utils.getStrings(State.class) + ")"
                },
                ""
        );
    }

    public static VendorDao getNewInstance(){
        return new VendorDao();
    }

    @Override
    public Vendor getObjFromRs(ResultSet rs) throws SQLException {
        Vendor vendor = new Vendor();
        vendor.setId(rs.getInt("id"));
        vendor.setCompany(rs.getString("company"));
        vendor.setTrade(rs.getString("trade"));
        vendor.setCNPJ(rs.getString("cnpj"));
        vendor.setAddress(rs.getString("address"));
        vendor.setPhone(rs.getString("phone"));
        vendor.setEmail(rs.getString("email"));
        vendor.setZipcode(rs.getString("zipcode"));
        vendor.setCity(rs.getString("city"));
        try{
            vendor.setState(State.valueOf(rs.getString("state")));
        }catch (Exception e){
            e.printStackTrace();
        }
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
        if(obj.getState() != null) pstmt.setString(9,obj.getState().toString());
        else pstmt.setNull(9,Types.VARCHAR);
    }
}
