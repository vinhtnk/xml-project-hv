/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.GameDAO;
import DAO.OrderDAO;
import DTO.GameDTO;
import DTO.OrderDTO;
import Utils.ConvertDate;
import Utils.MarshallerUtils;
import Utils.UnmarshallerUtils;
import generated.jaxb.games.Games;
import generated.jaxb.orders.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Quaikiet
 */
public class AdminController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String path = getServletContext().getRealPath("/").replace("\\", "/");

//                String xslPath = path+"WEB-INF/orders.xsd";
//
//                UnmarshallerUtils.validateSchemaOrders(xslPath, xmlPath);
            String btnAction = request.getParameter("btnAction");

            if (null != btnAction) {
                if (btnAction.equals("Create New Game")) {
                    String txtName = request.getParameter("txtName");
                    String txtPrice = request.getParameter("txtPrice");
                    String txtPinBoard = request.getParameter("txtPinBoard");
                    String txtFile = request.getParameter("urlImageFile");
                    String genreId = request.getParameter("cboGenreName");
                    String txtDescription = request.getParameter("txtDescription");
                    GameDAO gameDao = new GameDAO();
                    GameDTO gameDTO = new GameDTO();
                    gameDTO.setName(txtName);
                    gameDTO.setPrice(Float.parseFloat(txtPrice));
                    gameDTO.setGenreId(genreId);
                    gameDTO.setImageUrl(txtFile);
                    gameDTO.setPinboard(txtPinBoard);
                    gameDTO.setDescription(txtDescription);
                    gameDao.insert(gameDTO);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                    dispatcher.forward(request, response);
                } else if (btnAction.equals("updateOrders")) {
                    String xmlContent = request.getParameter("xmlContent");
                    System.out.print("XML Content: " + xmlContent);
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(new InputSource(new StringReader(xmlContent)));

                    TransformerFactory tff = TransformerFactory.newInstance();
                    Transformer trans = tff.newTransformer();
                    trans.setOutputProperty(OutputKeys.INDENT, "YES");
                    Source sc = new DOMSource(doc);
                    Result result = new StreamResult(path + "XML/orders.xml");
                    trans.transform(sc, result);
                    Orders orders = UnmarshallerUtils.unmarshallingOrders(path + "XML/orders.xml");
                    List<OrderDTO> listOrders = new ArrayList<OrderDTO>();
                    if (orders.getOrder().size() > 0) {
                        for (int i = 0; i < orders.getOrder().size(); i++) {
                            OrderDTO order = new OrderDTO();
                            order.setOrderId(orders.getOrder().get(i).getOrderId().intValue());
                            order.setUserId(orders.getOrder().get(i).getUserId().intValue());
                            order.setOrderDate(ConvertDate.convertToDate(orders.getOrder().get(i).getOrderDate()));
                            order.setInvoice(orders.getOrder().get(i).getInvoice());
                            order.setDelivered(orders.getOrder().get(i).isDelivered());
                            listOrders.add(order);
                        }

                        OrderDAO orderDao = new OrderDAO();
                        orderDao.updateOrders(listOrders);
                        orderDao.closeConnection();

                    }

                } else if (btnAction.equals("updateGames")) {
                    String xmlContent = request.getParameter("xmlContent");
                     System.out.print("XML Content: " + xmlContent);
                    DocumentBuilderFactory dff = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dff.newDocumentBuilder();
                    Document doc = db.parse(new InputSource(new StringReader(xmlContent)));
                    TransformerFactory tff = TransformerFactory.newInstance();
                    Transformer trans = tff.newTransformer();
                    trans.setOutputProperty(OutputKeys.INDENT, "YES");
                    Source source = new DOMSource(doc);
                    Result result = new StreamResult(path + "XML/gamestmp.xml");
                    trans.transform(source, result);
                    Games games = UnmarshallerUtils.unmarshallingGames(path + "XML/gamestmp.xml");
                    List<GameDTO> listGames = new ArrayList<GameDTO>();
                    for (int i = 0; i < games.getGame().size(); i++) {
                        GameDTO game = new GameDTO();
                        game.setGameId(games.getGame().get(i).getGameId().intValue());
                        game.setPrice(games.getGame().get(i).getPrice());
                        game.setName(games.getGame().get(i).getName());
                        game.setDescription(games.getGame().get(i).getDescription());
                        game.setPinboard(games.getGame().get(i).getPinboard());
                        listGames.add(game);
                    }
                    GameDAO gameDao = new GameDAO();
                    gameDao.updateGames(listGames);
                    gameDao.closeConnection();
                }

            }
        } catch (TransformerException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
