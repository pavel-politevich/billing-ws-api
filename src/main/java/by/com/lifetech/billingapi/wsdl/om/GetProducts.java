
package by.com.lifetech.billingapi.wsdl.om;

import by.com.lifetech.billingapi.wsdl.om.ws.in.GetProductsRequest;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getProducts complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getProducts">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getProductsRequest" type="{astelit.ukr:om:ws:in}getProductsRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProducts", propOrder = {
    "getProductsRequest"
})
public class GetProducts {

    protected GetProductsRequest getProductsRequest;

    /**
     * Gets the value of the getProductsRequest property.
     * 
     * @return
     *     possible object is
     *     {@link GetProductsRequest }
     *     
     */
    public GetProductsRequest getGetProductsRequest() {
        return getProductsRequest;
    }

    /**
     * Sets the value of the getProductsRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetProductsRequest }
     *     
     */
    public void setGetProductsRequest(GetProductsRequest value) {
        this.getProductsRequest = value;
    }

}
