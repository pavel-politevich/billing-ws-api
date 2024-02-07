
package by.com.lifetech.billingapi.wsdl.om;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for productSpecCharUse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productSpecCharUse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="package" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="minCardinality" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="maxCardinality" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="productSpecCharacteristic" type="{astelit.ukr:om}productSpecCharacteristic" minOccurs="0"/>
 *         &lt;element name="prodSpecCharValueUse" type="{astelit.ukr:om}prodSpecCharValueUse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="canBeOveridden" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="extensible" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="unique" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productSpecCharUse", propOrder = {
    "name",
    "description",
    "_package",
    "minCardinality",
    "maxCardinality",
    "productSpecCharacteristic",
    "prodSpecCharValueUse"
})
public class ProductSpecCharUse {

    protected String name;
    protected String description;
    @XmlElement(name = "package")
    protected Boolean _package;
    protected Integer minCardinality;
    protected Integer maxCardinality;
    protected ProductSpecCharacteristic productSpecCharacteristic;
    protected List<ProdSpecCharValueUse> prodSpecCharValueUse;
    @XmlAttribute(name = "canBeOveridden")
    protected Boolean canBeOveridden;
    @XmlAttribute(name = "extensible")
    protected Boolean extensible;
    @XmlAttribute(name = "unique")
    protected String unique;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

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
     * Gets the value of the package property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPackage() {
        return _package;
    }

    /**
     * Sets the value of the package property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPackage(Boolean value) {
        this._package = value;
    }

    /**
     * Gets the value of the minCardinality property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinCardinality() {
        return minCardinality;
    }

    /**
     * Sets the value of the minCardinality property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinCardinality(Integer value) {
        this.minCardinality = value;
    }

    /**
     * Gets the value of the maxCardinality property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxCardinality() {
        return maxCardinality;
    }

    /**
     * Sets the value of the maxCardinality property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxCardinality(Integer value) {
        this.maxCardinality = value;
    }

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
     * Gets the value of the prodSpecCharValueUse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prodSpecCharValueUse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProdSpecCharValueUse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProdSpecCharValueUse }
     * 
     * 
     */
    public List<ProdSpecCharValueUse> getProdSpecCharValueUse() {
        if (prodSpecCharValueUse == null) {
            prodSpecCharValueUse = new ArrayList<ProdSpecCharValueUse>();
        }
        return this.prodSpecCharValueUse;
    }

    /**
     * Gets the value of the canBeOveridden property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCanBeOveridden() {
        return canBeOveridden;
    }

    /**
     * Sets the value of the canBeOveridden property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCanBeOveridden(Boolean value) {
        this.canBeOveridden = value;
    }

    /**
     * Gets the value of the extensible property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExtensible() {
        return extensible;
    }

    /**
     * Sets the value of the extensible property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExtensible(Boolean value) {
        this.extensible = value;
    }

    /**
     * Gets the value of the unique property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnique() {
        return unique;
    }

    /**
     * Sets the value of the unique property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnique(String value) {
        this.unique = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
