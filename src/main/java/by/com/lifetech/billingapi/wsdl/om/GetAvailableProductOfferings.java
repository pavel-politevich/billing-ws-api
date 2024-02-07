
package by.com.lifetech.billingapi.wsdl.om;

import by.com.lifetech.billingapi.wsdl.om.ws.in.FulfillRequest;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAvailableProductOfferings complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAvailableProductOfferings">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetAvailableProductOfferingsRequest" type="{astelit.ukr:om:ws:in}fulfillRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAvailableProductOfferings", propOrder = {
    "getAvailableProductOfferingsRequest"
})
public class GetAvailableProductOfferings {

    @XmlElement(name = "GetAvailableProductOfferingsRequest")
    protected FulfillRequest getAvailableProductOfferingsRequest;

    /**
     * Gets the value of the getAvailableProductOfferingsRequest property.
     * 
     * @return
     *     possible object is
     *     {@link FulfillRequest }
     *     
     */
    public FulfillRequest getGetAvailableProductOfferingsRequest() {
        return getAvailableProductOfferingsRequest;
    }

    /**
     * Sets the value of the getAvailableProductOfferingsRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link FulfillRequest }
     *     
     */
    public void setGetAvailableProductOfferingsRequest(FulfillRequest value) {
        this.getAvailableProductOfferingsRequest = value;
    }

}
