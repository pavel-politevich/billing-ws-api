
package by.com.lifetech.billingapi.wsdl.message;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommonBulkMessage complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="CommonBulkMessage">
 *   <complexContent>
 *     <extension base="{http://astelit.ukr/idr/sms/message/}BulkMessage">
 *       <sequence>
 *         <element name="commonConf" type="{http://astelit.ukr/idr/sms/message/}BaseMessage"/>
 *         <element name="destinations" type="{http://astelit.ukr/idr/sms/message/}MessageDest" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommonBulkMessage", propOrder = {
    "commonConf",
    "destinations"
})
public class CommonBulkMessage
    extends BulkMessage
{

    @XmlElement(required = true)
    protected BaseMessage commonConf;
    @XmlElement(nillable = true)
    protected List<MessageDest> destinations;

    /**
     * Gets the value of the commonConf property.
     * 
     * @return
     *     possible object is
     *     {@link BaseMessage }
     *     
     */
    public BaseMessage getCommonConf() {
        return commonConf;
    }

    /**
     * Sets the value of the commonConf property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaseMessage }
     *     
     */
    public void setCommonConf(BaseMessage value) {
        this.commonConf = value;
    }

    /**
     * Gets the value of the destinations property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the destinations property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDestinations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageDest }
     * </p>
     * 
     * 
     * @return
     *     The value of the destinations property.
     */
    public List<MessageDest> getDestinations() {
        if (destinations == null) {
            destinations = new ArrayList<>();
        }
        return this.destinations;
    }

}
