
package generated.jaxb.OrderDetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderDetail" type="{http://xml.netbeans.org/schema/orderDetail}orderDetailType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "orderDetail"
})
@XmlRootElement(name = "orderDetails")
public class OrderDetails {

    @XmlElement(required = true)
    protected OrderDetailType orderDetail;

    /**
     * Gets the value of the orderDetail property.
     * 
     * @return
     *     possible object is
     *     {@link OrderDetailType }
     *     
     */
    public OrderDetailType getOrderDetail() {
        return orderDetail;
    }

    /**
     * Sets the value of the orderDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderDetailType }
     *     
     */
    public void setOrderDetail(OrderDetailType value) {
        this.orderDetail = value;
    }

}
