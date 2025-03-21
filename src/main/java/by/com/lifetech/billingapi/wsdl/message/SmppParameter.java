
package by.com.lifetech.billingapi.wsdl.message;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for smppParameter complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="smppParameter">
 *   <complexContent>
 *     <extension base="{http://astelit.ukr/idr/sms/message/}controlParameter">
 *       <sequence>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "smppParameter")
@XmlSeeAlso({
    SmppOptionalParameter.class,
    SmppMandatoryParameter.class
})
public abstract class SmppParameter
    extends ControlParameter
{


}
