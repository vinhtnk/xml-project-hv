/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import DAO.ProductDAO;
import DTO.OrderDTO;
import DTO.OrderDetailDTO;
import DTO.ProductDTO;
import Utils.JAXBUnmarshalling;
import generated.jaxb.Products.Products;
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
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Stork
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
            throws ServletException, IOException, ParserConfigurationException, SAXException, TransformerConfigurationException, TransformerException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String action = request.getParameter("btnAction");
            //String btndelete = request.getParameter("Delete");
            String productid = request.getParameter("productID");
            if (action != null) {
                if (action.equalsIgnoreCase("Delete")) {
                    //String orderXML = request.getParameter("orderXML");
                    //String deleteProductXML = request.getParameter("deleteProductXML");

                    //DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    //DocumentBuilder db = dbf.newDocumentBuilder();
                    //Document doc1 = db.parse(new InputSource(new StringReader(orderXML)));
                    //Document doc2 = db.parse(new InputSource(new StringReader(deleteProductXML)));

                    //TransformerFactory tff = TransformerFactory.newInstance();
                    //Transformer trans = tff.newTransformer();
                    //trans.setOutputProperty(OutputKeys.INDENT, "YES");

                    // Source src1 = new DOMSource(doc1);
                    //Source src2 = new DOMSource(doc2);
                    //String path = getServletContext().getRealPath("/");

                    //Result result1 = new StreamResult(path + "xml/orders.xml");
                    // Result result2 = new StreamResult(path + "xml/deleteProductXML.xml");
                    //trans.transform(src1, result1);
                    //trans.transform(src2, result2);

                    //Products deleteProduct = JAXBUnmarshalling.unmarshallingProducts(path + "xml/deleteProductXML.xml");
                    //List<ProductDTO> listPro = new ArrayList<ProductDTO>();
                    // List<OrderDetailDTO> listOrderdetail = new ArrayList<OrderDetailDTO>();
                    ProductDAO proDao = new ProductDAO();
                    //ProductDTO proDto = new ProductDTO();
                    boolean success = proDao.deleteProduct(productid);
                    if (success) {
                        out.print("delete success");
                        //response.sendRedirect("admin_page.jsp");
                    } else {
                        out.print("delete fail");
                    }
                }
            }
        } //                response.sendRedirect("./checkOutSuccess.jsp");
        catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            out.close();
            //response.sendRedirect("admin_page.jsp");
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
        try {
            processRequest(request, response);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }






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
        try {
            processRequest(request, response);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
