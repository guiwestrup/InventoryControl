package br.org.catolicasc.dao;

import br.org.catolicasc.model.Company;
import br.org.catolicasc.model.State;
import br.org.catolicasc.model.Status;
import br.org.catolicasc.model.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyDao extends BaseDao<Company> {
    protected CompanyDao() {
        super(
                true,
                "company",
                new String[]{
                        "name",
                        "cnpj",
                        "address",
                        "neighborhood",
                        "city",
                        "state",
                        "email",
                        "status"
                },
                new String[]{
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "ENUM(" + Utils.getStrings(State.class) + ")",
                        "VARCHAR(255)",
                        "ENUM(" + Utils.getStrings(Status.class) + ") DEFAULT \"" + Status.ACTIVE + "\""
                },
                "");
    }

    @Override
    public Company getObjFromRs(ResultSet rs) throws SQLException {
        Company company = new Company();
        company.setId(rs.getInt("id"));
        company.setName(rs.getString("name"));
        company.setCNPJ(rs.getString("cnpj"));
        company.setAddress(rs.getString("address"));
        company.setNeighborhood(rs.getString("neighborhood"));
        company.setCity(rs.getString("city"));
        company.setState(State.valueOf(rs.getString("state")));
        company.setEmail(rs.getString("email"));
        company.setStatus(Status.valueOf(rs.getString("status")));
        return company;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, Company obj) throws SQLException {
        pstmt.setString(1, obj.getName());
        pstmt.setString(2, obj.getCNPJ());
        pstmt.setString(3, obj.getAddress());
        pstmt.setString(4, obj.getNeighborhood());
        pstmt.setString(5, obj.getCity());
        pstmt.setString(6, obj.getState().toString());
        pstmt.setString(7, obj.getEmail());
        pstmt.setString(8, obj.getStatus().toString());
    }
}
