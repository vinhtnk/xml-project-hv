
package generated.jaxb.games;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GameType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GameType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GameId" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Price" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="Pinboard" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Image_URL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="GenreId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GameType", propOrder = {
    "gameId",
    "name",
    "price",
    "pinboard",
    "description",
    "imageURL",
    "createdDate",
    "genreId"
})
public class GameType {

    @XmlElement(name = "GameId", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger gameId;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Price")
    protected float price;
    @XmlElement(name = "Pinboard", required = true)
    protected String pinboard;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "Image_URL", required = true)
    protected String imageURL;
    @XmlElement(name = "CreatedDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "GenreId", required = true)
    protected String genreId;

    /**
     * Gets the value of the gameId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getGameId() {
        return gameId;
    }

    /**
     * Sets the value of the gameId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setGameId(BigInteger value) {
        this.gameId = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(float value) {
        this.price = value;
    }

    /**
     * Gets the value of the pinboard property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPinboard() {
        return pinboard;
    }

    /**
     * Sets the value of the pinboard property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPinboard(String value) {
        this.pinboard = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the imageURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Sets the value of the imageURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageURL(String value) {
        this.imageURL = value;
    }

    /**
     * Gets the value of the createdDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the value of the createdDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedDate(XMLGregorianCalendar value) {
        this.createdDate = value;
    }

    /**
     * Gets the value of the genreId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenreId() {
        return genreId;
    }

    /**
     * Sets the value of the genreId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenreId(String value) {
        this.genreId = value;
    }

}
