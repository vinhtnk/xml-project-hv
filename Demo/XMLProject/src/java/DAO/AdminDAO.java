/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.AdminDTO;
import Utils.DBUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quaikiet
 */
public class AdminDAO {

    private String sqlQuery;
    private Connection connection;
    private Statement statement;

    public AdminDAO() {
        connection = DBUtil.getDBConnection();
        sqlQuery = "";
    }

    public List<AdminDTO> findAll() {
        sqlQuery = "Select * From tblAdmins";
        List<AdminDTO> listAdmins = new ArrayList<AdminDTO>();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            statement.close();
            AdminDTO admin = null;
            while (rs.next()) {
                admin = new AdminDTO();
                admin.setUsername(rs.getString("UserName"));
                admin.setPassword(rs.getString("Password"));
                admin.setEmail(rs.getString("Email"));
                listAdmins.add(admin);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listAdmins;
    }

    public boolean isAdmin(String username, String password) {
        sqlQuery = "Select * from tblAdmins Where UserName= '" + username + "' AND Password = '" + password + "'";
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public AdminDTO getAdminInfo(String username) {
        sqlQuery = "Select * From tblAdmins Where username ='" + username + "'";
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                AdminDTO admin = new AdminDTO();
                admin.setFullName(rs.getString("FullName"));
                admin.setPassword(rs.getString("Password"));
                admin.setRole(rs.getString("Role"));
                admin.setEmail(rs.getString("Email"));
                return admin;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
