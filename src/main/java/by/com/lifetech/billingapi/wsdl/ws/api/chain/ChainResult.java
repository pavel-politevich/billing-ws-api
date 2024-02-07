
package by.com.lifetech.billingapi.wsdl.ws.api.chain;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for chainResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chainResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://chain.api.ws.rtn.idr.astelit.ukr/}generalResult">
 *       &lt;sequence>
 *         &lt;element name="resultList" type="{http://chain.api.ws.rtn.idr.astelit.ukr/}chainResultElement" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="businessError" type="{http://chain.api.ws.rtn.idr.astelit.ukr/}businessError" minOccurs="0"/>
 *         &lt;element name="nodeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serverInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chainVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="debugLog" type="{http://chain.api.ws.rtn.idr.astelit.ukr/}logEntry" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chainResult", propOrder = {
    "resultList",
    "businessError",
    "nodeName",
    "serverInfo",
    "chainVersion",
    "debugLog"
})
public class ChainResult
    extends GeneralResult
{

    @XmlElement(nillable = true)
    protected List<ChainResultElement> resultList;
    protected BusinessError businessError;
    protected String nodeName;
    protected String serverInfo;
    protected String chainVersion;
    @XmlElement(nillable = true)
    protected List<LogEntry> debugLog;

    /**
     * Gets the value of the resultList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChainResultElement }
     * 
     * 
     */
    public List<ChainResultElement> getResultList() {
        if (resultList == null) {
            resultList = new ArrayList<ChainResultElement>();
        }
        return this.resultList;
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

    /**
     * Gets the value of the nodeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * Sets the value of the nodeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeName(String value) {
        this.nodeName = value;
    }

    /**
     * Gets the value of the serverInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerInfo() {
        return serverInfo;
    }

    /**
     * Sets the value of the serverInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerInfo(String value) {
        this.serverInfo = value;
    }

    /**
     * Gets the value of the chainVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChainVersion() {
        return chainVersion;
    }

    /**
     * Sets the value of the chainVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChainVersion(String value) {
        this.chainVersion = value;
    }

    /**
     * Gets the value of the debugLog property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the debugLog property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDebugLog().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LogEntry }
     * 
     * 
     */
    public List<LogEntry> getDebugLog() {
        if (debugLog == null) {
            debugLog = new ArrayList<LogEntry>();
        }
        return this.debugLog;
    }

}
