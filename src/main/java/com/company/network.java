package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class network {

    static HashMap<String, PreparedStatement> preparedStatements = new HashMap<String, PreparedStatement>();
    private Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/papa_djons?serverTimezone=UTC";
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
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

            //preparedStatements.put("entryKey", connection.prepareStatement("SELECT * FROM `hwid` WHERE `key`=?"));

            //preparedStatements.put("getName", connection.prepareStatement("SELECT * FROM `hwid` WHERE `key`=?"));

           // preparedStatements.put("getServer", connection.prepareStatement("SELECT * FROM `server` WHERE `name`=?"));

            //preparedStatements.put("serverList", connection.prepareStatement("SELECT * FROM `server` WHERE 1"));

            preparedStatements.put("passAuth", connection.prepareStatement("SELECT * FROM `Users` WHERE `login`=?"));
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

    public ArrayList<String> serverList() {
        PreparedStatement serverList = preparedStatements.get("serverList");
        ResultSet rs = null;
        ArrayList<String> list1 = null;
        ArrayList<String> list = new ArrayList<String>();
        try {


            rs = serverList.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                list.add(name);

            }
            return list;
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
        return list1;
    }


    public String getServer(String name, String type){
        PreparedStatement getServer = preparedStatements.get("getServer");
        ResultSet rs = null;
        try {

            getServer.setString(1, name);

            rs = getServer.executeQuery();
            while (rs.next()) {

                return rs.getString(type);

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
        return "";
    }



    public String getName(String key){
        PreparedStatement getName = preparedStatements.get("getName");
        ResultSet rs = null;
        try {

            getName.setString(1, key);

            rs = getName.executeQuery();
            while (rs.next()) {

                return rs.getString("name");

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
        return "";
    }


    public String entryKey(String key){
        PreparedStatement entryKey = preparedStatements.get("entryKey");
        ResultSet rs = null;
        try {

            entryKey.setString(1, key);

            rs = entryKey.executeQuery();
            while (rs.next()) {

                return rs.getString("type");

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
        return "1";
    }
}
