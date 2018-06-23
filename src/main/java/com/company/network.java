package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class network {

    static HashMap<String, PreparedStatement> preparedStatements = new HashMap<String, PreparedStatement>();
    private Connection connection;
    private static final String url = "jdbc:sqlite:users.db";
    private static final String user = "root";
    private static final String password = "";


    public void openConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url, user, password);

            preparedStatements.put("passAuth", connection.prepareStatement("SELECT * FROM `user` WHERE `login`=?"));
        }
    }


    public String passAuth(String name){
        PreparedStatement passAuth = preparedStatements.get("passAuth");
        ResultSet rs = null;
        try {

            passAuth.setString(1, name);

            rs = passAuth.executeQuery();
            while (rs.next()) {

                return rs.getString("password");

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }
}
