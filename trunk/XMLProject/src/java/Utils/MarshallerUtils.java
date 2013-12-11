/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DAO.CategoryDAO;
import DTO.CategoryDTO;
import generated.jaxb.Categories.Categories;
import generated.jaxb.Categories.CategoryType;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Hoang
 */
public class MarshallerUtils {

    public static void marshallXML(Object obj, String outputPath) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(obj.getClass());
            Marshaller mar = ctx.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            mar.marshal(obj, new File(outputPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void marshallingCategories(String outputPath) {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<CategoryDTO> listCategory = new ArrayList<CategoryDTO>();
        listCategory = categoryDAO.getAllCategory();

        Categories categories = new Categories();
        if (listCategory.size() > 0) {
            for (int i = 0; i < listCategory.size(); i++) {
                CategoryType category = new CategoryType();
                category.setCategoryID(BigInteger.valueOf(listCategory.get(i).getCategoryID()));
                category.setCategoryName(listCategory.get(i).getCategoryName());
                categories.setCategory(category);
            }
        }
        MarshallerUtils.marshallXML(categories, outputPath);
    }
}
