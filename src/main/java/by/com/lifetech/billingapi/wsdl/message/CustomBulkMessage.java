
package by.com.lifetech.billingapi.wsdl.message;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomBulkMessage complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="CustomBulkMessage">
 *   <complexContent>
 *     <extension base="{http://astelit.ukr/idr/sms/message/}BulkMessage">
 *       <sequence>
 *         <element name="messages" type="{http://astelit.ukr/idr/sms/message/}Message" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomBulkMessage", propOrder = {
    "messages"
})
public class CustomBulkMessage
    extends BulkMessage
{

    @XmlElement(nillable = true)
    protected List<Message> messages;

    /**
     * Gets the value of the messages property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messages property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getMessages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Message }
     * </p>
     * 
     * 
     * @return
     *     The value of the messages property.
     */
    public List<Message> getMessages() {
        if (messages == null) {
            messages = new ArrayList<>();
        }
        return this.messages;
    }

}
