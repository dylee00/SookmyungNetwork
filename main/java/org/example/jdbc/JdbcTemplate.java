package org.example.jdbc;

import java.sql.*;

public class JdbcTemplate {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://sookmyung-network.ctejkrebksxz.us-east-1.rds.amazonaws.com:3306/network?serverTime=UTC&characterEncoding=UTF-8",
                    "dylee",
                    "20001122");
            conn.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(Connection conn) {
        try {
            if(conn != null && !conn.isClosed())
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt) {
        try {
            if(stmt != null && !stmt.isClosed())
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rset) {
        try {
            if(rset != null && !rset.isClosed())
                rset.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit(Connection conn) {
        try {
            if(conn != null & !conn.isClosed())
                conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection conn) {
        try {
            if(conn != null & !conn.isClosed())
                conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}



