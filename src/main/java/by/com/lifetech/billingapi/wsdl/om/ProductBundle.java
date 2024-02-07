
package by.com.lifetech.billingapi.wsdl.om;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for productBundle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productBundle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bundledProductOffering" type="{astelit.ukr:om}bundledProductOffering" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="product" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="productOffering" type="{astelit.ukr:om}simpleProductOffering" minOccurs="0"/>
 *         &lt;element name="productSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productSpecification" type="{astelit.ukr:om}atomicProductSpecification" minOccurs="0"/>
 *         &lt;element name="productStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "productBundle", propOrder = {
    "bundledProductOffering",
    "description",
    "name",
    "product",
    "productOffering",
    "productSerialNumber",
    "productSpecification",
    "productStatus",
    "productCharacteristicValue"
})
public class ProductBundle {

    protected BundledProductOffering bundledProductOffering;
    protected String description;
    protected String name;
    protected Object product;
    protected SimpleProductOffering productOffering;
    protected String productSerialNumber;
    protected AtomicProductSpecification productSpecification;
    protected String productStatus;
    protected List<ProductCharacteristicValue> productCharacteristicValue;

    /**
     * Gets the value of the bundledProductOffering property.
     * 
     * @return
     *     possible object is
     *     {@link BundledProductOffering }
     *     
     */
    public BundledProductOffering getBundledProductOffering() {
        return bundledProductOffering;
    }

    /**
     * Sets the value of the bundledProductOffering property.
     * 
     * @param value
     *     allowed object is
     *     {@link BundledProductOffering }
     *     
     */
    public void setBundledProductOffering(BundledProductOffering value) {
        this.bundledProductOffering = value;
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
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setProduct(Object value) {
        this.product = value;
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
