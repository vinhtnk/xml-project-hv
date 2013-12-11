
package generated.jaxb.OrderDetail;

import java.util.ArrayList;
import java.util.List;
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

    @XmlElement(name="orderDetail", required = true)
    protected List<OrderDetailType> orderDetail;

    public List<OrderDetailType> getOrderDetail() {
        if (orderDetail == null){
            orderDetail = new ArrayList<OrderDetailType>();
        }
        return orderDetail;
    }

    

}
