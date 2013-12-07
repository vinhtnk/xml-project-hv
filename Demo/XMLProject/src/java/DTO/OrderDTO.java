/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Quaikiet
 */
public class OrderDTO implements Serializable {

    private int orderId;
    private int userId;
    private Date orderDate;
    private BigDecimal invoice;
    private boolean delivered;
    public OrderDTO() {
    }

    public OrderDTO(int orderId, int userId, Date orderDate, BigDecimal invoice, boolean delivered) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.invoice = invoice;
        this.delivered = delivered;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public BigDecimal getInvoice() {
        return invoice;
    }

    public void setInvoice(BigDecimal invoice) {
        this.invoice = invoice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
