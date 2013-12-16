/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import generated.jaxb.OrderDetail.OrderDetails;
import java.io.File;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

public class XJCUtils {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
//            XJCUtils.generateCategoriesObject();
//            XJCUtils.generateProductsObject();
//            XJCUtils.generateUsersObject();
//            XJCUtils.generateOrdersObject();
//            XJCUtils.generateOrderDetailsObject();
//            JAXBMarshalling.marshallingOrder("src/java/Utils/demo3.xml");
//            JAXBMarshalling.marshallingOrderDetails("src/java/Utils/demo4.xml");
//            String orderDetailsXMLDOM = "<orderDetails>";
//
//            orderDetailsXMLDOM += "<orderDetail>";
//            orderDetailsXMLDOM += "<orderID></orderID>";
//            orderDetailsXMLDOM += "<productID>" + "123" + "</productID>";
//            orderDetailsXMLDOM += "<productName>" + "abc" + "</productName>";
//            orderDetailsXMLDOM += "<quantity>" + "1" + "</quantity>";
//            orderDetailsXMLDOM += "<price>" + "145" + "</price>";
//            orderDetailsXMLDOM += "</orderDetail>";
//            orderDetailsXMLDOM += "</orderDetails>";
//                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            //Document doc1 = db.parse(new InputSource(new StringReader(orderXML)));
//            Document doc2 = db.parse(new InputSource(new StringReader(orderDetailsXMLDOM)));
//
//            TransformerFactory tff = TransformerFactory.newInstance();
//            Transformer trans = tff.newTransformer();
//            trans.setOutputProperty(OutputKeys.INDENT, "YES");
//
//            //Source src1 = new DOMSource(doc1);
//            Source src2 = new DOMSource(doc2);
//            //String path = getServletContext().getRealPath("/");
//
//            //Result result1 = new StreamResult(path + "xml/orders.xml");
//            Result result2 = new StreamResult("src/java/Utils/orderDetails.xml");
//            //trans.transform(src1, result1);
//            trans.transform(src2, result2);
            OrderDetails orderDetails = JAXBUnmarshalling.unmarshallingOrderDetails("src/java/Utils/orderDetails.xml");
            System.out.println("name:" + orderDetails.getOrderDetail().get(0).getProductID());
            System.out.println("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void generateCategoriesObject() {
        try {
            generateObject("generated.jaxb.Categories", "src/java/", "src/java/schema/categories.xsd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateProductsObject() {
        try {
            generateObject("generated.jaxb.Products", "src/java/", "src/java/schema/products.xsd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateUsersObject() {
        try {
            generateObject("generated.jaxb.Users", "src/java/", "src/java/schema/users.xsd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateOrdersObject() {
        try {
            generateObject("generated.jaxb.Orders", "src/java/", "src/java/schema/orders.xsd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateOrderDetailsObject() {
        try {
            generateObject("generated.jaxb.OrderDetail", "src/java/", "src/java/schema/orderDetail.xsd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateObject(String packageName, String outputPath, String schemaFilePath) {
        try {
            SchemaCompiler sc = XJC.createSchemaCompiler();
            sc.setErrorListener(new ErrorListener() {

                public void error(SAXParseException saxpe) {
                    System.out.println("error " + saxpe.getMessage());
                }

                public void fatalError(SAXParseException saxpe) {
                    System.out.println("fatal " + saxpe.getMessage());
                }

                public void warning(SAXParseException saxpe) {
                    System.out.println("warning " + saxpe.getMessage());
                }

                public void info(SAXParseException saxpe) {
                    System.out.println("info " + saxpe.getMessage());
                }
            });

            sc.forcePackageName(packageName);
            File schemaFile = new File(schemaFilePath);
            InputSource is = new InputSource(schemaFile.toURI().toString());
            sc.parseSchema(is);
            S2JJAXBModel model = sc.bind();
            JCodeModel code = model.generateCode(null, null);
            code.build(new File(outputPath));
            System.out.println("Generate object successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
