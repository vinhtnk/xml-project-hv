/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Utils.MarshallerUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
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
 * @author Quaikiet
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
             String path = getServletContext().getRealPath("/").replace("\\", "/");
           String btnAction = request.getParameter("btnAction");
            if (null != btnAction) {
                String OutputTopgamesXMLPath = path + "XML/topgames.xml";
                if (btnAction.trim().equals("PrintListTopGame")) {
                    int fromMonth = Integer.parseInt(request.getParameter("cboFromMonth").trim());
                    int fromYear = Integer.parseInt(request.getParameter("cboFromYear".trim()));
                    int toMonth = Integer.parseInt(request.getParameter("cboToMonth".trim()));
                    int toYear = Integer.parseInt(request.getParameter("cboToYear".trim()));
                    int numberOfGame = Integer.parseInt(request.getParameter("txtTopGameNeedReport"));
                    MarshallerUtils.marshallingTopGame(OutputTopgamesXMLPath, fromMonth, fromYear, toMonth, toYear, numberOfGame);
                    String outputFOPath = path + "FO/topgame.fo";
                    String inputXSLPath = path + "stylesheet/topgameFO.xsl";
                    convertToPDF(response, outputFOPath, OutputTopgamesXMLPath, inputXSLPath);
                }
            }
            
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
   public void methodTrAX(String xslPath, String xmlPath, String ouputPath) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            StreamSource xstlFile = new StreamSource(xslPath);
            Transformer trans = tf.newTransformer(xstlFile);
            StreamSource xmlFile = new StreamSource(xmlPath);
            StreamResult htmlFile = new StreamResult(
                    new FileOutputStream(ouputPath));
            trans.transform(xmlFile, htmlFile);

        } catch (Exception e) {
        }

    }
   public void convertToPDF(HttpServletResponse response, String outputFOPath, String inputXMLPath, String inputXSLPath) {
        {
            FileInputStream input = null;
            try {
                methodTrAX(inputXSLPath, inputXMLPath, outputFOPath);
                File file = new File(outputFOPath);
                input = new FileInputStream(file);
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                response.setContentType("application/pdf");
                FopFactory ff = FopFactory.newInstance();
                FOUserAgent fua = ff.newFOUserAgent();
                Fop fop = ff.newFop(MimeConstants.MIME_PDF, fua, output);
                TransformerFactory tff = TransformerFactory.newInstance();
                Transformer trans = tff.newTransformer();
                File fo = new File(outputFOPath);
                Source src = new StreamSource(fo);
                Result result = new SAXResult(fop.getDefaultHandler());
                trans.transform(src, result);
                byte[] content = output.toByteArray();
                response.setContentLength(content.length);
                response.getOutputStream().write(content);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (TransformerException ex) {
                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FOPException ex) {
                java.util.logging.Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    input.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
