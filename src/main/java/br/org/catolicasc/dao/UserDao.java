package br.org.catolicasc.dao;

import br.org.catolicasc.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends BaseDao<User> {

    private UserDao() {
        super(
                "user",
                new String[]{
                        "name",
                        "password",
                        "address",
                        "city",
                        "state",
                        "role"
                },
                new String[]{
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "ENUM(" + Utils.getStrings(State.class) + ")",
                        "ENUM(" + Utils.getStrings(Role.class) + ")"
                },
                ""
        );
    }

    public static UserDao getNewInstance(){
        return new UserDao();
    }

    @Override
    public User getObjFromRs(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setAddress(rs.getString("address"));
        user.setCity(rs.getString("city"));
        user.setState(State.valueOf(rs.getString("state")));
        user.setRole(Role.valueOf(rs.getString("role")));
        return user;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, User obj) throws SQLException {
        pstmt.setString(1, obj.getName());
        pstmt.setString(2, obj.getPassword());
        pstmt.setString(3, obj.getAddress());
        pstmt.setString(4, obj.getCity());
        pstmt.setString(5, obj.getState().toString());
        pstmt.setString(6, obj.getRole().toString());
    }
}
