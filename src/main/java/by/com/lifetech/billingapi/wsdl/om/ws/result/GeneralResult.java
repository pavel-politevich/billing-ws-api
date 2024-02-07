
package by.com.lifetech.billingapi.wsdl.om.ws.result;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for generalResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="generalResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="commonResult" type="{astelit.ukr:om:ws:result}result" minOccurs="0"/>
 *         &lt;element name="invokeTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="resultByAppenders">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="value" type="{astelit.ukr:om:ws:result}result" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="scenarioError" type="{astelit.ukr:om:ws:result}scenarioErrorResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "generalResult", propOrder = {
    "commonResult",
    "invokeTime",
    "transactionId",
    "resultByAppenders",
    "scenarioError"
})
@XmlSeeAlso({
    GetProductOfferingsResult.class,
    FlexInstallmentResult.class,
    GetProductsResult.class,
    FulfillResult.class,
    GetAvailableProductOfferingsResult.class,
    GeneralOperateResult.class
})
public class GeneralResult {

    protected Result commonResult;
    protected Long invokeTime;
    protected Object transactionId;
    @XmlElement(required = true)
    protected GeneralResult.ResultByAppenders resultByAppenders;
    protected ScenarioErrorResult scenarioError;

    /**
     * Gets the value of the commonResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getCommonResult() {
        return commonResult;
    }

    /**
     * Sets the value of the commonResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setCommonResult(Result value) {
        this.commonResult = value;
    }

    /**
     * Gets the value of the invokeTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInvokeTime() {
        return invokeTime;
    }

    /**
     * Sets the value of the invokeTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInvokeTime(Long value) {
        this.invokeTime = value;
    }

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setTransactionId(Object value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the resultByAppenders property.
     * 
     * @return
     *     possible object is
     *     {@link GeneralResult.ResultByAppenders }
     *     
     */
    public GeneralResult.ResultByAppenders getResultByAppenders() {
        return resultByAppenders;
    }

    /**
     * Sets the value of the resultByAppenders property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeneralResult.ResultByAppenders }
     *     
     */
    public void setResultByAppenders(GeneralResult.ResultByAppenders value) {
        this.resultByAppenders = value;
    }

    /**
     * Gets the value of the scenarioError property.
     * 
     * @return
     *     possible object is
     *     {@link ScenarioErrorResult }
     *     
     */
    public ScenarioErrorResult getScenarioError() {
        return scenarioError;
    }

    /**
     * Sets the value of the scenarioError property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScenarioErrorResult }
     *     
     */
    public void setScenarioError(ScenarioErrorResult value) {
        this.scenarioError = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="value" type="{astelit.ukr:om:ws:result}result" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class ResultByAppenders {

        protected List<GeneralResult.ResultByAppenders.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GeneralResult.ResultByAppenders.Entry }
         * 
         * 
         */
        public List<GeneralResult.ResultByAppenders.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<GeneralResult.ResultByAppenders.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="value" type="{astelit.ukr:om:ws:result}result" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            protected Result value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link Result }
             *     
             */
            public Result getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link Result }
             *     
             */
            public void setValue(Result value) {
                this.value = value;
            }

        }

    }

}
