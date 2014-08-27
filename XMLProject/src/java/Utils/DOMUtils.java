/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
public class DOMUtils {
public String categoryFilter(String categoryID, String xmlPath) {
        try {
            Document categories = DOMUtils.parserDOM(xmlPath);
            Element result = categories.createElement("categories");
            creteaCategoryNodes(result, categories, categoryID);
            return DOMUtils.toString(result);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DOMUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DOMUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DOMUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    private void creteaCategoryNodes(Element result, Node doc, String categoryID) {
        NodeList node = doc.getChildNodes();
        if (node != null) {
            for (int i = 0; i < node.getLength(); i++) {
                if (node.item(i).getNodeName().equals("categoryID") && node.item(i).getTextContent().equals(categoryID)) {
                    result.appendChild(doc);
                    break;
                }
                creteaCategoryNodes(result, (Node) node.item(i), categoryID);
            }
        }
    }

    public static Document parserDOM(String filePath) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        File file = new File(filePath);
        Document doc = (Document) db.parse(file);
        return doc;
    }

    public static Document parseXMLFromString(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return (Document) builder.parse(is);
    }

    public static void writeXML(Node node, String filePath) throws TransformerConfigurationException, TransformerException {
        TransformerFactory tff = TransformerFactory.newInstance();
        Transformer trans = tff.newTransformer();
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        Source src = new DOMSource(node);
        File file = new File(filePath);
        StreamResult result = new StreamResult(file);
        trans.transform(src, result);
    }

    public static String toString(Node doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }
}
