/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com;

import java.sql.*;

/**
 *
 * @author Hoang
 */
public class ConnectDB {
    
    public static Connection getCon() {
        Connection con = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://localhost:1433; instanceName=HOANGDH60486/SQLEXPRESS2008;"
                + "databaseName=H2store";
        String uname = "sa";
        String pwd = "123456";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, uname, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    
}
