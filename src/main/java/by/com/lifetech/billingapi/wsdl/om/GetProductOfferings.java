
package by.com.lifetech.billingapi.wsdl.om;

import by.com.lifetech.billingapi.wsdl.om.ws.in.GeneralRequest;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getProductOfferings complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getProductOfferings">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getProductOfferingsRequest" type="{astelit.ukr:om:ws:in}generalRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProductOfferings", propOrder = {
    "getProductOfferingsRequest"
})
public class GetProductOfferings {

    protected GeneralRequest getProductOfferingsRequest;

    /**
     * Gets the value of the getProductOfferingsRequest property.
     * 
     * @return
     *     possible object is
     *     {@link GeneralRequest }
     *     
     */
    public GeneralRequest getGetProductOfferingsRequest() {
        return getProductOfferingsRequest;
    }

    /**
     * Sets the value of the getProductOfferingsRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeneralRequest }
     *     
     */
    public void setGetProductOfferingsRequest(GeneralRequest value) {
        this.getProductOfferingsRequest = value;
    }

}
