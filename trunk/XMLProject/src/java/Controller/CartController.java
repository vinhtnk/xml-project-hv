/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import DTO.OrderDTO;
import DTO.OrderDetailDTO;
import Utils.JAXBUnmarshalling;
import generated.jaxb.OrderDetail.OrderDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
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
 * @author Hoang
 */
public class CartController extends HttpServlet {

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
            String btnAction = request.getParameter("btnAction");
            String email = request.getParameter("email");
            if (btnAction.equalsIgnoreCase("checkout")) {
                //String orderXML = request.getParameter("orderXML");
                String orderDetailsXML = request.getParameter("orderDetailsXML");

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                //Document doc1 = db.parse(new InputSource(new StringReader(orderXML)));
                Document doc2 = db.parse(new InputSource(new StringReader(orderDetailsXML)));

                TransformerFactory tff = TransformerFactory.newInstance();
                Transformer trans = tff.newTransformer();
                trans.setOutputProperty(OutputKeys.INDENT, "YES");
                trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                // Source src1 = new DOMSource(doc1);
                Source src2 = new DOMSource(doc2);
                String path = getServletContext().getRealPath("/");

                //Result result1 = new StreamResult(path + "xml/orders.xml");
                Result result2 = new StreamResult(path + "xml/orderDetails.xml");
                //trans.transform(src1, result1);
                trans.transform(src2, result2);

                OrderDetails orderDetails = JAXBUnmarshalling.unmarshallingOrderDetails(path + "xml/orderDetails.xml");
                List<OrderDetailDTO> listOrderdetail = new ArrayList<OrderDetailDTO>();
                if (orderDetails.getOrderDetail().size() > 0) {
                    //  invoice to save to tblOrders
                    double invoice = 0;
                    for (int i = 0; i < orderDetails.getOrderDetail().size(); i++) {
                        OrderDetailDTO orderdetail = new OrderDetailDTO();
                        orderdetail.setProductID(orderDetails.getOrderDetail().get(i).getProductID());
                        orderdetail.setProductName(orderDetails.getOrderDetail().get(i).getProductName());
                        orderdetail.setPrice(orderDetails.getOrderDetail().get(i).getPrice());
                        orderdetail.setQuantity(orderDetails.getOrderDetail().get(i).getQuantity().intValue());
                        invoice += orderdetail.getQuantity() * orderdetail.getPrice();
                        listOrderdetail.add(orderdetail);
                    }
                    OrderDAO orderDao = new OrderDAO();
                    OrderDTO order = new OrderDTO();
                    order.setTotalPrice((int) invoice);
                    order.setEmail(email);
                    orderDao.addNeworder(order);
                    int orderId = orderDao.getOrderId(order);
                    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                    orderDetailDAO.addProducttoOrder(listOrderdetail, orderId);
                }              
                
                out.print("checkout successful!");
                
            }

        } catch (TransformerException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SAXException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
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
