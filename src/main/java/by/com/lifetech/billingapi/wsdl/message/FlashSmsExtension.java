
package by.com.lifetech.billingapi.wsdl.message;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FlashSmsExtension complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="FlashSmsExtension">
 *   <complexContent>
 *     <extension base="{http://astelit.ukr/idr/sms/message/}Extension">
 *       <sequence>
 *       </sequence>
 *       <attribute name="flash" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlashSmsExtension")
public class FlashSmsExtension
    extends Extension
{

    @XmlAttribute(name = "flash")
    protected Boolean flash;

    /**
     * Gets the value of the flash property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlash() {
        return flash;
    }

    /**
     * Sets the value of the flash property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlash(Boolean value) {
        this.flash = value;
    }

}
