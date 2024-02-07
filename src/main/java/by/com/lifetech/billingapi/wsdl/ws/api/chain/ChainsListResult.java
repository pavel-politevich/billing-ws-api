
package by.com.lifetech.billingapi.wsdl.ws.api.chain;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for chainsListResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chainsListResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://chain.api.ws.rtn.idr.astelit.ukr/}generalResult">
 *       &lt;sequence>
 *         &lt;element name="resultSet" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chainsListResult", propOrder = {
    "resultSet"
})
public class ChainsListResult
    extends GeneralResult
{

    @XmlElement(nillable = true)
    protected List<String> resultSet;

    /**
     * Gets the value of the resultSet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultSet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultSet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getResultSet() {
        if (resultSet == null) {
            resultSet = new ArrayList<String>();
        }
        return this.resultSet;
    }

}
