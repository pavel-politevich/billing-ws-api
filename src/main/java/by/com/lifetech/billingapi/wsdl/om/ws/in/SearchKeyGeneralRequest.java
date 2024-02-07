
package by.com.lifetech.billingapi.wsdl.om.ws.in;

import by.com.lifetech.billingapi.wsdl.om.SearchKey;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for searchKeyGeneralRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="searchKeyGeneralRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{astelit.ukr:om:ws:in}generalRequest">
 *       &lt;sequence>
 *         &lt;element name="searchKey" type="{astelit.ukr:om}searchKey" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchKeyGeneralRequest", propOrder = {
    "searchKey"
})
@XmlSeeAlso({
    GetProductsRequest.class,
    OperateProductRequest.class,
    FulfillRequest.class
})
public class SearchKeyGeneralRequest
    extends GeneralRequest
{

    protected SearchKey searchKey;

    /**
     * Gets the value of the searchKey property.
     * 
     * @return
     *     possible object is
     *     {@link SearchKey }
     *     
     */
    public SearchKey getSearchKey() {
        return searchKey;
    }

    /**
     * Sets the value of the searchKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchKey }
     *     
     */
    public void setSearchKey(SearchKey value) {
        this.searchKey = value;
    }

}
