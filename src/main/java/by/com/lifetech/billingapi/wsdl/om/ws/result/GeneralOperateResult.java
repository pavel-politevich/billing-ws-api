
package by.com.lifetech.billingapi.wsdl.om.ws.result;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for generalOperateResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="generalOperateResult">
 *   &lt;complexContent>
 *     &lt;extension base="{astelit.ukr:om:ws:result}generalResult">
 *       &lt;sequence>
 *         &lt;element name="chainResults" type="{astelit.ukr:om:ws:result}chainExecutionResult" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="chainName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "generalOperateResult", propOrder = {
    "chainResults",
    "chainName"
})
public class GeneralOperateResult
    extends GeneralResult
{

    @XmlElement(nillable = true)
    protected List<ChainExecutionResult> chainResults;
    protected String chainName;

    /**
     * Gets the value of the chainResults property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chainResults property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChainResults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChainExecutionResult }
     * 
     * 
     */
    public List<ChainExecutionResult> getChainResults() {
        if (chainResults == null) {
            chainResults = new ArrayList<ChainExecutionResult>();
        }
        return this.chainResults;
    }

    /**
     * Gets the value of the chainName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChainName() {
        return chainName;
    }

    /**
     * Sets the value of the chainName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChainName(String value) {
        this.chainName = value;
    }

}
