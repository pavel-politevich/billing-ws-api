
package by.com.lifetech.billingapi.wsdl.om.ws.in;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ukr.astelit.om.ws.in package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ukr.astelit.om.ws.in
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetProductsRequest }
     * 
     */
    public GetProductsRequest createGetProductsRequest() {
        return new GetProductsRequest();
    }

    /**
     * Create an instance of {@link GeneralRequest }
     * 
     */
    public GeneralRequest createGeneralRequest() {
        return new GeneralRequest();
    }

    /**
     * Create an instance of {@link SearchKeyGeneralRequest }
     * 
     */
    public SearchKeyGeneralRequest createSearchKeyGeneralRequest() {
        return new SearchKeyGeneralRequest();
    }

    /**
     * Create an instance of {@link OperateProductRequest }
     * 
     */
    public OperateProductRequest createOperateProductRequest() {
        return new OperateProductRequest();
    }

    /**
     * Create an instance of {@link FulfillRequest }
     * 
     */
    public FulfillRequest createFulfillRequest() {
        return new FulfillRequest();
    }

}
