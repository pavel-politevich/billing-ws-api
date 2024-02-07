
package by.com.lifetech.billingapi.wsdl.om.ws.result;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getProductOfferingsResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getProductOfferingsResult">
 *   &lt;complexContent>
 *     &lt;extension base="{astelit.ukr:om:ws:result}generalResult">
 *       &lt;sequence>
 *         &lt;element name="productOfferingsList" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProductOfferingsResult", propOrder = {
    "productOfferingsList"
})
public class GetProductOfferingsResult
    extends GeneralResult
{

    @XmlElement(nillable = true)
    protected List<Object> productOfferingsList;

    /**
     * Gets the value of the productOfferingsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productOfferingsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductOfferingsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getProductOfferingsList() {
        if (productOfferingsList == null) {
            productOfferingsList = new ArrayList<Object>();
        }
        return this.productOfferingsList;
    }

}
