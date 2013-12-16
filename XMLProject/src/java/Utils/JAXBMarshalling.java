/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DAO.CategoryDAO;
import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import DAO.ProductDAO;
import DTO.CategoryDTO;
import DTO.OrderDTO;
import DTO.OrderDetailDTO;
import DTO.ProductDTO;
import generated.jaxb.Categories.Categories;
import generated.jaxb.Categories.CategoryType;
import generated.jaxb.OrderDetail.OrderDetailType;
import generated.jaxb.OrderDetail.OrderDetails;
import generated.jaxb.Orders.OrderType;
import generated.jaxb.Orders.Orders;
import generated.jaxb.Products.ProductType;
import generated.jaxb.Products.Products;
import java.io.File;
import java.math.BigInteger;
import java.sql.Date;
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

    public static void marshallingOrder(String outputPath) {
        try {
            OrderDAO orderDAO = new OrderDAO();
        List<OrderDTO> listOrder = new ArrayList<OrderDTO>();
        listOrder = orderDAO.getAllOrder();

        Orders orders = new Orders();
        if (listOrder.size() > 0) {
            for (int i = 0; i < listOrder.size(); i++) {
                OrderType order = new OrderType();
                order.setOrderID(BigInteger.valueOf(listOrder.get(i).getOrderID()));
                order.setEmail(listOrder.get(i).getEmail());
                order.setTotalPrice(listOrder.get(i).getTotalPrice());
                
                orders.getOrder().add(order);
            }
        }
        JAXBMarshalling.marshallXML(orders, outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void marshallingOrderDetails(String outputPath) {
        try {
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        List<OrderDetailDTO> listOrderDetail = new ArrayList<OrderDetailDTO>();
        listOrderDetail = orderDetailDAO.getAllOrderDetail();

        OrderDetails odts = new OrderDetails();
        if (listOrderDetail.size() > 0) {
            for (int i = 0; i < listOrderDetail.size(); i++) {
                OrderDetailType odt = new OrderDetailType();
                odt.setOrderID(BigInteger.valueOf(listOrderDetail.get(i).getOrderID()));
                odt.setProductID(listOrderDetail.get(i).getProductID());
                odt.setProductName(listOrderDetail.get(i).getProductName());
                odt.setQuantity(BigInteger.valueOf(listOrderDetail.get(i).getQuantity()));
                odt.setPrice(listOrderDetail.get(i).getPrice());
                odts.getOrderDetail().add(odt);
            }
        }
        JAXBMarshalling.marshallXML(odts, outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
