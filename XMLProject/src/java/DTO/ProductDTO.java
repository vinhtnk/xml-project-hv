/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author Hoang
 */
@XmlSeeAlso(java.lang.Object.class)
public class ProductDTO implements Serializable{
    private int productID;
    private String productName;
    private int categoryID;
    private float price;
    private String description;
    private String img_link;
    private boolean new_product;

    public ProductDTO() {
    }



    public ProductDTO(int productID, String productName, int categoryID, float price, String description, String img_link, boolean new_product) {
        this.productID = productID;
        this.productName = productName;
        this.categoryID = categoryID;
        this.price = price;
        this.description = description;
        this.img_link = img_link;
        this.new_product = new_product;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_link() {
        return img_link;
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
    }

    public boolean isNew_product() {
        return new_product;
    }

    public void setNew_product(boolean new_product) {
        this.new_product = new_product;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



}