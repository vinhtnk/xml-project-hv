/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.OrderDTO;
import Utils.DBUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quaikiet
 */
public class OrderDAO {

    Connection connection;
    Statement statement;
    String sqlQuery;

    public OrderDAO() {
        try {
            connection = DBUtil.getDBConnection();
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int insertOrder(OrderDTO order) {
        Date today = new Date();
        sqlQuery = "Insert Into tblOrders Values ('" + order.getUserId() + "','" + new java.sql.Date(today.getTime()) + "', '" + order.getInvoice() + "', 'false')";
        try {

            int result = statement.executeUpdate(sqlQuery);
            if (result > 0) {
                return result;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getOrderId(OrderDTO order)
    {
        int orderId=0;
         sqlQuery = "Select * From tblOrders";
        try {

            ResultSet result = statement.executeQuery(sqlQuery);
            while(result.next())
            {
                orderId++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderId;
    }

    public void updateOrders(List<OrderDTO> list) {
        for (int i = 0; i < list.size(); i++) {
            try {
                sqlQuery = "Update tblOrders Set delivered ='" + list.get(i).isDelivered() + "'" + " Where OrderId='" + list.get(i).getOrderId() + "'";
                int rs = statement.executeUpdate(sqlQuery);
                if(rs==0)
                {
                    System.out.println("Update table tblOrders Has Error");
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<OrderDTO> getOrders() {
        sqlQuery = "Select * From tblOrders";
        List<OrderDTO> listOrders = new ArrayList<OrderDTO>();
        try {

            ResultSet rs = statement.executeQuery(sqlQuery);
            OrderDTO order = null;
            while (rs.next()) {
                order = new OrderDTO();
                order.setOrderId(rs.getInt("OrderId"));
                order.setUserId(rs.getInt("UserId"));
                order.setInvoice(rs.getBigDecimal("Invoice"));
                order.setOrderDate(new Date(rs.getDate("OrderDate").getTime()));
                order.setDelivered(rs.getBoolean("Delivered"));
                listOrders.add(order);
            }
        } catch (Exception ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOrders;
    }

    public void closeConnection() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
