/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

/**
 *
 * @author Hoang
 */
public class CategoryDTO {
    private int categoryID;
    private String categoryName;

    public CategoryDTO() {
    }

    public CategoryDTO(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    

}
