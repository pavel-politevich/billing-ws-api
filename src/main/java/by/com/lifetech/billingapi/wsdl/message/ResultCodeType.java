
package by.com.lifetech.billingapi.wsdl.message;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for resultCodeType</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * <pre>{@code
 * <simpleType name="resultCodeType">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="SUCCESS"/>
 *     <enumeration value="VALIDATION_FAILURE"/>
 *     <enumeration value="CHAIN_ERROR"/>
 *     <enumeration value="ERROR"/>
 *     <enumeration value="CHAIN_VALIDATION_FAILURE"/>
 *     <enumeration value="CHAIN_RUNTIME_ERROR"/>
 *     <enumeration value="CHAIN_ILLEGAL_STATE"/>
 *     <enumeration value="CHAIN_DATABASE_ERROR"/>
 *     <enumeration value="CHAIN_TRANSACTION_ERROR"/>
 *     <enumeration value="CHAIN_BUSINESS_ERROR"/>
 *     <enumeration value="CHAIN_SEMAPHORE_BUSY_ERROR"/>
 *     <enumeration value="CHAIN_SEMAPHORE_MAX_COUNT_REACHED_ERROR"/>
 *     <enumeration value="CHAIN_UNKNOWN_ERROR"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "resultCodeType", namespace = "http://api.ws.rtn.idr.astelit.ukr/")
@XmlEnum
public enum ResultCodeType {

    SUCCESS,
    VALIDATION_FAILURE,
    CHAIN_ERROR,
    ERROR,
    CHAIN_VALIDATION_FAILURE,
    CHAIN_RUNTIME_ERROR,
    CHAIN_ILLEGAL_STATE,
    CHAIN_DATABASE_ERROR,
    CHAIN_TRANSACTION_ERROR,
    CHAIN_BUSINESS_ERROR,
    CHAIN_SEMAPHORE_BUSY_ERROR,
    CHAIN_SEMAPHORE_MAX_COUNT_REACHED_ERROR,
    CHAIN_UNKNOWN_ERROR;

    public String value() {
        return name();
    }

    public static ResultCodeType fromValue(String v) {
        return valueOf(v);
    }

}
