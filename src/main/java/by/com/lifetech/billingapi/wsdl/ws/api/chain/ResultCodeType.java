
package by.com.lifetech.billingapi.wsdl.ws.api.chain;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for resultCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="resultCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="VALIDATION_FAILURE"/>
 *     &lt;enumeration value="CHAIN_ERROR"/>
 *     &lt;enumeration value="ERROR"/>
 *     &lt;enumeration value="CHAIN_VALIDATION_FAILURE"/>
 *     &lt;enumeration value="CHAIN_RUNTIME_ERROR"/>
 *     &lt;enumeration value="CHAIN_ILLEGAL_STATE"/>
 *     &lt;enumeration value="CHAIN_DATABASE_ERROR"/>
 *     &lt;enumeration value="CHAIN_TRANSACTION_ERROR"/>
 *     &lt;enumeration value="CHAIN_BUSINESS_ERROR"/>
 *     &lt;enumeration value="CHAIN_SEMAPHORE_BUSY_ERROR"/>
 *     &lt;enumeration value="CHAIN_SEMAPHORE_MAX_COUNT_REACHED_ERROR"/>
 *     &lt;enumeration value="CHAIN_UNKNOWN_ERROR"/>
 *     &lt;enumeration value="PROMO_ILLEGAL_STATE"/>
 *     &lt;enumeration value="PROMO_INTERNAL_ERROR"/>
 *     &lt;enumeration value="NOTHING_TO_SHOW"/>
 *     &lt;enumeration value="PROMO_BUSINESS_ERROR"/>
 *     &lt;enumeration value="ILLEGAL_STATE"/>
 *     &lt;enumeration value="PERMIT"/>
 *     &lt;enumeration value="FORBID_QUOTA"/>
 *     &lt;enumeration value="FORBID_FILTER"/>
 *     &lt;enumeration value="ERROR_ALREADY_ROLLED_BACK"/>
 *     &lt;enumeration value="RULE_DISABLED"/>
 *     &lt;enumeration value="RULE_NOT_FOUND"/>
 *     &lt;enumeration value="QUOTA_NOT_FOUND"/>
 *     &lt;enumeration value="ERROR_REVERT_NOTHING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "resultCodeType")
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
    CHAIN_UNKNOWN_ERROR,
    PROMO_ILLEGAL_STATE,
    PROMO_INTERNAL_ERROR,
    NOTHING_TO_SHOW,
    PROMO_BUSINESS_ERROR,
    ILLEGAL_STATE,
    PERMIT,
    FORBID_QUOTA,
    FORBID_FILTER,
    ERROR_ALREADY_ROLLED_BACK,
    RULE_DISABLED,
    RULE_NOT_FOUND,
    QUOTA_NOT_FOUND,
    ERROR_REVERT_NOTHING;

    public String value() {
        return name();
    }

    public static ResultCodeType fromValue(String v) {
        return valueOf(v);
    }

}
