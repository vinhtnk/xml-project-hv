/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import DTO.OrderDTO;
import DTO.OrderDetailDTO;
import Utils.UnmarshallerUtils;
import generated.jaxb.orderdetails.Orderdetails;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class OrderController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String path = getServletContext().getRealPath("/").replace("\\", "/");
        String header = request.getHeader("Content-Type");
        try {
            String userid = request.getParameter("userId");
            String action = request.getParameter("btnAction");
            String xmlParam = request.getParameter("xmlReq");

            if (action != null) {
                if (action.equals("checkOut")) {

                    System.out.println("checkout content: " + xmlParam);
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(new InputSource(new StringReader(xmlParam)));

                    TransformerFactory tff = TransformerFactory.newInstance();
                    Transformer trans = tff.newTransformer();
                    trans.setOutputProperty(OutputKeys.INDENT, "YES");

                    Source src = new DOMSource(doc);

                    Result result = new StreamResult(path + "XML/orderdetail-temp.xml");

                    trans.transform(src, result);

                    Orderdetails orderDetails = UnmarshallerUtils.UnmarshallingOrderDetails(path + "XML/orderdetail-temp.xml");
                    List<OrderDetailDTO> listOrderdetail = new ArrayList<OrderDetailDTO>();
                    if (orderDetails.getOrderDetail().size() > 0) {
                        //  invoice to save to tblOrders
                        double invoice = 0;
                        for (int i = 0; i < orderDetails.getOrderDetail().size(); i++) {
                            OrderDetailDTO orderdetail = new OrderDetailDTO();
                            orderdetail.setGameId(orderDetails.getOrderDetail().get(i).getGameId().intValue());
                            orderdetail.setPrice(orderDetails.getOrderDetail().get(i).getPrice());
                            orderdetail.setQuantity(orderDetails.getOrderDetail().get(i).getQuantity().intValue());
                            orderdetail.setTotal(BigDecimal.valueOf(orderDetails.getOrderDetail().get(i).getPrice().floatValue() * orderDetails.getOrderDetail().get(i).getQuantity().intValue()));
                            invoice += orderdetail.getQuantity() * orderdetail.getPrice().doubleValue();
                            listOrderdetail.add(orderdetail);
                        }
                        OrderDAO orderDao = new OrderDAO();
                        OrderDTO order = new OrderDTO();
                        order.setInvoice(BigDecimal.valueOf(invoice));
                        order.setDelivered(false);
                        order.setUserId(Integer.parseInt(userid));
                        orderDao.insertOrder(order);
                        int orderId = orderDao.getOrderId(order);
                        orderDao.closeConnection();

                        OrderDetailDAO orderdetailDao = new OrderDetailDAO();

                        orderdetailDao.insertOrderDetail(listOrderdetail, orderId);
                        orderdetailDao.closeConnection();


                    }
                }
            }


        } catch (TransformerException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
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
