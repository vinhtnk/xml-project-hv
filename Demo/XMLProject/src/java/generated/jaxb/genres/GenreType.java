
package generated.jaxb.genres;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for genreType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="genreType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GenreId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GenreName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "genreType", propOrder = {
    "genreId",
    "genreName"
})
public class GenreType {

    @XmlElement(name = "GenreId", required = true)
    protected String genreId;
    @XmlElement(name = "GenreName", required = true)
    protected String genreName;

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

    /**
     * Gets the value of the genreName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenreName() {
        return genreName;
    }

    /**
     * Sets the value of the genreName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenreName(String value) {
        this.genreName = value;
    }

}
