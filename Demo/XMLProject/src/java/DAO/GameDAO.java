/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.GameDTO;
import DTO.TopGameDTO;
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
public class GameDAO {

    private Connection connection;
    private Statement statement;
    String sqlQuery;

    public GameDAO() {
        try {
            connection = DBUtil.getDBConnection();
            statement = connection.createStatement();
            sqlQuery = "";
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insert(GameDTO game) {
        try {
            Date today = new Date();
            sqlQuery = "Insert Into tblGames Values('" + game.getName() + "', '" + game.getPrice() + "','" + game.getPinboard() + "','" + game.getDescription() + "' , '" + game.getImageUrl() + "','" + new java.sql.Date(today.getTime()) + "','" + game.getGenreId() + "')";
            statement = connection.createStatement();
            int rs = statement.executeUpdate(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateGames(List<GameDTO> list) {
        try {
            for (int i = 0; i < list.size(); i++) {

                sqlQuery = "Update tblGames Set  Name='" + list.get(i).getName() + "', Price='" + list.get(i).getPrice() + "',Description='" + list.get(i).getDescription() + "',Pinboard='" + list.get(i).getPinboard() + "' Where GameId='" + list.get(i).getGameId() + "'";
                int rs = statement.executeUpdate(sqlQuery);
                if (rs == 0) {
                    System.out.println("Update table tblGames Has Error");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<TopGameDTO> getTopGame(int fromMonth, int fromYear, int toMonth, int toYear, int numberGame) {
        List<TopGameDTO> listtop = new ArrayList<TopGameDTO>();
        int dateNumber = 0;

        int listMonthhas31Date[] = {1, 3, 5, 7, 8, 10, 12};
        int listMonthhas30Date[] = {4, 6, 9, 11};

        if (toMonth == 2) {
            dateNumber = 28;
        }
        for (int i = 0; i < 4; i++) {
            if (toMonth == listMonthhas30Date[i]) {
                dateNumber = 30;
            }
        }
        if (dateNumber != 30 || dateNumber != 28) {
            dateNumber = 31;
        }

        try {
            sqlQuery = "Select games.Name, games.Price,  SUM(orders.Quantity) AS Quantity, Sum(orders.Total) as Total "
                    + " From (select tblOrders.OrderId,tblOrderDetails.GameId, tblOrders.OrderDate, tblOrderDetails.Quantity, tblOrderDetails.Total, tblOrderDetails.Price "
                    + " From tblOrderDetails, tblOrders where tblOrderDetails.OrderId=tblOrders.OrderId) orders left join tblGames games "
                    + " on orders.GameId = games.GameId "
                    + " Where orders.OrderDate >= '" + fromYear + "-" + fromMonth + "-01' And "
                    + " orders.orderDate <= '" + toYear + "-" + toMonth + "-" + dateNumber + "' "
                    + " group by games.Name, games.Price "
                    + " order by Quantity DESC";
            ResultSet rs = statement.executeQuery(sqlQuery);
            int countGame = 0;
            while (rs.next() && countGame < numberGame) {
                TopGameDTO game = new TopGameDTO();
                game.setName(rs.getString("Name"));
                game.setPrice(rs.getBigDecimal("Price"));
                game.setQuantity(rs.getInt("Quantity"));
                game.setTotal(rs.getBigDecimal("Total"));
                listtop.add(game);
                countGame++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listtop;
    }

    public List<GameDTO> getAll() {
        List<GameDTO> listGame = new ArrayList<GameDTO>();
        try {
            sqlQuery = "Select * from tblGames";
            ResultSet rs = null;
            rs = statement.executeQuery(sqlQuery);
            GameDTO game = null;
            while (rs.next()) {
                game = new GameDTO();
                game.setGameId(rs.getInt("GameId"));
                game.setImageUrl(rs.getString("Image_URL"));
                game.setDescription(rs.getString("Description"));
                game.setName(rs.getString("Name"));
                game.setPinboard(rs.getString("PinBoard"));
                game.setPrice(rs.getFloat("Price"));
                game.setCreatedDate(new Date(rs.getDate("CreatedDate").getTime()));
                game.setGenreId(rs.getString("GenreId"));
                listGame.add(game);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listGame;
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
