
package by.com.lifetech.billingapi.wsdl.om;

import by.com.lifetech.billingapi.wsdl.om.ws.in.OperateProductRequest;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for operateProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="operateProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operateProductRequest" type="{astelit.ukr:om:ws:in}operateProductRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "operateProduct", propOrder = {
    "operateProductRequest"
})
public class OperateProduct {

    protected OperateProductRequest operateProductRequest;

    /**
     * Gets the value of the operateProductRequest property.
     * 
     * @return
     *     possible object is
     *     {@link OperateProductRequest }
     *     
     */
    public OperateProductRequest getOperateProductRequest() {
        return operateProductRequest;
    }

    /**
     * Sets the value of the operateProductRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperateProductRequest }
     *     
     */
    public void setOperateProductRequest(OperateProductRequest value) {
        this.operateProductRequest = value;
    }

}
