
package by.com.lifetech.billingapi.wsdl.om.ws.result;

import java.util.ArrayList;
import java.util.List;

import by.com.lifetech.billingapi.wsdl.om.FlexibleInstallment;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for flexInstallmentResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="flexInstallmentResult">
 *   &lt;complexContent>
 *     &lt;extension base="{astelit.ukr:om:ws:result}generalResult">
 *       &lt;sequence>
 *         &lt;element name="flexibleInstallments" type="{astelit.ukr:om}flexibleInstallment" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flexInstallmentResult", propOrder = {
    "flexibleInstallments"
})
public class FlexInstallmentResult
    extends GeneralResult
{

    @XmlElement(nillable = true)
    protected List<FlexibleInstallment> flexibleInstallments;

    /**
     * Gets the value of the flexibleInstallments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flexibleInstallments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlexibleInstallments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlexibleInstallment }
     * 
     * 
     */
    public List<FlexibleInstallment> getFlexibleInstallments() {
        if (flexibleInstallments == null) {
            flexibleInstallments = new ArrayList<FlexibleInstallment>();
        }
        return this.flexibleInstallments;
    }

}
