
package by.com.lifetech.billingapi.wsdl.om;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for chainVar complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chainVar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="varName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="varValue" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chainVar", propOrder = {
    "varName",
    "varValue"
})
public class ChainVar {

    protected String varName;
    protected Object varValue;

    /**
     * Gets the value of the varName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVarName() {
        return varName;
    }

    /**
     * Sets the value of the varName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVarName(String value) {
        this.varName = value;
    }

    /**
     * Gets the value of the varValue property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getVarValue() {
        return varValue;
    }

    /**
     * Sets the value of the varValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setVarValue(Object value) {
        this.varValue = value;
    }

}
