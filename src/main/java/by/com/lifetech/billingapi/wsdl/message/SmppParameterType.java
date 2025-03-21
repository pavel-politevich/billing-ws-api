
package by.com.lifetech.billingapi.wsdl.message;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for smppParameterType</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * <pre>{@code
 * <simpleType name="smppParameterType">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="INT"/>
 *     <enumeration value="STRING"/>
 *     <enumeration value="BYTE"/>
 *     <enumeration value="SHORT"/>
 *     <enumeration value="NULL"/>
 *     <enumeration value="OCTETSTRING"/>
 *     <enumeration value="COCTETSTRING"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "smppParameterType")
@XmlEnum
public enum SmppParameterType {

    INT,
    STRING,
    BYTE,
    SHORT,
    NULL,
    OCTETSTRING,
    COCTETSTRING;

    public String value() {
        return name();
    }

    public static SmppParameterType fromValue(String v) {
        return valueOf(v);
    }

}
