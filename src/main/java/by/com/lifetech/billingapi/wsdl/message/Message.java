
package by.com.lifetech.billingapi.wsdl.message;

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Message complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Message">
 *   <complexContent>
 *     <extension base="{http://astelit.ukr/idr/sms/message/}BaseMessage">
 *       <sequence>
 *         <element name="externalId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="dest" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="params" type="{http://astelit.ukr/idr/sms/message/}Param" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="processExpiration" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="controlParameters" type="{http://astelit.ukr/idr/sms/message/}controlParameter" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Message", propOrder = {
    "externalId",
    "dest",
    "params",
    "processExpiration",
    "controlParameters"
})
public class Message
    extends BaseMessage
{

    @XmlElement(required = true)
    protected String externalId;
    @XmlElement(required = true)
    protected String dest;
    @XmlElement(nillable = true)
    protected List<Param> params;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar processExpiration;
    @XmlElement(nillable = true)
    protected List<ControlParameter> controlParameters;

    /**
     * Gets the value of the externalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * Sets the value of the externalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalId(String value) {
        this.externalId = value;
    }

    /**
     * Gets the value of the dest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDest() {
        return dest;
    }

    /**
     * Sets the value of the dest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDest(String value) {
        this.dest = value;
    }

    /**
     * Gets the value of the params property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the params property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getParams().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Param }
     * </p>
     * 
     * 
     * @return
     *     The value of the params property.
     */
    public List<Param> getParams() {
        if (params == null) {
            params = new ArrayList<>();
        }
        return this.params;
    }

    /**
     * Gets the value of the processExpiration property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProcessExpiration() {
        return processExpiration;
    }

    /**
     * Sets the value of the processExpiration property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProcessExpiration(XMLGregorianCalendar value) {
        this.processExpiration = value;
    }

    /**
     * Gets the value of the controlParameters property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the controlParameters property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getControlParameters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ControlParameter }
     * </p>
     * 
     * 
     * @return
     *     The value of the controlParameters property.
     */
    public List<ControlParameter> getControlParameters() {
        if (controlParameters == null) {
            controlParameters = new ArrayList<>();
        }
        return this.controlParameters;
    }

}
