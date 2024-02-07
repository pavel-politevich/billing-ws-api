
package by.com.lifetech.billingapi.wsdl.om;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for productComponent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productComponent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productSpecification" type="{astelit.ukr:om}atomicProductSpecification" minOccurs="0"/>
 *         &lt;element name="productOffering" type="{astelit.ukr:om}simpleProductOffering" minOccurs="0"/>
 *         &lt;element name="productCharacteristicValue" type="{astelit.ukr:om}productCharacteristicValue" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productComponent", propOrder = {
    "name",
    "description",
    "productSerialNumber",
    "productStatus",
    "productSpecification",
    "productOffering",
    "productCharacteristicValue"
})
public class ProductComponent {

    protected String name;
    protected String description;
    protected String productSerialNumber;
    protected String productStatus;
    protected AtomicProductSpecification productSpecification;
    protected SimpleProductOffering productOffering;
    protected List<ProductCharacteristicValue> productCharacteristicValue;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the productSerialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductSerialNumber() {
        return productSerialNumber;
    }

    /**
     * Sets the value of the productSerialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductSerialNumber(String value) {
        this.productSerialNumber = value;
    }

    /**
     * Gets the value of the productStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductStatus() {
        return productStatus;
    }

    /**
     * Sets the value of the productStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductStatus(String value) {
        this.productStatus = value;
    }

    /**
     * Gets the value of the productSpecification property.
     * 
     * @return
     *     possible object is
     *     {@link AtomicProductSpecification }
     *     
     */
    public AtomicProductSpecification getProductSpecification() {
        return productSpecification;
    }

    /**
     * Sets the value of the productSpecification property.
     * 
     * @param value
     *     allowed object is
     *     {@link AtomicProductSpecification }
     *     
     */
    public void setProductSpecification(AtomicProductSpecification value) {
        this.productSpecification = value;
    }

    /**
     * Gets the value of the productOffering property.
     * 
     * @return
     *     possible object is
     *     {@link SimpleProductOffering }
     *     
     */
    public SimpleProductOffering getProductOffering() {
        return productOffering;
    }

    /**
     * Sets the value of the productOffering property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimpleProductOffering }
     *     
     */
    public void setProductOffering(SimpleProductOffering value) {
        this.productOffering = value;
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

}
