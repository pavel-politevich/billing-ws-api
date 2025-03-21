
package by.com.lifetech.billingapi.wsdl.message;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for smppOptionalParameter complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="smppOptionalParameter">
 *   <complexContent>
 *     <extension base="{http://astelit.ukr/idr/sms/message/}smppParameter">
 *       <sequence>
 *         <element name="type" type="{http://astelit.ukr/idr/sms/message/}smppParameterType" minOccurs="0"/>
 *         <element name="value" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "smppOptionalParameter", propOrder = {
    "type",
    "value"
})
public class SmppOptionalParameter
    extends SmppParameter
{

    @XmlSchemaType(name = "string")
    protected SmppParameterType type;
    protected Object value;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link SmppParameterType }
     *     
     */
    public SmppParameterType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmppParameterType }
     *     
     */
    public void setType(SmppParameterType value) {
        this.type = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setValue(Object value) {
        this.value = value;
    }

}
