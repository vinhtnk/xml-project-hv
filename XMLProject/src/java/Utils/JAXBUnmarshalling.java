/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

import generated.jaxb.Categories.Categories;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Hoang
 */
public class JAXBUnmarshalling {
    public static List<Object> unmashallXML(Object obj, String sourceXML){
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

    public static Categories unmarshallingCategory(String sourceXML){
        try {
            JAXBContext ctx = JAXBContext.newInstance(Categories.class);
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            File file = new File(sourceXML);
            Categories categories  = (Categories) unmarshaller.unmarshal(file);
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
