/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import java.util.Date;

/**
 *
 * @author Hoang
 */
public class OrderDTO {
    private int orderID;
    private String email;
    private float totalPrice;
    private Date orderDate;
    private Date receiveDate;
    private String note;
    private String status;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, String email, float totalPrice, Date orderDate, Date receiveDate, String note, String status) {
        this.orderID = orderID;
        this.email = email;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.receiveDate = receiveDate;
        this.note = note;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    

}
