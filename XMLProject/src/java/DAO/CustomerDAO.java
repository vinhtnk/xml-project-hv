/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.CustomerDTO;
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
public class CustomerDAO {

    Connection con;
    PreparedStatement stm;
    String query = "";

     public boolean checkLogin(String email, String pwd) {
        con = ConnectDB.getCon();

        try {
            query = "Select * from custommer where customeremail=? and password=?";
            stm = con.prepareStatement(query);
            stm.setString(1, email);
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

    public List<CustomerDTO> getAllcustomer() {
        con = ConnectDB.getCon();
        List<CustomerDTO> listCustomer = new ArrayList<CustomerDTO>();
        try {
            query = "Select * from customer";
            stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery(query);
            CustomerDTO customer = null;
            while (rs.next()) {
                customer = new CustomerDTO();
                customer.setCustomerID(rs.getInt(0));
                customer.setCustomerName(rs.getString(1));
                customer.setGender(rs.getString(2));
                customer.setPhone(rs.getString(3));
                customer.setAddress(rs.getString(4));
                customer.setEmail(rs.getString(5));
                customer.setPwd(rs.getString(6));
                customer.setQuestion(rs.getString(7));
                customer.setAnswer(rs.getString(8));
                listCustomer.add(customer);
            }
            return listCustomer;

        } catch (SQLException e) {
            return null;
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<CustomerDTO> searchcustomerByName(String key) {
        con = ConnectDB.getCon();
        List<CustomerDTO> listCustomer = new ArrayList<CustomerDTO>();
        try {
            query = "Select * from customer where customerName like ?";
            stm = con.prepareStatement(query);
            stm.setString(1, "%" + key + "%");
            ResultSet rs = stm.executeQuery(query);
            CustomerDTO customer = null;
            while (rs.next()) {
                customer = new CustomerDTO();
                customer.setCustomerID(rs.getInt(0));
                customer.setCustomerName(rs.getString(1));
                customer.setGender(rs.getString(2));
                customer.setPhone(rs.getString(3));
                customer.setAddress(rs.getString(4));
                customer.setEmail(rs.getString(5));
                customer.setPwd(rs.getString(6));
                customer.setQuestion(rs.getString(7));
                customer.setAnswer(rs.getString(8));
                listCustomer.add(customer);
            }
            return listCustomer;

        } catch (SQLException e) {
            return null;
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean addNewcustomer(CustomerDTO customer) {
        con = ConnectDB.getCon();
        try {
            query = "insert into customer (customerName,Gender,Phone,Address,CustomerEmail,Password, "
                    + "Question,Answer)values (?,?,?,?,?,?,?,?)";
            stm = con.prepareStatement(query);
            stm.setString(1, customer.getCustomerName());
            stm.setString(2, customer.getGender());
            stm.setString(3, customer.getPhone());
            stm.setString(4, customer.getAddress());
            stm.setString(5, customer.getEmail());
            stm.setString(6, customer.getPwd());
            stm.setString(7, customer.getQuestion());
            stm.setString(8, customer.getAnswer());

            int row = stm.executeUpdate();
            if (row > 1) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean updatecustomer(CustomerDTO customer) {
        con = ConnectDB.getCon();
        try {
            query = "update customer set customerName = ?, Gender =?,Phone=?,Address=?,"
                    + "CustomerEmail=?,Password=?Question=?,Answer=? where customerid=?";

            stm = con.prepareStatement(query);
            stm.setString(1, customer.getCustomerName());
            stm.setString(2, customer.getGender());
            stm.setString(3, customer.getPhone());
            stm.setString(4, customer.getAddress());
            stm.setString(5, customer.getEmail());
            stm.setString(6, customer.getPwd());
            stm.setString(7, customer.getQuestion());
            stm.setString(8, customer.getAnswer());
            stm.setInt(9, customer.getCustomerID());
            int row = stm.executeUpdate();
            if (row > 1) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean deletecustomer(CustomerDTO customer) {
        con = ConnectDB.getCon();
        try {
            query = "delete from customer where customerid=?";

            stm = con.prepareStatement(query);
            stm.setInt(1, customer.getCustomerID());
            int row = stm.executeUpdate();
            if (row > 1) {
                return true;
            }

        } catch (SQLException e) {
            return false;
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
