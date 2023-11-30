
package by.com.lifetech.billingapi.wsdl;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for executeDebugChain complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="executeDebugChain"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="chainRequest" type="{http://chain.api.ws.rtn.idr.astelit.ukr/}chainRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "executeDebugChain", propOrder = {
    "chainRequest"
})
public class ExecuteDebugChain {

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
