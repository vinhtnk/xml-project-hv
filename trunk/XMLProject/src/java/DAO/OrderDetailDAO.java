/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DTO.OrderDetailDTO;
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
public class OrderDetailDAO {
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    String query = "";

    public List<OrderDetailDTO> getAllOrderDetail() {
        con = ConnectDB.getCon();
        List<OrderDetailDTO> listOrderDetail= new ArrayList<OrderDetailDTO>();
        try {
            query = "Select * from orderDetail";
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                OrderDetailDTO orderDetail = new OrderDetailDTO();
                orderDetail.setOrderID(rs.getInt(0));
                orderDetail.setProductID(rs.getInt(1));
                orderDetail.setProductName(rs.getString(2));
                orderDetail.setQuantity(rs.getInt(3));
                orderDetail.setPrice(rs.getFloat(4));
                
                listOrderDetail.add(orderDetail);
            }
            return listOrderDetail;

        } catch (SQLException e) {
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
                Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<OrderDetailDTO> searchOrderDetailByID(int orderDetailId) {
        con = ConnectDB.getCon();
        List<OrderDetailDTO> listOrderDetail = new ArrayList<OrderDetailDTO>();
        try {
            query = "Select * from orderDetail where orderDetailId = ?";
            stm = con.prepareStatement(query);
            stm.setInt(1, orderDetailId);
            rs = stm.executeQuery();
             
            while (rs.next()) {
                OrderDetailDTO orderDetail = new OrderDetailDTO();
                orderDetail = new OrderDetailDTO();
                orderDetail.setOrderID(rs.getInt(0));
                orderDetail.setProductID(rs.getInt(1));
                orderDetail.setProductName(rs.getString(2));
                orderDetail.setQuantity(rs.getInt(3));
                orderDetail.setPrice(rs.getFloat(4));
                listOrderDetail.add(orderDetail);
            }
            return listOrderDetail;

        } catch (SQLException e) {
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
                Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addProducttoOrder(OrderDetailDTO orderDetail) {
        con = ConnectDB.getCon();
        try {
            query = "insert into orderDetail (OrderID,ProductID,ProductName,Quantity,Price) "
                    + "values (?,?,?,?,?)";
            stm = con.prepareStatement(query);
            stm.setInt(1, orderDetail.getOrderID());
            stm.setInt(2, orderDetail.getProductID());
            stm.setString(3, orderDetail.getProductName());
            stm.setInt(4, orderDetail.getQuantity());
            stm.setFloat(5, orderDetail.getPrice());
            
            stm.executeUpdate();
            
        } catch (SQLException e) {
            
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public void updateQuantity(OrderDetailDTO orderDetail){
        con = ConnectDB.getCon();
        try {
            query = "update orderDetail set quantity = ? where orderDetailid=? and productid=?" ;

            stm = con.prepareStatement(query);
            stm.setInt(1, orderDetail.getQuantity());
            stm.setInt(2, orderDetail.getOrderID());
            stm.setInt(3, orderDetail.getProductID());
            stm.executeUpdate();
            
        } catch (SQLException e) {
            
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
    }

    private boolean checkOrderDetailID(OrderDetailDTO orderDetail)
    {
        con = ConnectDB.getCon();
        try {
            query = "select orderDetailID from orderDetail where orderDetailid=? and productid=?" ;

            stm = con.prepareStatement(query);
            stm.setInt(1, orderDetail.getOrderID());
            stm.setInt(2, orderDetail.getProductID());
            rs = stm.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            return false;
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
                Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public void addNewOrderDetail(OrderDetailDTO orderDetail){
        if (checkOrderDetailID(orderDetail))
        {
            updateQuantity(orderDetail);
        }
        else
        {
            addProducttoOrder(orderDetail);
        }
    }

    
}
