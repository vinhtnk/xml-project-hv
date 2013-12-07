/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DAO.AdminDAO;
import DAO.GameDAO;
import DAO.GenresDAO;
import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import DTO.OrderDTO;
import DTO.OrderDetailDTO;
import generated.jaxb.orderdetails.OrderDetailType;
import generated.jaxb.orderdetails.Orderdetails;
import generated.jaxb.orders.OrderType;
import generated.jaxb.orders.Orders;



import DAO.UserDAO;
import DTO.AdminDTO;


import DTO.GameDTO;
import DTO.GenreDTO;
import DTO.TopGameDTO;


import DTO.UserDTO;

import generated.jaxb.games.GameType;

import generated.jaxb.games.Games;
import generated.jaxb.genres.GenreType;
import generated.jaxb.genres.Genres;
import generated.jaxb.topgames.TopGameType;
import generated.jaxb.topgames.Topgames;


import generated.jaxb.users.UserType;

import generated.jaxb.users.Users;

import java.io.File;

import java.math.BigInteger;

import java.util.ArrayList;

import java.util.List;

import javax.xml.bind.JAXBContext;

import javax.xml.bind.Marshaller;

/**
 *
 * @author Quaikiet
 */
public class MarshallerUtils {

    public static void marshalling(Object obj, String outputPath) {

        try {
            JAXBContext ctx = JAXBContext.newInstance(obj.getClass());
            Marshaller mar = ctx.createMarshaller();
            mar.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
            mar.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(obj, new File(outputPath));
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

    public static void marshallingGames(String outputPath) {
        GameDAO gameDao = new GameDAO();
        List<GameDTO> listGames = new ArrayList<GameDTO>();
        listGames = gameDao.getAll();
        Games games = new Games();
        if (listGames.size() > 0) {
            for (int i = 0; i < listGames.size(); i++) {
                GameType game = new GameType();
                game.setGameId(BigInteger.valueOf(listGames.get(i).getGameId()));
                game.setName(listGames.get(i).getName());
                game.setDescription(listGames.get(i).getDescription());
                game.setPinboard(listGames.get(i).getPinboard());
                game.setImageURL(listGames.get(i).getImageUrl());
                game.setPrice(listGames.get(i).getPrice());
                game.setGenreId(listGames.get(i).getGenreId());
                java.util.Date date = new java.util.Date(listGames.get(i).getCreatedDate().getTime());
                game.setCreatedDate(ConvertDate.convertToXmlGre(date));
                games.getGame().add(game);
            }
        }
        MarshallerUtils.marshalling(games, outputPath);
    }

    public static void marshallingUsers(String outputPath) {
        UserDAO userDao = new UserDAO();
        List<UserDTO> listUsers = new ArrayList<UserDTO>();
        listUsers = userDao.findAll();
        Users users = new Users();
        if (listUsers.size() > 0) {
            for (int i = 0; i < listUsers.size(); i++) {
                UserType user = new UserType();
                user.setUserName(listUsers.get(i).getUsername());
                user.setPassword(listUsers.get(i).getPassword());
                user.setRole(listUsers.get(i).getRole());
                user.setFullName(listUsers.get(i).getFullName());
                user.setEmail(listUsers.get(i).getEmail());
                user.setUserId(BigInteger.valueOf(listUsers.get(i).getUserId()));
                users.getUser().add(user);
            }
        }
        MarshallerUtils.marshalling(users, outputPath);
    }

    public static void marshallingOrders(String outputPath) {
        OrderDAO orderDao = new OrderDAO();
        List<OrderDTO> listOrders = new ArrayList<OrderDTO>();
        listOrders = orderDao.getOrders();
        Orders orders = new Orders();
        if (listOrders.size() > 0) {

            for (int i = 0; i < listOrders.size(); i++) {
                OrderType order = new OrderType();
                order.setOrderId(BigInteger.valueOf(listOrders.get(i).getOrderId()));
                order.setUserId(BigInteger.valueOf(listOrders.get(i).getUserId()));
                order.setOrderDate(ConvertDate.convertToXmlGre(listOrders.get(i).getOrderDate()));
                order.setInvoice(listOrders.get(i).getInvoice());
                order.setDelivered(listOrders.get(i).isDelivered());
                orders.getOrder().add(order);
            }
        }
        MarshallerUtils.marshalling(orders, outputPath);
    }

    public static void marshallingOrderDetails(String outputPath) {
        OrderDetailDAO orderdetailDao = new OrderDetailDAO();
        List<OrderDetailDTO> listOrderDetails = new ArrayList<OrderDetailDTO>();
        listOrderDetails = orderdetailDao.getAll();
        orderdetailDao.closeConnection();
        Orderdetails orderdetails = new Orderdetails();
        if (listOrderDetails.size() > 0) {

            for (int i = 0; i < listOrderDetails.size(); i++) {
                OrderDetailType orderdetail = new OrderDetailType();
                orderdetail.setOrderId(BigInteger.valueOf(listOrderDetails.get(i).getOrderId()));
                orderdetail.setGameId(BigInteger.valueOf(listOrderDetails.get(i).getGameId()));
                orderdetail.setPrice(listOrderDetails.get(i).getPrice());
                orderdetail.setQuantity(BigInteger.valueOf(listOrderDetails.get(i).getQuantity()));
                orderdetail.setTotal(listOrderDetails.get(i).getTotal());
                orderdetails.getOrderDetail().add(orderdetail);
            }
        }
        MarshallerUtils.marshalling(orderdetails, outputPath);
    }

    public static void marshallingGenres(String outputPath) {
        GenresDAO genreDao = new GenresDAO();
        List<GenreDTO> listGenre = new ArrayList<GenreDTO>();
        listGenre = genreDao.findAll();
        Genres genres = new Genres();
        if (listGenre.size() > 0) {
            for (int i = 0; i < listGenre.size(); i++) {
                GenreType Gen = new GenreType();
                Gen.setGenreId(listGenre.get(i).getGenderId());
                String genreNameA = listGenre.get(i).getGenderName();
                Gen.setGenreName(genreNameA);
                genres.getGenre().add(Gen);
            }
            MarshallerUtils.marshalling(genres, outputPath);
        }
    }
 


    public static void marshallingTopGame(String outputPath, int fromMonth, int fromYear, int toMonth, int toYear, int numberOfGames)
    {
        TopGameDTO topgame = new TopGameDTO();
        GameDAO gameDAO = new GameDAO();
        List<TopGameDTO> listtopgame = new ArrayList<TopGameDTO>();
        listtopgame = gameDAO.getTopGame(fromMonth, fromYear, toMonth, toYear, numberOfGames);
        Topgames topgames = new Topgames();

        for (int i = 0; i < listtopgame.size(); i++) {
           TopGameType game = new TopGameType();
           game.setName(listtopgame.get(i).getName());
           game.setPrice(listtopgame.get(i).getPrice());
           game.setQuantity(BigInteger.valueOf(listtopgame.get(i).getQuantity()));
           game.setTotal(listtopgame.get(i).getTotal());
           topgames.getTopGame().add(game);
        }
        topgames.setNumBerTopGame(BigInteger.valueOf(listtopgame.size()));

        MarshallerUtils.marshalling(topgames,outputPath );
    }
}
