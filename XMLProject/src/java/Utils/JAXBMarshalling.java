/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import DTO.CategoryDTO;
import DTO.ProductDTO;
import generated.jaxb.Categories.Categories;
import generated.jaxb.Categories.CategoryType;
import generated.jaxb.Products.ProductType;
import generated.jaxb.Products.Products;
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
public class JAXBMarshalling {

    public static void marshallXML(Object obj, String outputPath) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(obj.getClass());
            Marshaller mar = ctx.createMarshaller();
            mar.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
            mar.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            mar.marshal(obj, new File(outputPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void marshallingCategories(String outputPath) {
        try {
            CategoryDAO categoryDAO = new CategoryDAO();
            List<CategoryDTO> listCategory = new ArrayList<CategoryDTO>();
            listCategory = categoryDAO.getAllCategory();

            Categories categories = new Categories();
            if (listCategory.size() > 0) {
                for (int i = 0; i < listCategory.size(); i++) {
                    CategoryType category = new CategoryType();
                    category.setCategoryID(BigInteger.valueOf(listCategory.get(i).getCategoryID()));
                    category.setCategoryName(listCategory.get(i).getCategoryName());
                    categories.getCategory().add(category);
                }
            }
            JAXBMarshalling.marshallXML(categories, outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void marshallingProduct(String outputPath) {
        try {
            ProductDAO productDAO = new ProductDAO();
        List<ProductDTO> listProduct = new ArrayList<ProductDTO>();
        listProduct = productDAO.getAllProduct();

        Products products = new Products();
        if (listProduct.size() > 0) {
            for (int i = 0; i < listProduct.size(); i++) {
                ProductType product = new ProductType();
                product.setProductID(listProduct.get(i).getProductID());
                product.setProductName(listProduct.get(i).getProductName());
                product.setCategoryID(BigInteger.valueOf(listProduct.get(i).getCategoryID()));
                product.setPrice(Integer.valueOf(listProduct.get(i).getPrice()));
                product.setDescription(listProduct.get(i).getDescription());
                product.setImgLink(listProduct.get(i).getImg_link());
                product.setNewProduct(Boolean.valueOf(listProduct.get(i).isNew_product()));
                products.getProduct().add(product);
            }
        }
        JAXBMarshalling.marshallXML(products, outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
