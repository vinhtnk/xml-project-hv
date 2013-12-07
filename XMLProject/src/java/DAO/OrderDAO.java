/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DTO.OrderDTO;
import com.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoang
 */
public class OrderDAO {
    Connection con;
    PreparedStatement stm;
    String query = "";

    public List<OrderDTO> getAllOrder() {
        con = ConnectDB.getCon();
        List<OrderDTO> listorder = new ArrayList<OrderDTO>();
        try {
            query = "Select * from order";
            stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery(query);
            OrderDTO order = null;
            while (rs.next()) {
                order = new OrderDTO();
                order.setOrderID(rs.getInt(0));
                order.setCustomerEmail(rs.getString(1));
                order.setTotalPrice(rs.getFloat(2));
                order.setOrderDate(rs.getDate(3));
                order.setReceiveDate(rs.getDate(4));
                order.setNote(rs.getString(5));
                order.setStatus(rs.getString(6));
                listorder.add(order);
            }
            return listorder;

        } catch (SQLException e) {
            return null;
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<OrderDTO> searchorderByID(int orderId) {
        con = ConnectDB.getCon();
        List<OrderDTO> listorder = new ArrayList<OrderDTO>();
        try {
            query = "Select * from order where orderId = ?";
            stm = con.prepareStatement(query);
            stm.setInt(1, orderId);
            ResultSet rs = stm.executeQuery(query);
            OrderDTO order = null;
            while (rs.next()) {
                order = new OrderDTO();
                order.setOrderID(rs.getInt(0));
                order.setCustomerEmail(rs.getString(1));
                order.setTotalPrice(rs.getFloat(2));
                order.setOrderDate(rs.getDate(3));
                order.setReceiveDate(rs.getDate(4));
                order.setNote(rs.getString(5));
                order.setStatus(rs.getString(6));
                listorder.add(order);
            }
            return listorder;

        } catch (SQLException e) {
            return null;
        } finally {
            try {
                con.close();
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean addNeworder(OrderDTO order) {
        con = ConnectDB.getCon();
        try {
            query = "insert into order (CustomerEmail,TotalPrice,OrderDate,ReceiveDate,Note,Status) "
                    + "values (?,?,?,?,?,?)";
            stm = con.prepareStatement(query);
            stm.setString(1, order.getCustomerEmail());
            stm.setFloat(2, order.getTotalPrice());
            stm.setDate(3, new java.sql.Date(new Date().getTime()));
            stm.setDate(4, (java.sql.Date) order.getReceiveDate());
            stm.setString(5, order.getNote());
            stm.setString(6, order.getStatus());
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
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean updateorder(OrderDTO order){
        con = ConnectDB.getCon();
        try {
            query = "update order set status = ? where orderid=?" ;

            stm = con.prepareStatement(query);
            stm.setString(1, order.getStatus());
            stm.setInt(2, order.getOrderID());
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
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean deleteorder(OrderDTO order){
        con = ConnectDB.getCon();
        try {
            query = "delete from order where orderid=?" ;

            stm = con.prepareStatement(query);
            stm.setInt(1, order.getOrderID());
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
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
