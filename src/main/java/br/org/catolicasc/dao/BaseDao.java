package br.org.catolicasc.dao;

import br.org.catolicasc.model.BaseDaoClass;
import br.org.catolicasc.model.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T extends BaseDaoClass>{
    protected Connection conn;
    protected String TABLE;

    //  set this variables in child class {
    //  todos os atributos da classe, alem do ID
    private String[] attributes;
    private String[] attributesType;
    private String createAdditional;
    //  }

    private PreparedStatement selectTodos;
    private PreparedStatement selectOne;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;

    protected BaseDao(String TableName, String[] attributes, String[] attributesType, String createAdditional) {
        this.TABLE = TableName;
        this.attributes = attributes;
        this.attributesType = attributesType;
        this.createAdditional = createAdditional;

        String stringInsert = "";
        String stringInsertValues = "";
        String stringUpdate = "";

        try {
            conn  = ConnectionManager.getInstance().getConnection();

            selectTodos = conn.prepareStatement("SELECT * FROM " + TABLE);
            selectOne = conn.prepareStatement("SELECT * FROM " + TABLE + " WHERE id=?");
            //delete = conn.prepareStatement("UPDATE " + TABLE + " SET status=\"" + Status.ACTIVE + "\" WHERE id=?");
            delete = conn.prepareStatement("DELETE FROM " + TABLE + " WHERE id=?");

            for(String att: attributes){
                stringInsert += att + ",";
                stringInsertValues += "?,";
                stringUpdate += att + "=?,";
            }

            stringInsert = stringInsert.substring(0,stringInsert.length() - 1);
            stringInsertValues = stringInsertValues.substring(0,stringInsertValues.length() - 1);
            stringUpdate = stringUpdate.substring(0,stringUpdate.length() - 1);

            insert = conn.prepareStatement("INSERT INTO " + TABLE + " (" + stringInsert + ") VALUES (" + stringInsertValues + ")", Statement.RETURN_GENERATED_KEYS);
            update = conn.prepareStatement("UPDATE " + TABLE + " SET " + stringUpdate + " WHERE id=?");

            createTable(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void createTable(Boolean dropTable) throws Exception {
        if(attributes.length != attributesType.length){
            throw new Exception("Attributes and Types is different");
        }

        Statement stmt = conn.createStatement();

        if(dropTable){
            String SqlDrop = "DROP TABLE IF EXISTS " + TABLE;
            stmt.execute(SqlDrop);
        }

        if(createAdditional.length() > 0 && !createAdditional.substring(0,1).equals(",")){
            createAdditional = "," + createAdditional;
        }

        String attWithTypesToCreateTable = "";

        for (int i = 0; i < attributes.length; i++) {
            attWithTypesToCreateTable += attributes[i] + " " + attributesType[i] + ",";
        }

        String sqlCreate = "CREATE TABLE IF NOT EXISTS " + TABLE
                + " (id INT NOT NULL AUTO_INCREMENT,"
                + attWithTypesToCreateTable
                + "PRIMARY KEY (id)"
                + createAdditional
                + ")";

        stmt.execute(sqlCreate);
    }

    public abstract T getObjFromRs(ResultSet rs) throws SQLException;
    public abstract void setAttributesFromObj(PreparedStatement pstmt, T obj) throws SQLException;

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

    public List<T> getAllWithWhere(String whereParams) {
        List<T> resultado = null;
        ResultSet rs = null;

        try {
            rs = conn.prepareStatement("SELECT * FROM " + TABLE + " WHERE " + whereParams).executeQuery();
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

    public int modify(T obj) {
        int resultado = 0;

        try {
            if(getById(obj.getId()) != null){
                update.setLong(attributes.length + 1, obj.getId());
                resultado = update.executeUpdate();
            }else{
                setAttributesFromObj(insert, obj);
                resultado = insert.executeUpdate();

                ResultSet generatedKeys = insert.getGeneratedKeys();

                if (generatedKeys.next()) {
                    obj.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating client failed, no ID obtained.");
                }
            }
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
