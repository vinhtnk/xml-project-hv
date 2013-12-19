/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ProductDTO;
import com.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoang
 */
public class ProductDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    String query = "";

    public List<ProductDTO> getAllProduct() {
        con = ConnectDB.getCon();
        List<ProductDTO> listProduct = new ArrayList<ProductDTO>();
        try {
            query = "Select * from product";
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();
            ProductDTO product = null;
            while (rs.next()) {
                product = new ProductDTO();
                product.setProductID(rs.getString("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setCategoryID(rs.getInt("CategoryId"));
                product.setPrice(rs.getInt("Price"));
                product.setDescription(rs.getString("Description"));
                product.setImg_link(rs.getString("Image_Link"));
                product.setNew_product(rs.getBoolean("New_Product"));
                listProduct.add(product);
            }
            return listProduct;

        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<ProductDTO> getNewProduct() {
        con = ConnectDB.getCon();
        List<ProductDTO> listProduct = new ArrayList<ProductDTO>();
        try {
            query = "Select * from product where New_Product='true'";
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();
             
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setProductID(rs.getString("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setCategoryID(rs.getInt("CategoryId"));
                product.setPrice(rs.getInt("Price"));
                product.setDescription(rs.getString("Description"));
                product.setImg_link(rs.getString("Image_Link"));
                product.setNew_product(rs.getBoolean("New_Product"));
                listProduct.add(product);
            }
            return listProduct;

        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<ProductDTO> searchProductByName(String key) {
        con = ConnectDB.getCon();
        List<ProductDTO> listProduct = new ArrayList<ProductDTO>();
        try {
            query = "Select * from product where productName like ?";
            stm = con.prepareStatement(query);
            stm.setString(1, "%" + key + "%");
            rs = stm.executeQuery();
            
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setProductID(rs.getString("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setCategoryID(rs.getInt("CategoryId"));
                product.setPrice(rs.getInt("Price"));
                product.setDescription(rs.getString("Description"));
                product.setImg_link(rs.getString("Image_Link"));
                product.setNew_product(rs.getBoolean("New_Product"));
                listProduct.add(product);
            }
            return listProduct;

        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<ProductDTO> searchProductByCategory(int categoryID) {
        con = ConnectDB.getCon();
        List<ProductDTO> listProduct = new ArrayList<ProductDTO>();
        try {
            query = "Select * from product where categoryid=?";
            stm = con.prepareStatement(query);
            stm.setInt(1, categoryID);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setProductID(rs.getString("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setCategoryID(rs.getInt("CategoryId"));
                product.setPrice(rs.getInt("Price"));
                product.setDescription(rs.getString("Description"));
                product.setImg_link(rs.getString("Image_Link"));
                product.setNew_product(rs.getBoolean("New_Product"));
                listProduct.add(product);
            }
            return listProduct;

        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean addNewProduct(ProductDTO product) {
        con = ConnectDB.getCon();
        try {
            query = "insert into product (productID, productName, categoryid, price, description, image_link, new_product"
                    + "values (?,?,?,?,?,?,?)";
            stm = con.prepareStatement(query);
            stm.setString(1, product.getProductID());
            stm.setString(2, product.getProductID());
            stm.setInt(3, product.getCategoryID());
            stm.setDouble(4, product.getPrice());
            stm.setString(5, product.getDescription());
            stm.setString(6, product.getImg_link());
            stm.setBoolean(7, product.isNew_product());
            stm.execute();
            int row = stm.executeUpdate();
            if(row>1){
                return true;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean updateProduct1(String id, String des,String img,String name, int cate,int price, String isnew){
        con = ConnectDB.getCon();
        try {
            query = "update product set productName = ?, categoryid =?, price=?, description=?, "
                    + "image_link=?, new_product=? where productid=?" ;

            stm = con.prepareStatement(query);
            stm.setString(1, id);
            stm.setString(2, name );
            stm.setInt(3, cate);
            stm.setInt(4, price);
            stm.setString(5, des);
            stm.setString(6, img);
            stm.setString(7, isnew);
            int row = stm.executeUpdate();
            if(row>1){
                return true;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean updateProduct(ProductDTO product){
        con = ConnectDB.getCon();
        try {
            query = "update product set productName = ?, categoryid =?, price=?, description=?, "
                    + "image_link=?, new_product=? where productid=?" ;

            stm = con.prepareStatement(query);
            stm.setString(1, product.getProductName());
            stm.setInt(2, product.getCategoryID());
            stm.setInt(3, product.getPrice());
            stm.setString(4, product.getDescription());
            stm.setString(5, product.getImg_link());
            stm.setBoolean(6, product.isNew_product());
            stm.setString(7, product.getProductID());
            int row = stm.executeUpdate();
            if(row>1){
                return true;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean deleteProduct(String id){
        con = ConnectDB.getCon();
        try {
            query = "delete from product where productid=?" ;

            stm = con.prepareStatement(query);            
            stm.setString(1, id);
            int row = stm.executeUpdate();
            if(row>0){
                return true;
            }
            
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}