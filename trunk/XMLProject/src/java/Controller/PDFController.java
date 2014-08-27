/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

/**
 *
 * @author Hoang
 */
public class PDFController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String btnAction = request.getParameter("btnAction");

            if (btnAction.equalsIgnoreCase("printOrderDetails")) {
                //pdf
                String path = getServletContext().getRealPath("/");
                String xmlPath = path + "xml/orderDetails.xml";
                String xlsPath = path + "xsl/cartFO.xsl";
                String foPath = path + "fo/cartFO.fo";
                methodTrAX(xlsPath, xmlPath, foPath, path);
                File file = new File(foPath);
                FileInputStream input = new FileInputStream(file);

                ByteArrayOutputStream output = new ByteArrayOutputStream();
                response.setContentType("application/pdf");

                FopFactory ff = FopFactory.newInstance();
                FOUserAgent fua = ff.newFOUserAgent();

                Fop fop = ff.newFop(MimeConstants.MIME_PDF, fua, output);

                TransformerFactory tff = TransformerFactory.newInstance();
                Transformer trans = tff.newTransformer();
                trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                File fo = new File(foPath);
                Source src = new StreamSource(fo);
                Result result = new SAXResult(fop.getDefaultHandler());

                trans.transform(src, result);
                byte[] content = output.toByteArray();
                response.setContentLength(content.length);
                response.getOutputStream().write(content);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } else if (btnAction.equalsIgnoreCase("printPDF")) {
                String path = getServletContext().getRealPath("/");
                String xslPath = path + "xsl/productPDF.xsl";
                String xmlPath = path + "xml/products.xml";
                String foPath = path + "fo/abc.fo";
                //String output = path + "xsl/";
                methodTrAX(xslPath, xmlPath, foPath, path);
                File file = new File(foPath);
                FileInputStream input = new FileInputStream(file);


                ByteArrayOutputStream output = new ByteArrayOutputStream();
                response.setContentType("application/pdf");

                FopFactory ff = FopFactory.newInstance();
                FOUserAgent fua = ff.newFOUserAgent();

                Fop fop = ff.newFop(MimeConstants.MIME_PDF, fua, output);

                TransformerFactory tff = TransformerFactory.newInstance();
                Transformer trans = tff.newTransformer();
                trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                File fo = new File(foPath);
                Source src = new StreamSource(fo);
                Result result = new SAXResult(fop.getDefaultHandler());

                trans.transform(src, result);
                byte[] content = output.toByteArray();
                response.setContentLength(content.length);
                response.getOutputStream().write(content);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            }
        } catch (TransformerException ex) {
            Logger.getLogger(PDFController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (FOPException ex) {
            Logger.getLogger(PDFController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void methodTrAX(String xslPath, String xmlPath, String output, String path) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            StreamSource xsltFile = new StreamSource(xslPath);
            Transformer trans = tf.newTransformer(xsltFile);

            StreamSource xmlFile = new StreamSource(xmlPath);
            StreamResult htmlFile = new StreamResult(new FileOutputStream(output));
            trans.transform(xmlFile, htmlFile);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
