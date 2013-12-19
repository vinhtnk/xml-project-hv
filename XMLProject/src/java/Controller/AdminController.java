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
import generated.jaxb.OrderDetail.OrderDetails;
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
                    ProductDAO proDao = new ProductDAO();
                    boolean success = proDao.deleteProduct(productid);
                    if (success) {
                        out.print("delete success");
                        //response.sendRedirect("admin_page.jsp");
                    } else {
                        out.print("delete fail");
                    }
                }
            }
            if (action.equalsIgnoreCase("addproduct")) {
                String addProductXML = request.getParameter("addProductXML");
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new InputSource(new StringReader(addProductXML)));

                TransformerFactory tff = TransformerFactory.newInstance();
                Transformer trans = tff.newTransformer();
                trans.setOutputProperty(OutputKeys.INDENT, "YES");
                trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                Source src = new DOMSource(doc);
                String path = getServletContext().getRealPath("/");
                Result result = new StreamResult(path + "xml/addProduct.xml");
                trans.transform(src, result);

                Products product = JAXBUnmarshalling.unmarshallingProduct(path + "xml/addProduct.xml");
                List<ProductDTO> newpro = new ArrayList<ProductDTO>();
                ProductDTO proDto = new ProductDTO();
                proDto.setProductID( product.getProduct().get(0).getProductID());
                proDto.setProductName(product.getProduct().get(0).getProductName());
                proDto.setCategoryID(product.getProduct().get(0).getCategoryID().intValue());
                proDto.setPrice(product.getProduct().get(0).getPrice());
                proDto.setDescription(product.getProduct().get(0).getDescription());
                proDto.setImg_link(product.getProduct().get(0).getImgLink());
                proDto.setNew_product(product.getProduct().get(0).isNewProduct());
                ProductDAO proDao = new ProductDAO();
                proDao.addNewProduct(proDto);
                //OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                //orderDetailDAO.addProducttoOrder(listOrderdetail, orderId);

            }
        } catch (Exception ex) {
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
