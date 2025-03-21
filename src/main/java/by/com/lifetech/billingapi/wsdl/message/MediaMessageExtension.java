
package by.com.lifetech.billingapi.wsdl.message;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MediaMessageExtension complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="MediaMessageExtension">
 *   <complexContent>
 *     <extension base="{http://astelit.ukr/idr/sms/message/}Extension">
 *       <sequence>
 *         <element name="smil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="attachments" type="{http://astelit.ukr/idr/sms/message/}Attachment" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MediaMessageExtension", propOrder = {
    "smil",
    "attachments"
})
public class MediaMessageExtension
    extends Extension
{

    protected String smil;
    protected List<Attachment> attachments;

    /**
     * Gets the value of the smil property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmil() {
        return smil;
    }

    /**
     * Sets the value of the smil property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmil(String value) {
        this.smil = value;
    }

    /**
     * Gets the value of the attachments property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attachments property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAttachments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Attachment }
     * </p>
     * 
     * 
     * @return
     *     The value of the attachments property.
     */
    public List<Attachment> getAttachments() {
        if (attachments == null) {
            attachments = new ArrayList<>();
        }
        return this.attachments;
    }

}
