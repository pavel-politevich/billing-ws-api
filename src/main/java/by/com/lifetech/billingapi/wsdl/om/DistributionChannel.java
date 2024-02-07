
package by.com.lifetech.billingapi.wsdl.om;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for distributionChannel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="distributionChannel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="channelSpecification" type="{astelit.ukr:om}distributionChannelSpecification" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "distributionChannel", propOrder = {
    "name",
    "id",
    "uid",
    "channelSpecification"
})
public class DistributionChannel {

    protected String name;
    protected String id;
    protected Long uid;
    protected DistributionChannelSpecification channelSpecification;

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
     * Gets the value of the channelSpecification property.
     * 
     * @return
     *     possible object is
     *     {@link DistributionChannelSpecification }
     *     
     */
    public DistributionChannelSpecification getChannelSpecification() {
        return channelSpecification;
    }

    /**
     * Sets the value of the channelSpecification property.
     * 
     * @param value
     *     allowed object is
     *     {@link DistributionChannelSpecification }
     *     
     */
    public void setChannelSpecification(DistributionChannelSpecification value) {
        this.channelSpecification = value;
    }

}
