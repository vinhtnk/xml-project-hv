/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.CategoryDTO;
import DTO.CategoryDTO;
import com.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoang
 */
public class CategoryDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    String query = "";

    public List<CategoryDTO> getAllCategory() {
        con = ConnectDB.getCon();
        List<CategoryDTO> listCategory = new ArrayList<CategoryDTO>();
        try {
            query = "Select * from category";
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();
             
            while (rs.next()) {
                CategoryDTO category = new CategoryDTO();
                category.setCategoryID(rs.getInt("categoryID"));
                category.setCategoryName(rs.getString("categoryName"));
                listCategory.add(category);
            }
            return listCategory;

        } catch (SQLException e) {
            e.printStackTrace();
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
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public List<CategoryDTO> searchCategoryByName(String key) {
        con = ConnectDB.getCon();
        List<CategoryDTO> listCategory = new ArrayList<CategoryDTO>();
        try {
            query = "Select * from category where categoryName like ?";
            stm = con.prepareStatement(query);
            stm.setString(1, "%" + key + "%");
            rs = stm.executeQuery();
             
            while (rs.next()) {
                CategoryDTO category = new CategoryDTO();
                category.setCategoryID(rs.getInt("categoryID"));
                category.setCategoryName(rs.getString("categoryName"));
                listCategory.add(category);
            }
            return listCategory;
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
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean addNewCategory(CategoryDTO category) {
        con = ConnectDB.getCon();


        try {
            query = "insert into category (categoryName) values (?)";
            stm = con.prepareStatement(query);
            stm.setString(1, category.getCategoryName());
            int row = stm.executeUpdate();
            if (row > 1) {
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
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean updateCategory(CategoryDTO category) {
        con = ConnectDB.getCon();


        try {
            query = "update category set categoryName = ? where categoryid=?";

            stm = con.prepareStatement(query);
            stm.setString(1, category.getCategoryName());
            stm.setInt(2, category.getCategoryID());
            int row = stm.executeUpdate();
            if (row > 1) {
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
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean deleteCategory(CategoryDTO category) {
        con = ConnectDB.getCon();
        try {
            query = "delete from category where categoryid=?";

            stm = con.prepareStatement(query);
            stm.setInt(1, category.getCategoryID());
            int row = stm.executeUpdate();
            if (row > 1) {
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
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
