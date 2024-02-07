
package by.com.lifetech.billingapi.wsdl.om.ws.result;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for resultCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="resultCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="INTERNAL_ERROR"/>
 *     &lt;enumeration value="CHAIN_ERROR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "resultCode")
@XmlEnum
public enum ResultCode {

    SUCCESS,
    INTERNAL_ERROR,
    CHAIN_ERROR;

    public String value() {
        return name();
    }

    public static ResultCode fromValue(String v) {
        return valueOf(v);
    }

}
