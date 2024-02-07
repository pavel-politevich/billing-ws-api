
package by.com.lifetech.billingapi.wsdl.om;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bundledProductOffering complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bundledProductOffering">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productSpecCharUse" type="{astelit.ukr:om}productSpecCharUse" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="distributionChannels" type="{astelit.ukr:om}distributionChannel" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marketSegment" type="{astelit.ukr:om}atomicMarketSegment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="productSpecification" type="{astelit.ukr:om}atomicProductSpecification" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bundledProductOffering", propOrder = {
    "productSpecCharUse",
    "description",
    "distributionChannels",
    "id",
    "marketSegment",
    "productSpecification",
    "status"
})
public class BundledProductOffering {

    protected List<ProductSpecCharUse> productSpecCharUse;
    protected String description;
    @XmlElement(nillable = true)
    protected List<DistributionChannel> distributionChannels;
    protected String id;
    protected List<AtomicMarketSegment> marketSegment;
    protected AtomicProductSpecification productSpecification;
    protected String status;

    /**
     * Gets the value of the productSpecCharUse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productSpecCharUse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductSpecCharUse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductSpecCharUse }
     * 
     * 
     */
    public List<ProductSpecCharUse> getProductSpecCharUse() {
        if (productSpecCharUse == null) {
            productSpecCharUse = new ArrayList<ProductSpecCharUse>();
        }
        return this.productSpecCharUse;
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
     * Gets the value of the distributionChannels property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the distributionChannels property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDistributionChannels().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DistributionChannel }
     * 
     * 
     */
    public List<DistributionChannel> getDistributionChannels() {
        if (distributionChannels == null) {
            distributionChannels = new ArrayList<DistributionChannel>();
        }
        return this.distributionChannels;
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
     * Gets the value of the marketSegment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the marketSegment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarketSegment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AtomicMarketSegment }
     * 
     * 
     */
    public List<AtomicMarketSegment> getMarketSegment() {
        if (marketSegment == null) {
            marketSegment = new ArrayList<AtomicMarketSegment>();
        }
        return this.marketSegment;
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
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
