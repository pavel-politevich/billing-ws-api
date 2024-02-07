
package by.com.lifetech.billingapi.wsdl.om.ws.result;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ukr.astelit.om.ws.result package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ChainExecutionResult_QNAME = new QName("astelit.ukr:om:ws:result", "chainExecutionResult");
    private final static QName _GeneralResult_QNAME = new QName("astelit.ukr:om:ws:result", "generalResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ukr.astelit.om.ws.result
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GeneralResult }
     * 
     */
    public GeneralResult createGeneralResult() {
        return new GeneralResult();
    }

    /**
     * Create an instance of {@link GeneralResult.ResultByAppenders }
     * 
     */
    public GeneralResult.ResultByAppenders createGeneralResultResultByAppenders() {
        return new GeneralResult.ResultByAppenders();
    }

    /**
     * Create an instance of {@link ChainExecutionResult }
     * 
     */
    public ChainExecutionResult createChainExecutionResult() {
        return new ChainExecutionResult();
    }

    /**
     * Create an instance of {@link Result }
     * 
     */
    public Result createResult() {
        return new Result();
    }

    /**
     * Create an instance of {@link GetProductOfferingsResult }
     * 
     */
    public GetProductOfferingsResult createGetProductOfferingsResult() {
        return new GetProductOfferingsResult();
    }

    /**
     * Create an instance of {@link FlexInstallmentResult }
     * 
     */
    public FlexInstallmentResult createFlexInstallmentResult() {
        return new FlexInstallmentResult();
    }

    /**
     * Create an instance of {@link GetProductsResult }
     * 
     */
    public GetProductsResult createGetProductsResult() {
        return new GetProductsResult();
    }

    /**
     * Create an instance of {@link FulfillResult }
     * 
     */
    public FulfillResult createFulfillResult() {
        return new FulfillResult();
    }

    /**
     * Create an instance of {@link ScenarioErrorResult }
     * 
     */
    public ScenarioErrorResult createScenarioErrorResult() {
        return new ScenarioErrorResult();
    }

    /**
     * Create an instance of {@link GetAvailableProductOfferingsResult }
     * 
     */
    public GetAvailableProductOfferingsResult createGetAvailableProductOfferingsResult() {
        return new GetAvailableProductOfferingsResult();
    }

    /**
     * Create an instance of {@link GeneralOperateResult }
     * 
     */
    public GeneralOperateResult createGeneralOperateResult() {
        return new GeneralOperateResult();
    }

    /**
     * Create an instance of {@link GeneralResult.ResultByAppenders.Entry }
     * 
     */
    public GeneralResult.ResultByAppenders.Entry createGeneralResultResultByAppendersEntry() {
        return new GeneralResult.ResultByAppenders.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChainExecutionResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om:ws:result", name = "chainExecutionResult")
    public JAXBElement<ChainExecutionResult> createChainExecutionResult(ChainExecutionResult value) {
        return new JAXBElement<ChainExecutionResult>(_ChainExecutionResult_QNAME, ChainExecutionResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneralResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "astelit.ukr:om:ws:result", name = "generalResult")
    public JAXBElement<GeneralResult> createGeneralResult(GeneralResult value) {
        return new JAXBElement<GeneralResult>(_GeneralResult_QNAME, GeneralResult.class, null, value);
    }

}
