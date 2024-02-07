
package by.com.lifetech.billingapi.wsdl.om.ws.in;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getProductsRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getProductsRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{astelit.ukr:om:ws:in}searchKeyGeneralRequest">
 *       &lt;sequence>
 *         &lt;element name="productOfferingId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProductsRequest", propOrder = {
    "productOfferingId"
})
public class GetProductsRequest
    extends SearchKeyGeneralRequest
{

    protected String productOfferingId;

    /**
     * Gets the value of the productOfferingId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductOfferingId() {
        return productOfferingId;
    }

    /**
     * Sets the value of the productOfferingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductOfferingId(String value) {
        this.productOfferingId = value;
    }

}
