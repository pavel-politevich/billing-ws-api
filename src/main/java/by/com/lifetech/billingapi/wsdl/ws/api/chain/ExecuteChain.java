
package by.com.lifetech.billingapi.wsdl.ws.api.chain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for executeChain complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="executeChain">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chainRequest" type="{http://chain.api.ws.rtn.idr.astelit.ukr/}chainRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "executeChain", propOrder = {
    "chainRequest"
})
public class ExecuteChain {

    protected ChainRequest chainRequest;

    /**
     * Gets the value of the chainRequest property.
     * 
     * @return
     *     possible object is
     *     {@link ChainRequest }
     *     
     */
    public ChainRequest getChainRequest() {
        return chainRequest;
    }

    /**
     * Sets the value of the chainRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChainRequest }
     *     
     */
    public void setChainRequest(ChainRequest value) {
        this.chainRequest = value;
    }

}
