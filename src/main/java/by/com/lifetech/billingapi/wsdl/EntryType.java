
package by.com.lifetech.billingapi.wsdl;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for entryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="entryType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="START"/&gt;
 *     &lt;enumeration value="STOP"/&gt;
 *     &lt;enumeration value="INFO"/&gt;
 *     &lt;enumeration value="ERROR"/&gt;
 *     &lt;enumeration value="WARN"/&gt;
 *     &lt;enumeration value="NODE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "entryType")
@XmlEnum
public enum EntryType {

    START,
    STOP,
    INFO,
    ERROR,
    WARN,
    NODE;

    public String value() {
        return name();
    }

    public static EntryType fromValue(String v) {
        return valueOf(v);
    }

}
