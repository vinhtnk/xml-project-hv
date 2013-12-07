/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DTO.EmployeeDTO;
import com.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoang
 */
public class EmployeeDAO {
    Connection con;
    PreparedStatement stm;
    String query = "";

    public boolean checkLogin(String name, String pwd) {
        con = ConnectDB.getCon();
        
        try {
            query = "Select * from employee where account=? and password=?";
            stm = con.prepareStatement(query);
            stm.setString(1, name);
            stm.setString(2, pwd);
            ResultSet rs = stm.executeQuery(query);
            
            if (rs.next()) {
                return true;
            }
            

        } catch (SQLException e) {
            return false;
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    

    public boolean addNewemployee(EmployeeDTO employee) {
        con = ConnectDB.getCon();
        try {
            query = "insert into employee (account, password) values (?,?)";
            stm = con.prepareStatement(query);
            stm.setString(1, employee.getAccount());
            stm.setString(2, employee.getPwd());
            int row = stm.executeUpdate();
            if(row>1){
                return true;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean updateemployee(EmployeeDTO employee){
        con = ConnectDB.getCon();
        try {
            query = "update employee set password = ? where account=?" ;

            stm = con.prepareStatement(query);
            stm.setString(1, employee.getPwd());
            stm.setString(2, employee.getAccount());
            int row = stm.executeUpdate();
            if(row>1){
                return true;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean deleteemployee(EmployeeDTO employee){
        con = ConnectDB.getCon();
        try {
            query = "delete from employee where account=?" ;

            stm = con.prepareStatement(query);
            stm.setString(1, employee.getAccount());
            int row = stm.executeUpdate();
            if(row>1){
                return true;
            }

        } catch (SQLException e) {
            return false;
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
