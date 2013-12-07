
package generated.jaxb.topgames;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="TopGame" type="{http://xml.netbeans.org/schema/topgames}TopGameType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="NumBerTopGame" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "topGame"
})
@XmlRootElement(name = "topgames")
public class Topgames {

    @XmlElement(name = "TopGame", required = true)
    protected List<TopGameType> topGame;
    @XmlAttribute(name = "NumBerTopGame")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger numBerTopGame;

    /**
     * Gets the value of the topGame property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the topGame property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTopGame().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TopGameType }
     * 
     * 
     */
    public List<TopGameType> getTopGame() {
        if (topGame == null) {
            topGame = new ArrayList<TopGameType>();
        }
        return this.topGame;
    }

    /**
     * Gets the value of the numBerTopGame property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumBerTopGame() {
        return numBerTopGame;
    }

    /**
     * Sets the value of the numBerTopGame property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumBerTopGame(BigInteger value) {
        this.numBerTopGame = value;
    }

}
