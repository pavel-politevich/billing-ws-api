
package by.com.lifetech.billingapi.wsdl.message;

import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BaseMessage complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="BaseMessage">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="idrCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="source" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="timeControl" type="{http://astelit.ukr/idr/sms/message/}TimeControl"/>
 *         <element name="schedulTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         <element name="identification" type="{http://astelit.ukr/idr/sms/message/}Identification"/>
 *         <element name="lang" type="{http://astelit.ukr/idr/sms/message/}Language"/>
 *         <element name="extension" type="{http://astelit.ukr/idr/sms/message/}Extension"/>
 *         <element name="msgId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseMessage", propOrder = {
    "idrCode",
    "source",
    "text",
    "timeControl",
    "schedulTime",
    "identification",
    "lang",
    "extension",
    "msgId"
})
@XmlSeeAlso({
    Message.class
})
public abstract class BaseMessage {

    @XmlElement(required = true)
    protected String idrCode;
    @XmlElement(required = true)
    protected String source;
    @XmlElement(required = true)
    protected String text;
    @XmlElement(required = true)
    protected TimeControl timeControl;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar schedulTime;
    @XmlElement(required = true)
    protected Identification identification;
    @XmlElement(required = true)
    protected Language lang;
    @XmlElement(required = true)
    protected Extension extension;
    protected String msgId;

    /**
     * Gets the value of the idrCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdrCode() {
        return idrCode;
    }

    /**
     * Sets the value of the idrCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdrCode(String value) {
        this.idrCode = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the timeControl property.
     * 
     * @return
     *     possible object is
     *     {@link TimeControl }
     *     
     */
    public TimeControl getTimeControl() {
        return timeControl;
    }

    /**
     * Sets the value of the timeControl property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeControl }
     *     
     */
    public void setTimeControl(TimeControl value) {
        this.timeControl = value;
    }

    /**
     * Gets the value of the schedulTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSchedulTime() {
        return schedulTime;
    }

    /**
     * Sets the value of the schedulTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSchedulTime(XMLGregorianCalendar value) {
        this.schedulTime = value;
    }

    /**
     * Gets the value of the identification property.
     * 
     * @return
     *     possible object is
     *     {@link Identification }
     *     
     */
    public Identification getIdentification() {
        return identification;
    }

    /**
     * Sets the value of the identification property.
     * 
     * @param value
     *     allowed object is
     *     {@link Identification }
     *     
     */
    public void setIdentification(Identification value) {
        this.identification = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link Language }
     *     
     */
    public Language getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link Language }
     *     
     */
    public void setLang(Language value) {
        this.lang = value;
    }

    /**
     * Gets the value of the extension property.
     * 
     * @return
     *     possible object is
     *     {@link Extension }
     *     
     */
    public Extension getExtension() {
        return extension;
    }

    /**
     * Sets the value of the extension property.
     * 
     * @param value
     *     allowed object is
     *     {@link Extension }
     *     
     */
    public void setExtension(Extension value) {
        this.extension = value;
    }

    /**
     * Gets the value of the msgId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * Sets the value of the msgId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgId(String value) {
        this.msgId = value;
    }

}
