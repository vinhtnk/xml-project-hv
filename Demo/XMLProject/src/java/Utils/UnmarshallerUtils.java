/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DAO.OrderDAO;
import generated.jaxb.games.Games;
import generated.jaxb.orderdetails.Orderdetails;
import generated.jaxb.orders.Orders;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Quaikiet
 */
public class UnmarshallerUtils {

    public static List<Object> Unmarshalling(Object obj, String sourceXML) {
        try {
            JAXBContext jc = JAXBContext.newInstance(obj.getClass());
            Unmarshaller um = jc.createUnmarshaller();
            File f = new File(sourceXML);
            JAXBElement jax = (JAXBElement) um.unmarshal(f);
            List<Object> list = new ArrayList<Object>();
            list = (List<Object>) jax.getValue();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Orders unmarshallingOrders(String sourceXML)
    {
        try {
            JAXBContext ctx = JAXBContext.newInstance(Orders.class);
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            File file = new File(sourceXML);
            Orders orders  = (Orders) unmarshaller.unmarshal(file);
            return orders;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

     public static Games unmarshallingGames(String sourceXML)
    {
        try {
            JAXBContext ctx = JAXBContext.newInstance(Games.class);
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            File file = new File(sourceXML);
            Games games  = (Games) unmarshaller.unmarshal(file);
            return games;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static Orderdetails UnmarshallingOrderDetails(String sourceXML){
        try {
            JAXBContext ctx = JAXBContext.newInstance(Orderdetails.class);
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            File file = new File(sourceXML);
            Orderdetails orderdetails = (Orderdetails) unmarshaller.unmarshal(file);
            return orderdetails;

        } catch (JAXBException ex) {
            Logger.getLogger(UnmarshallerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void validateSchemaOrders(String schemaPath, String inputXMLPath) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Orders.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(schemaPath));

            Validator validator = schema.newValidator();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            dbf.setValidating(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            File f = new File(inputXMLPath);
            Document doc = db.parse(f);
            validator.validate(new DOMSource(doc));
            OrderDAO orders = new OrderDAO();
            Orders order = (Orders) unmarshaller.unmarshal(f);
            System.out.println("order: " + order.toString());
//            if (order == null) {
//                return;
//            } else {
//                orders.insertOrder(order);
//            }
//            orders.closeConnection();

        } catch (IOException ex) {
            Logger.getLogger(UnmarshallerUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(UnmarshallerUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(UnmarshallerUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(UnmarshallerUtils.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
