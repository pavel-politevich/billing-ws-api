
package by.com.lifetech.billingapi.wsdl.message;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transmitBulkMessage complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="transmitBulkMessage">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="bulkMessage" type="{http://astelit.ukr/idr/sms/message/}BulkMessage" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transmitBulkMessage", namespace = "http://api.ws.rtn.idr.astelit.ukr/", propOrder = {
    "bulkMessage"
})
public class TransmitBulkMessage {

    protected BulkMessage bulkMessage;

    /**
     * Gets the value of the bulkMessage property.
     * 
     * @return
     *     possible object is
     *     {@link BulkMessage }
     *     
     */
    public BulkMessage getBulkMessage() {
        return bulkMessage;
    }

    /**
     * Sets the value of the bulkMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BulkMessage }
     *     
     */
    public void setBulkMessage(BulkMessage value) {
        this.bulkMessage = value;
    }

}
