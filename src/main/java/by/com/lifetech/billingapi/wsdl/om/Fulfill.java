
package by.com.lifetech.billingapi.wsdl.om;

import by.com.lifetech.billingapi.wsdl.om.ws.in.FulfillRequest;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fulfill complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fulfill">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fulfillRequest" type="{astelit.ukr:om:ws:in}fulfillRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fulfill", propOrder = {
    "fulfillRequest"
})
public class Fulfill {

    protected FulfillRequest fulfillRequest;

    /**
     * Gets the value of the fulfillRequest property.
     * 
     * @return
     *     possible object is
     *     {@link FulfillRequest }
     *     
     */
    public FulfillRequest getFulfillRequest() {
        return fulfillRequest;
    }

    /**
     * Sets the value of the fulfillRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link FulfillRequest }
     *     
     */
    public void setFulfillRequest(FulfillRequest value) {
        this.fulfillRequest = value;
    }

}
