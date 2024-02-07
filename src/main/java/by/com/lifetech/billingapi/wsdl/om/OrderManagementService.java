
package by.com.lifetech.billingapi.wsdl.om;

import by.com.lifetech.billingapi.wsdl.om.ws.in.FulfillRequest;
import by.com.lifetech.billingapi.wsdl.om.ws.in.GeneralRequest;
import by.com.lifetech.billingapi.wsdl.om.ws.in.GetProductsRequest;
import by.com.lifetech.billingapi.wsdl.om.ws.in.OperateProductRequest;
import by.com.lifetech.billingapi.wsdl.om.ws.in.SearchKeyGeneralRequest;
import by.com.lifetech.billingapi.wsdl.om.ws.result.FlexInstallmentResult;
import by.com.lifetech.billingapi.wsdl.om.ws.result.FulfillResult;
import by.com.lifetech.billingapi.wsdl.om.ws.result.GeneralOperateResult;
import by.com.lifetech.billingapi.wsdl.om.ws.result.GetAvailableProductOfferingsResult;
import by.com.lifetech.billingapi.wsdl.om.ws.result.GetProductOfferingsResult;
import by.com.lifetech.billingapi.wsdl.om.ws.result.GetProductsResult;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;



/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "OrderManagementService", targetNamespace = "astelit.ukr:om")
@XmlSeeAlso({
    by.com.lifetech.billingapi.wsdl.om.ObjectFactory.class,
    by.com.lifetech.billingapi.wsdl.om.ws.result.ObjectFactory.class,
    by.com.lifetech.billingapi.wsdl.ws.api.chain.ObjectFactory.class,
    by.com.lifetech.billingapi.wsdl.om.ws.in.ObjectFactory.class
})
public interface OrderManagementService {


    /**
     * 
     * @param fulfillRequest
     * @return
     *     returns by.com.lifetech.billingapi.wsdl.om.ws.result.FulfillResult
     */
    @WebMethod
    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "fulfill", targetNamespace = "astelit.ukr:om", className = "by.com.lifetech.billingapi.wsdl.om.Fulfill")
    @ResponseWrapper(localName = "fulfillResponse", targetNamespace = "astelit.ukr:om", className = "by.com.lifetech.billingapi.wsdl.om.FulfillResponse")
    public FulfillResult fulfill(
        @WebParam(name = "fulfillRequest", targetNamespace = "")
        FulfillRequest fulfillRequest);

    /**
     * 
     * @param operateProductRequest
     * @return
     *     returns by.com.lifetech.billingapi.wsdl.om.ws.result.GeneralOperateResult
     */
    @WebMethod
    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "operateProduct", targetNamespace = "astelit.ukr:om", className = "by.com.lifetech.billingapi.wsdl.om.OperateProduct")
    @ResponseWrapper(localName = "operateProductResponse", targetNamespace = "astelit.ukr:om", className = "by.com.lifetech.billingapi.wsdl.om.OperateProductResponse")
    public GeneralOperateResult operateProduct(
        @WebParam(name = "operateProductRequest", targetNamespace = "")
        OperateProductRequest operateProductRequest);

    /**
     * 
     * @param searchKeyGeneralRequest
     * @return
     *     returns by.com.lifetech.billingapi.wsdl.om.ws.result.FlexInstallmentResult
     */
    @WebMethod
    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "getFlexInstallments", targetNamespace = "astelit.ukr:om", className = "by.com.lifetech.billingapi.wsdl.om.GetFlexInstallments")
    @ResponseWrapper(localName = "getFlexInstallmentsResponse", targetNamespace = "astelit.ukr:om", className = "by.com.lifetech.billingapi.wsdl.om.GetFlexInstallmentsResponse")
    public FlexInstallmentResult getFlexInstallments(
        @WebParam(name = "searchKeyGeneralRequest", targetNamespace = "")
        SearchKeyGeneralRequest searchKeyGeneralRequest);

    /**
     * 
     * @param getProductOfferingsRequest
     * @return
     *     returns by.com.lifetech.billingapi.wsdl.om.ws.result.GetProductOfferingsResult
     */
    @WebMethod
    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "getProductOfferings", targetNamespace = "astelit.ukr:om", className = "by.com.lifetech.billingapi.wsdl.om.GetProductOfferings")
    @ResponseWrapper(localName = "getProductOfferingsResponse", targetNamespace = "astelit.ukr:om", className = "by.com.lifetech.billingapi.wsdl.om.GetProductOfferingsResponse")
    public GetProductOfferingsResult getProductOfferings(
        @WebParam(name = "getProductOfferingsRequest", targetNamespace = "")
        GeneralRequest getProductOfferingsRequest);

    /**
     * 
     * @param getAvailableProductOfferingsRequest
     * @return
     *     returns by.com.lifetech.billingapi.wsdl.om.ws.result.GetAvailableProductOfferingsResult
     */
    @WebMethod
    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "getAvailableProductOfferings", targetNamespace = "astelit.ukr:om", className = "by.com.lifetech.billingapi.wsdl.om.GetAvailableProductOfferings")
    @ResponseWrapper(localName = "getAvailableProductOfferingsResponse", targetNamespace = "astelit.ukr:om", className = "by.com.lifetech.billingapi.wsdl.om.GetAvailableProductOfferingsResponse")
    public GetAvailableProductOfferingsResult getAvailableProductOfferings(
        @WebParam(name = "GetAvailableProductOfferingsRequest", targetNamespace = "")
        FulfillRequest getAvailableProductOfferingsRequest);

    /**
     * 
     * @param getProductsRequest
     * @return
     *     returns by.com.lifetech.billingapi.wsdl.om.ws.result.GetProductsResult
     */
    @WebMethod
    @WebResult(name = "response", targetNamespace = "")
    @RequestWrapper(localName = "getProducts", targetNamespace = "astelit.ukr:om", className = "by.com.lifetech.billingapi.wsdl.om.GetProducts")
    @ResponseWrapper(localName = "getProductsResponse", targetNamespace = "astelit.ukr:om", className = "by.com.lifetech.billingapi.wsdl.om.GetProductsResponse")
    public GetProductsResult getProducts(
        @WebParam(name = "getProductsRequest", targetNamespace = "")
        GetProductsRequest getProductsRequest);

}