package br.org.catolicasc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static String DATABASE = "inventory-control";
    private static String JDBC_DRIVER = "org.mariadb.jdbc.Driver";;
    private static String URL = "jdbc:mariadb://localhost:3306/" + DATABASE + "?useSSL=false";
    private static String USER = "root";
    private static String PASSWORD = "root";

    private static Connection conn = null;
    private static ConnectionManager mngr;

    static {
        mngr = new ConnectionManager();
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
