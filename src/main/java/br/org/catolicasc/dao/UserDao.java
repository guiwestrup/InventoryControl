package br.org.catolicasc.dao;

import br.org.catolicasc.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends BaseDao<User> {

    private UserDao() {
        super(
                false,
                "user",
                new String[]{
                        "name",
                        "password",
                        "address",
                        "city",
                        "state",
                        "role",
                        "status"
                },
                new String[]{
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "VARCHAR(255)",
                        "ENUM(" + Utils.getStrings(State.class) + ")",
                        "ENUM(" + Utils.getStrings(Role.class) + ")",
                        "ENUM(" + Utils.getStrings(Status.class) + ")",
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
        user.setStatus(Status.valueOf(rs.getString("status")));
        return user;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, User obj) throws SQLException {
        pstmt.setString(1, obj.getName());
        //TODO revisar esse set Password
        pstmt.setString(2, "teste senha");
        pstmt.setString(3, obj.getAddress());
        pstmt.setString(4, obj.getCity());
        pstmt.setString(5, obj.getState().toString());
        pstmt.setString(6, obj.getRole().toString());
        pstmt.setString(7, obj.getStatus().toString());
    }
}
