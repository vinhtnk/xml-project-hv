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

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    String query = "";

    public List<OrderDTO> getAllOrder() {
        con = ConnectDB.getCon();
        List<OrderDTO> listorder = new ArrayList<OrderDTO>();
        try {
            query = "Select * from orders";
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();

            while (rs.next()) {
                OrderDTO order = new OrderDTO();
                order.setOrderID(rs.getInt("OrderID"));
                order.setEmail(rs.getString("Email"));
                order.setTotalPrice(rs.getInt("TotalPrice"));
                order.setOrderDate(rs.getDate("OrderDate"));
                order.setReceiveDate(rs.getDate("ReceiveDate"));
                order.setNote(rs.getString("Note"));
                order.setStatus(rs.getString("Status"));
                listorder.add(order);
            }
            return listorder;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<OrderDTO> searchorderByID(int orderId) {
        con = ConnectDB.getCon();
        List<OrderDTO> listorder = new ArrayList<OrderDTO>();
        try {
            query = "Select * from orders where orderId = ?";
            stm = con.prepareStatement(query);
            stm.setInt(1, orderId);
            rs = stm.executeQuery();

            while (rs.next()) {
                OrderDTO order = new OrderDTO();
                order.setOrderID(rs.getInt("OrderID"));
                order.setEmail(rs.getString("Email"));
                order.setTotalPrice(rs.getInt("TotalPrice"));
                order.setOrderDate(rs.getDate("OrderDate"));
                order.setReceiveDate(rs.getDate("ReceiveDate"));
                order.setNote(rs.getString("Note"));
                order.setStatus(rs.getString("Status"));
                listorder.add(order);
            }
            return listorder;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean addNeworder(OrderDTO order) {
        con = ConnectDB.getCon();
        try {
            query = "insert into orders (Email,TotalPrice,OrderDate,ReceiveDate,Note,Status) "
                    + "values (?,?,?,?,?,?)";
            stm = con.prepareStatement(query);
            stm.setString(1, order.getEmail());
            stm.setFloat(2, order.getTotalPrice());
            stm.setDate(3, new java.sql.Date(new Date().getTime()));
            stm.setDate(4, (java.sql.Date) order.getReceiveDate());
            stm.setString(5, order.getNote());
            stm.setString(6, order.getStatus());
            int row = stm.executeUpdate();
            if (row > 1) {

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
        return false;
    }

    public boolean updateorder(OrderDTO order) {
        con = ConnectDB.getCon();
        try {
            query = "update orders set status = ? where orderid=?";

            stm = con.prepareStatement(query);
            stm.setString(1, order.getStatus());
            stm.setInt(2, order.getOrderID());
            int row = stm.executeUpdate();
            if (row > 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean deleteorder(OrderDTO order) {
        con = ConnectDB.getCon();
        try {
            query = "delete from orders where orderid=?";

            stm = con.prepareStatement(query);
            stm.setInt(1, order.getOrderID());
            int row = stm.executeUpdate();
            if (row > 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public int getOrderId(OrderDTO order) {
        //con = ConnectDB.getCon();
        int orderId = 1;
        try {

            query = "Select @@Identity";
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();

            if (rs.next()) {
                orderId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            return orderId;
        
    }
}
