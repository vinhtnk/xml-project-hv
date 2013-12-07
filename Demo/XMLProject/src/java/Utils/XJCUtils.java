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
import java.io.File;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 *
 * @author Quaikiet
 */
public class XJCUtils {

    public static void main(String[] args) {
        try {
            XJCUtils.generateGamesObject();
            XJCUtils.generateGenresObject();
            XJCUtils.generateUsersObject();
            XJCUtils.generateOrdersObject();
            XJCUtils.generateOrderDetailsObject();
            XJCUtils.generateTopGamesObject();
        } catch (Exception e) {
            System.out.println("Generated failed");
        }
    }

     public static void generateTopGamesObject() {
        try {
            XJCUtils.generateObject("generated.jaxb.topgames", "src/java", "src/java/schema/topgames.xsd");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void generateUsersObject() {
        try {
            XJCUtils.generateObject("generated.jaxb.users", "src/java", "src/java/schema/users.xsd");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void generateOrderDetailsObject() {
        try {
            XJCUtils.generateObject("generated.jaxb.orderdetails", "src/java", "src/java/schema/orderdetails.xsd");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void generateOrdersObject() {
        try {
            XJCUtils.generateObject("generated.jaxb.orders", "src/java", "src/java/schema/orders.xsd");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void generateGamesObject() {
        try {
            XJCUtils.generateObject("generated.jaxb.games", "src/java", "src/java/schema/games.xsd");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void generateGenresObject() {
        try {
            XJCUtils.generateObject("generated.jaxb.genres", "src/java", "src/java/schema/genres.xsd");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void generateObject(String packageName, String outputPath, String schemaFilePath) {
        try {
            SchemaCompiler sc = XJC.createSchemaCompiler();
            sc.setErrorListener(new ErrorListener() {

                public void error(SAXParseException saxpe) {
                    System.out.println("error: " + saxpe.getMessage());
                }

                public void fatalError(SAXParseException saxpe) {
                    System.out.println("fatalError: " + saxpe.getMessage());
                }

                public void warning(SAXParseException saxpe) {
                    System.out.println("warning: " + saxpe.getMessage());
                }

                public void info(SAXParseException saxpe) {
                    System.out.println("info: " + saxpe.getMessage());
                }
            });
            sc.forcePackageName(packageName);
            File schemaFile = new File(schemaFilePath);
            InputSource is = new InputSource(schemaFile.toURI().toString());
            sc.parseSchema(is);
            S2JJAXBModel model = sc.bind();
            JCodeModel code = model.generateCode(null, null);
            code.build(new File(outputPath));
            System.out.println("Generate successfully");
        } catch (Exception e) {
        }
    }
}
