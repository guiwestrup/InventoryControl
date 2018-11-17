package br.org.catolicasc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static boolean MARIADB = false;

    private static String DATABASE;
    private static String JDBC_DRIVER;
    private static String URL;
    private static String USER ;
    private static String PASSWORD;

    private static Connection conn = null;
    private static ConnectionManager mngr;

    static {
        mngr = new ConnectionManager();

        DATABASE = "inventory-control";

        if(MARIADB){
            JDBC_DRIVER = "org.mariadb.jdbc.Driver";
            URL = "jdbc:mariadb://localhost:3306/" + DATABASE + "?useSSL=false";
            USER = "root";
            PASSWORD = "root";
        }else{
            JDBC_DRIVER = "com.mysql.jdbc.Driver";
            URL = "jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false";
            USER = "root";
            PASSWORD = "";
        }
    }

    public static ConnectionManager getInstance() {
        return mngr;
    }

    private ConnectionManager() {
    }

    public Connection getConnection() throws SQLException {

        if (conn == null || conn.isClosed()) {
            try {
                Class.forName(JDBC_DRIVER).newInstance();
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                conn.setAutoCommit(true);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                ex.printStackTrace();
            }
        }

        return conn;
    }
}
