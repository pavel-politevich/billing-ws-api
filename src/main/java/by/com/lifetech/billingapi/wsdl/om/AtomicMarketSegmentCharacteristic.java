
package by.com.lifetech.billingapi.wsdl.om;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for atomicMarketSegmentCharacteristic complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="atomicMarketSegmentCharacteristic">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="minCardinality" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="maxCardinality" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valueType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="derivationFormula" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validFor" type="{astelit.ukr:om}timePeriod" minOccurs="0"/>
 *         &lt;element name="marketSegmentCharacteristicValues" type="{astelit.ukr:om}marketSegmentCharacteristicValue" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="unique" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="extensible" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "atomicMarketSegmentCharacteristic", propOrder = {
    "uid",
    "name",
    "minCardinality",
    "maxCardinality",
    "description",
    "valueType",
    "derivationFormula",
    "validFor",
    "marketSegmentCharacteristicValues"
})
public class AtomicMarketSegmentCharacteristic {

    protected Long uid;
    protected String name;
    protected Integer minCardinality;
    protected Integer maxCardinality;
    protected String description;
    protected String valueType;
    protected String derivationFormula;
    protected TimePeriod validFor;
    @XmlElement(nillable = true)
    protected List<MarketSegmentCharacteristicValue> marketSegmentCharacteristicValues;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "unique")
    protected String unique;
    @XmlAttribute(name = "extensible")
    protected Boolean extensible;

    /**
     * Gets the value of the uid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUid() {
        return uid;
    }

    /**
     * Sets the value of the uid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUid(Long value) {
        this.uid = value;
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
     * Gets the value of the valueType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueType() {
        return valueType;
    }

    /**
     * Sets the value of the valueType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueType(String value) {
        this.valueType = value;
    }

    /**
     * Gets the value of the derivationFormula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDerivationFormula() {
        return derivationFormula;
    }

    /**
     * Sets the value of the derivationFormula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDerivationFormula(String value) {
        this.derivationFormula = value;
    }

    /**
     * Gets the value of the validFor property.
     * 
     * @return
     *     possible object is
     *     {@link TimePeriod }
     *     
     */
    public TimePeriod getValidFor() {
        return validFor;
    }

    /**
     * Sets the value of the validFor property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePeriod }
     *     
     */
    public void setValidFor(TimePeriod value) {
        this.validFor = value;
    }

    /**
     * Gets the value of the marketSegmentCharacteristicValues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the marketSegmentCharacteristicValues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarketSegmentCharacteristicValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MarketSegmentCharacteristicValue }
     * 
     * 
     */
    public List<MarketSegmentCharacteristicValue> getMarketSegmentCharacteristicValues() {
        if (marketSegmentCharacteristicValues == null) {
            marketSegmentCharacteristicValues = new ArrayList<MarketSegmentCharacteristicValue>();
        }
        return this.marketSegmentCharacteristicValues;
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

}
