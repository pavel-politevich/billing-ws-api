
package by.com.lifetech.billingapi.wsdl.ws.api.chain;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for chainSignatureResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chainSignatureResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://chain.api.ws.rtn.idr.astelit.ukr/}generalResult">
 *       &lt;sequence>
 *         &lt;element name="inputParameters" type="{http://chain.api.ws.rtn.idr.astelit.ukr/}chainVariableDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="outputParameters" type="{http://chain.api.ws.rtn.idr.astelit.ukr/}chainVariableDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chainSignatureResult", propOrder = {
    "inputParameters",
    "outputParameters"
})
public class ChainSignatureResult
    extends GeneralResult
{

    @XmlElement(nillable = true)
    protected List<ChainVariableDTO> inputParameters;
    @XmlElement(nillable = true)
    protected List<ChainVariableDTO> outputParameters;

    /**
     * Gets the value of the inputParameters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inputParameters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInputParameters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChainVariableDTO }
     * 
     * 
     */
    public List<ChainVariableDTO> getInputParameters() {
        if (inputParameters == null) {
            inputParameters = new ArrayList<ChainVariableDTO>();
        }
        return this.inputParameters;
    }

    /**
     * Gets the value of the outputParameters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the outputParameters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutputParameters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChainVariableDTO }
     * 
     * 
     */
    public List<ChainVariableDTO> getOutputParameters() {
        if (outputParameters == null) {
            outputParameters = new ArrayList<ChainVariableDTO>();
        }
        return this.outputParameters;
    }

}
