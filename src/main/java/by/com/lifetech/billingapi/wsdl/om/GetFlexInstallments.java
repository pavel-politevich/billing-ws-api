
package by.com.lifetech.billingapi.wsdl.om;

import by.com.lifetech.billingapi.wsdl.om.ws.in.SearchKeyGeneralRequest;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getFlexInstallments complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getFlexInstallments">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="searchKeyGeneralRequest" type="{astelit.ukr:om:ws:in}searchKeyGeneralRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getFlexInstallments", propOrder = {
    "searchKeyGeneralRequest"
})
public class GetFlexInstallments {

    protected SearchKeyGeneralRequest searchKeyGeneralRequest;

    /**
     * Gets the value of the searchKeyGeneralRequest property.
     * 
     * @return
     *     possible object is
     *     {@link SearchKeyGeneralRequest }
     *     
     */
    public SearchKeyGeneralRequest getSearchKeyGeneralRequest() {
        return searchKeyGeneralRequest;
    }

    /**
     * Sets the value of the searchKeyGeneralRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchKeyGeneralRequest }
     *     
     */
    public void setSearchKeyGeneralRequest(SearchKeyGeneralRequest value) {
        this.searchKeyGeneralRequest = value;
    }

}
