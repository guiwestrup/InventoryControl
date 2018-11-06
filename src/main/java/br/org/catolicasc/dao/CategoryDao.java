package br.org.catolicasc.dao;

import br.org.catolicasc.model.Category;
import br.org.catolicasc.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoryDao{
    private CategoryDao(){
        try {
            conn  = ConnectionManager.getInstance().getConnection();

            insertNovo = conn.prepareStatement("INSERT INTO " + TABLE + " (name, status) VALUES (?,?)");
            update = conn.prepareStatement("UPDATE " + TABLE + " SET nome=?, cpf=? WHERE id=?");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static CategoryDao getNewInstance(){
        return new CategoryDao();
    }

    @Override
    public Category getObjFromRs() {
        return null;
    }
}
