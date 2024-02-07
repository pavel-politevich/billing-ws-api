
package by.com.lifetech.billingapi.wsdl.om;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prodSpecCharValueUse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prodSpecCharValueUse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productSpecCharacteristicValue" type="{astelit.ukr:om}productSpecCharacteristicValue" minOccurs="0"/>
 *         &lt;element name="productSpecCharUse" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="default" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prodSpecCharValueUse", propOrder = {
    "productSpecCharacteristicValue",
    "productSpecCharUse"
})
public class ProdSpecCharValueUse {

    protected ProductSpecCharacteristicValue productSpecCharacteristicValue;
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object productSpecCharUse;
    @XmlAttribute(name = "id")
    protected Long id;
    @XmlAttribute(name = "default")
    protected Boolean _default;

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
     * Gets the value of the productSpecCharUse property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getProductSpecCharUse() {
        return productSpecCharUse;
    }

    /**
     * Sets the value of the productSpecCharUse property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setProductSpecCharUse(Object value) {
        this.productSpecCharUse = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the default property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDefault() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDefault(Boolean value) {
        this._default = value;
    }

}
