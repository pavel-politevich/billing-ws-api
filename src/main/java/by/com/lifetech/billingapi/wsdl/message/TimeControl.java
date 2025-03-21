
package by.com.lifetech.billingapi.wsdl.message;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TimeControl complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="TimeControl">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *       </sequence>
 *       <attribute name="schedulDays" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       <attribute name="btStart" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="btEnd" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeControl")
public class TimeControl {

    @XmlAttribute(name = "schedulDays")
    protected Integer schedulDays;
    @XmlAttribute(name = "btStart")
    protected String btStart;
    @XmlAttribute(name = "btEnd")
    protected String btEnd;

    /**
     * Gets the value of the schedulDays property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSchedulDays() {
        return schedulDays;
    }

    /**
     * Sets the value of the schedulDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSchedulDays(Integer value) {
        this.schedulDays = value;
    }

    /**
     * Gets the value of the btStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBtStart() {
        return btStart;
    }

    /**
     * Sets the value of the btStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBtStart(String value) {
        this.btStart = value;
    }

    /**
     * Gets the value of the btEnd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBtEnd() {
        return btEnd;
    }

    /**
     * Sets the value of the btEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBtEnd(String value) {
        this.btEnd = value;
    }

}
