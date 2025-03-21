
package by.com.lifetech.billingapi.wsdl.message;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Language complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="Language">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="billingLang" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="commonLang" type="{http://astelit.ukr/idr/sms/message/}CommonLang" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Language", propOrder = {
    "billingLang",
    "commonLang"
})
public class Language {

    protected String billingLang;
    protected CommonLang commonLang;

    /**
     * Gets the value of the billingLang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingLang() {
        return billingLang;
    }

    /**
     * Sets the value of the billingLang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingLang(String value) {
        this.billingLang = value;
    }

    /**
     * Gets the value of the commonLang property.
     * 
     * @return
     *     possible object is
     *     {@link CommonLang }
     *     
     */
    public CommonLang getCommonLang() {
        return commonLang;
    }

    /**
     * Sets the value of the commonLang property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonLang }
     *     
     */
    public void setCommonLang(CommonLang value) {
        this.commonLang = value;
    }

}
