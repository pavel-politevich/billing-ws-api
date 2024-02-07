
package by.com.lifetech.billingapi.wsdl.om.ws.in;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fulfillRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fulfillRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{astelit.ukr:om:ws:in}searchKeyGeneralRequest">
 *       &lt;sequence>
 *         &lt;element name="product" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fulfillRequest", propOrder = {
    "product"
})
public class FulfillRequest
    extends SearchKeyGeneralRequest
{

    protected Object product;

    /**
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setProduct(Object value) {
        this.product = value;
    }

}
