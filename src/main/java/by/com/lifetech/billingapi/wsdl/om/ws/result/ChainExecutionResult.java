
package by.com.lifetech.billingapi.wsdl.om.ws.result;

import java.util.ArrayList;
import java.util.List;

import by.com.lifetech.billingapi.wsdl.om.ChainVar;
import by.com.lifetech.billingapi.wsdl.ws.api.chain.BusinessError;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for chainExecutionResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chainExecutionResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{astelit.ukr:om:ws:result}resultCode" minOccurs="0"/>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="result" type="{astelit.ukr:om}chainVar" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="chain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessError" type="{http://chain.api.ws.rtn.idr.astelit.ukr/}businessError" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chainExecutionResult", propOrder = {
    "code",
    "error",
    "result",
    "chain",
    "businessError"
})
public class ChainExecutionResult {

    @XmlSchemaType(name = "string")
    protected ResultCode code;
    protected String error;
    @XmlElement(nillable = true)
    protected List<ChainVar> result;
    protected String chain;
    protected BusinessError businessError;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link ResultCode }
     *     
     */
    public ResultCode getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultCode }
     *     
     */
    public void setCode(ResultCode value) {
        this.code = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setError(String value) {
        this.error = value;
    }

    /**
     * Gets the value of the result property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the result property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChainVar }
     * 
     * 
     */
    public List<ChainVar> getResult() {
        if (result == null) {
            result = new ArrayList<ChainVar>();
        }
        return this.result;
    }

    /**
     * Gets the value of the chain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChain() {
        return chain;
    }

    /**
     * Sets the value of the chain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChain(String value) {
        this.chain = value;
    }

    /**
     * Gets the value of the businessError property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessError }
     *     
     */
    public BusinessError getBusinessError() {
        return businessError;
    }

    /**
     * Sets the value of the businessError property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessError }
     *     
     */
    public void setBusinessError(BusinessError value) {
        this.businessError = value;
    }

}
