/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Controller.AdminController;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Quaikiet
 */
public class DOMUtils {

    public static void writeDOMtoXML(String fileXMLPath, Node node) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            Source src = new DOMSource(node);
            File file = new File(fileXMLPath);
            Result rs = new StreamResult(file.toURI().getPath());
            trans.transform(src, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Document parseDOM(String filePath) {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(filePath);
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createCustomOrderXML(String ContextPath) {
        try {

            String xmlOrdersPath = ContextPath + "XML/orders.xml";
            String xmlUsersPath = ContextPath + "XML/users.xml";
            Document docOrders = DOMUtils.parseDOM(xmlOrdersPath);
            Document docUsers = DOMUtils.parseDOM(xmlUsersPath);
            Node nodeOrders = docOrders.getFirstChild();
            if (nodeOrders != null) {
                NodeList listOrder = nodeOrders.getChildNodes();
                if (listOrder.getLength() > 0) {
                    NodeList orderElements = docOrders.getElementsByTagName("Order");
                    int currentOrderIndex = 0;
                    for (int i = 0; i < listOrder.getLength(); i++) {
                        NodeList childListElementOfOrder = listOrder.item(i).getChildNodes();
                        if (childListElementOfOrder.getLength() > 0) {
                            for (int j = 0; j < childListElementOfOrder.getLength(); j++) {
                                Node tmp = childListElementOfOrder.item(j);
                                if (tmp.getNodeName().equals("UserId")) {
                                    String uname = DOMUtils.getUserNameInUserDocumentByUserID(tmp.getTextContent(), docUsers);
                                    Element Username = docOrders.createElement("UserName");
                                    Username.setTextContent(uname);
                                    orderElements.item(currentOrderIndex).appendChild(Username);
                                    currentOrderIndex++;
                                    break;
                                }
                            }
                        }
                    }
                }
                DOMUtils.writeDOMtoXML(xmlOrdersPath, nodeOrders);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getUserNameInUserDocumentByUserID(String userID, Document docUsers) {
        try {
            String Xpath = "//*[UserId='" + userID + "']";
            System.out.println("Xpath:" + Xpath);
            XPathFactory xPathFac = XPathFactory.newInstance();
            XPath xPath = xPathFac.newXPath();
            Node nodeUser = (Node) xPath.evaluate(Xpath, docUsers, XPathConstants.NODE);
            System.out.println("nodeUser:" + nodeUser);
            if (nodeUser != null) {
                NodeList nodeChilds = nodeUser.getChildNodes();
                for (int i = 0; i < nodeChilds.getLength(); i++) {
                    Node tmp = nodeChilds.item(i);
                    if (tmp.getNodeName().equals("UserName")) {
                        String username = tmp.getTextContent().trim();
                        System.out.println("UserName:" + username);
                        return username;
                    }
                }
            }
        } catch (XPathExpressionException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
