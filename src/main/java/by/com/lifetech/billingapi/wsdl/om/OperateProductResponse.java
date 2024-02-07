
package by.com.lifetech.billingapi.wsdl.om;

import by.com.lifetech.billingapi.wsdl.om.ws.result.GeneralOperateResult;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for operateProductResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="operateProductResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="response" type="{astelit.ukr:om:ws:result}generalOperateResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "operateProductResponse", propOrder = {
    "response"
})
public class OperateProductResponse {

    protected GeneralOperateResult response;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link GeneralOperateResult }
     *     
     */
    public GeneralOperateResult getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeneralOperateResult }
     *     
     */
    public void setResponse(GeneralOperateResult value) {
        this.response = value;
    }

}
