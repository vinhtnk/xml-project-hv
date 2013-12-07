/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.OrderDetailDTO;
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
public class OrderDetailDAO {

    private Connection connection;
    private Statement statement;
    private String sqlQuery;

    public OrderDetailDAO() {
        try {
            connection = DBUtil.getDBConnection();
            statement = connection.createStatement();
            sqlQuery = "";
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertOrderDetail(List<OrderDetailDTO> list, int orderId)
    {
         for (int i = 0; i < list.size(); i++) {
            try {
                sqlQuery = "Insert Into tblOrderDetails Values ('"+orderId+"', '" + list.get(i).getGameId()+"','"+list.get(i).getQuantity()+"', '"+list.get(i).getQuantity()+"', '"+list.get(i).getTotal()+"')";
                int rs = statement.executeUpdate(sqlQuery);
                if(rs==0)
                {
                    System.out.println("insert table tblOrderDetails Has Error");
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<OrderDetailDTO> getAll() {
        sqlQuery = "Select * From tblOrderDetails";
        List<OrderDetailDTO> orderDetails = new ArrayList<OrderDetailDTO>();
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                OrderDetailDTO orderdetail = new OrderDetailDTO();
                orderdetail.setGameId(rs.getInt("GameId"));
                orderdetail.setOrderId(rs.getInt("OrderId"));
                orderdetail.setPrice(rs.getBigDecimal("Price"));
                orderdetail.setQuantity(rs.getInt("Quantity"));
                orderDetails.add(orderdetail);
            }
        } catch (Exception e) {
        }
        return orderDetails;
    }

    public void closeConnection() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
