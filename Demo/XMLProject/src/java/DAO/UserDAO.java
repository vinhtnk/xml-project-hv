/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.UserDTO;
import Utils.DBUtil;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quaikiet
 */
public class UserDAO {

    private Connection connection;
    private Statement statement;
    String sqlQuery;

    public UserDAO() {
        try {
            this.connection = DBUtil.getDBConnection();
            statement = connection.createStatement();
            sqlQuery = "";
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isExistedUser(UserDTO user) {
        if (user == null) {
            return true;
        }

        try {
            sqlQuery = "Select * From tblUsers Where username = '" + user.getUsername() + "' AND email ='" + user.getEmail() + "'";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                rs.close();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insert(UserDTO user) {
        sqlQuery = "Insert Into tblUsers VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "','"
                + user.getRole() + "','" + user.getPhone() + "','" + user.getFullName() + "', '" + user.getEmail() + "')";
        try {
            statement = connection.createStatement();
            int isInserted = statement.executeUpdate(sqlQuery);
            if (isInserted > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<UserDTO> findAll() {
        sqlQuery = "Select * From tblUsers";
        List<UserDTO> listUsers = new ArrayList<UserDTO>();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            UserDTO user = null;
            while (rs.next()) {
                user = new UserDTO();
                user.setUsername(rs.getString("UserName"));
                user.setFullName(rs.getString("FullName"));
                user.setRole(rs.getString("Role"));
                user.setPassword(rs.getString("Password"));
                user.setUserId(rs.getInt("UserId"));
                user.setEmail(rs.getString("Email"));
                listUsers.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUsers;
    }

    public UserDTO getUserInfo(String username) {
        sqlQuery = "Select * From tblUsers Where username ='" + username + "'";
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUserId(rs.getInt("UserId"));
                user.setFullName(rs.getString("FullName"));
                user.setPassword(rs.getString("Password"));
                user.setRole(rs.getString("Role"));
                user.setEmail(rs.getString("Email"));
                user.setPhone(rs.getString("Phone"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isUser(String username, String password) {
        sqlQuery = "Select * from tblUsers Where UserName= '" + username + "' AND Password = '" + password + "'";
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                //return fullName;
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
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
