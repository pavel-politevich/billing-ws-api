
package by.com.lifetech.billingapi.wsdl.om;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for productCharacteristicValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productCharacteristicValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productSpecCharacteristic" type="{astelit.ukr:om}productSpecCharacteristic" minOccurs="0"/>
 *         &lt;element name="productSpecCharacteristicValue" type="{astelit.ukr:om}productSpecCharacteristicValue" minOccurs="0"/>
 *         &lt;element name="productCharacteristicValue" type="{astelit.ukr:om}productCharacteristicValue" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productCharacteristicValue", propOrder = {
    "productSpecCharacteristic",
    "productSpecCharacteristicValue",
    "productCharacteristicValue",
    "value"
})
public class ProductCharacteristicValue {

    protected ProductSpecCharacteristic productSpecCharacteristic;
    protected ProductSpecCharacteristicValue productSpecCharacteristicValue;
    protected List<ProductCharacteristicValue> productCharacteristicValue;
    protected String value;

    /**
     * Gets the value of the productSpecCharacteristic property.
     * 
     * @return
     *     possible object is
     *     {@link ProductSpecCharacteristic }
     *     
     */
    public ProductSpecCharacteristic getProductSpecCharacteristic() {
        return productSpecCharacteristic;
    }

    /**
     * Sets the value of the productSpecCharacteristic property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductSpecCharacteristic }
     *     
     */
    public void setProductSpecCharacteristic(ProductSpecCharacteristic value) {
        this.productSpecCharacteristic = value;
    }

    /**
     * Gets the value of the productSpecCharacteristicValue property.
     * 
     * @return
     *     possible object is
     *     {@link ProductSpecCharacteristicValue }
     *     
     */
    public ProductSpecCharacteristicValue getProductSpecCharacteristicValue() {
        return productSpecCharacteristicValue;
    }

    /**
     * Sets the value of the productSpecCharacteristicValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductSpecCharacteristicValue }
     *     
     */
    public void setProductSpecCharacteristicValue(ProductSpecCharacteristicValue value) {
        this.productSpecCharacteristicValue = value;
    }

    /**
     * Gets the value of the productCharacteristicValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productCharacteristicValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductCharacteristicValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductCharacteristicValue }
     * 
     * 
     */
    public List<ProductCharacteristicValue> getProductCharacteristicValue() {
        if (productCharacteristicValue == null) {
            productCharacteristicValue = new ArrayList<ProductCharacteristicValue>();
        }
        return this.productCharacteristicValue;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

}
