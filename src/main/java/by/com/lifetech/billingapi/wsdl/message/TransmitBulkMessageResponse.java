
package by.com.lifetech.billingapi.wsdl.message;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transmitBulkMessageResponse complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="transmitBulkMessageResponse">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="result" type="{http://api.ws.rtn.idr.astelit.ukr/}generalResult" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transmitBulkMessageResponse", namespace = "http://api.ws.rtn.idr.astelit.ukr/", propOrder = {
    "result"
})
public class TransmitBulkMessageResponse {

    protected GeneralResult result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link GeneralResult }
     *     
     */
    public GeneralResult getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeneralResult }
     *     
     */
    public void setResult(GeneralResult value) {
        this.result = value;
    }

}
