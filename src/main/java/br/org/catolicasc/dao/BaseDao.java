package br.org.catolicasc.dao;

import br.org.catolicasc.model.BaseDaoClass;
import br.org.catolicasc.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T>{
    protected Connection conn;
    protected String TABLE;

    protected String[] attributes;

    protected PreparedStatement selectTodos;
    protected PreparedStatement selectOne;
    protected PreparedStatement insert;
    protected PreparedStatement update;
    protected PreparedStatement delete;

    public BaseDao() {
        String stringInsert = "";
        String stringUpdate = "";

        try {
            conn  = ConnectionManager.getInstance().getConnection();

            selectTodos = conn.prepareStatement("SELECT * FROM " + TABLE);
            selectOne = conn.prepareStatement("SELECT * FROM " + TABLE + " WHERE id=?");
            delete = conn.prepareStatement("UPDATE " + TABLE + " SET status=" + Status.ACTIVE + " WHERE id=? ");

            for(String att: attributes){
                stringInsert += att + ",";
                stringUpdate += att + "=?,";
            }

            stringInsert.substring(0,stringInsert.length() - 1);
            stringUpdate.substring(0,stringUpdate.length() - 1);

            insert = conn.prepareStatement("INSERT INTO " + TABLE + " (" + stringInsert + ") VALUES (?,?)");
            update = conn.prepareStatement("UPDATE " + TABLE + " SET " + stringUpdate + " WHERE id=?");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public abstract T getObjFromRs(ResultSet rs);
    public abstract void setUpdateAttributes(T obj);
    public abstract void setInsertAttributes(T obj);

    public List<T> getAll() {
        List<T> resultado = null;
        ResultSet rs = null;

        try {
            rs = selectTodos.executeQuery();
            resultado = new ArrayList<>();

            while (rs.next()) {
                resultado.add(getObjFromRs(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                close();
            }
        }
        return resultado;
    }

    public T getById(int id){
        T resultado = null;
        ResultSet rs = null;

        try {
            selectOne.setInt(1, id);

            rs = selectOne.executeQuery();

            while (rs.next()) {
                resultado = getObjFromRs(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                close();
            }
        }
        return resultado;
    }

    public int insert(T obj) {
        int resultado = 0;

        try {
            setInsertAttributes(obj);

            // insere e retorna o numero de linhas atualizadas
            resultado = insert.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            close();
        }

        return resultado;
    }

    public int update(T obj) {
        int resultado = 0;

        try {
            setUpdateAttributes(obj);

            // retorna o numero de linhas atualizadas
            resultado = update.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            close();
        }

        return resultado;
    }

    public int deleteById(int id) {
        int resultado = 0;

        try {
            delete.setInt(1, id);
            // deleta e retorna o numero de linhas atualizadas
            resultado = delete.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            close();
        }

        return resultado;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
