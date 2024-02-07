
package by.com.lifetech.billingapi.wsdl.om;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for atomicMarketSegment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="atomicMarketSegment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validFor" type="{astelit.ukr:om}timePeriod" minOccurs="0"/>
 *         &lt;element name="marketSegmentCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marketSegmentSubCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marketSegmentCharacteristics" type="{astelit.ukr:om}atomicMarketSegmentCharacteristic" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="marketSegmentCharacteristicValues" type="{astelit.ukr:om}marketSegmentCharacteristicValue" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "atomicMarketSegment", propOrder = {
    "name",
    "description",
    "validFor",
    "marketSegmentCategory",
    "marketSegmentSubCategory",
    "marketSegmentCharacteristics",
    "marketSegmentCharacteristicValues"
})
public class AtomicMarketSegment {

    protected String name;
    protected String description;
    protected TimePeriod validFor;
    protected String marketSegmentCategory;
    protected String marketSegmentSubCategory;
    protected List<AtomicMarketSegmentCharacteristic> marketSegmentCharacteristics;
    protected List<MarketSegmentCharacteristicValue> marketSegmentCharacteristicValues;
    @XmlAttribute(name = "id")
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
     * Gets the value of the marketSegmentCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketSegmentCategory() {
        return marketSegmentCategory;
    }

    /**
     * Sets the value of the marketSegmentCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketSegmentCategory(String value) {
        this.marketSegmentCategory = value;
    }

    /**
     * Gets the value of the marketSegmentSubCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketSegmentSubCategory() {
        return marketSegmentSubCategory;
    }

    /**
     * Sets the value of the marketSegmentSubCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketSegmentSubCategory(String value) {
        this.marketSegmentSubCategory = value;
    }

    /**
     * Gets the value of the marketSegmentCharacteristics property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the marketSegmentCharacteristics property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarketSegmentCharacteristics().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AtomicMarketSegmentCharacteristic }
     * 
     * 
     */
    public List<AtomicMarketSegmentCharacteristic> getMarketSegmentCharacteristics() {
        if (marketSegmentCharacteristics == null) {
            marketSegmentCharacteristics = new ArrayList<AtomicMarketSegmentCharacteristic>();
        }
        return this.marketSegmentCharacteristics;
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

}
