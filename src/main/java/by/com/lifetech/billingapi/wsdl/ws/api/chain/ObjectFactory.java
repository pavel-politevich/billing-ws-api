
package by.com.lifetech.billingapi.wsdl.ws.api.chain;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ukr.astelit.idr.rtn.ws.api.chain package. 
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

    private final static QName _GetChainSignature_QNAME = new QName("http://chain.api.ws.rtn.idr.astelit.ukr/", "getChainSignature");
    private final static QName _GetRegisteredChainsNames_QNAME = new QName("http://chain.api.ws.rtn.idr.astelit.ukr/", "getRegisteredChainsNames");
    private final static QName _ExecuteDebugChainResponse_QNAME = new QName("http://chain.api.ws.rtn.idr.astelit.ukr/", "executeDebugChainResponse");
    private final static QName _GetChainSignatureResponse_QNAME = new QName("http://chain.api.ws.rtn.idr.astelit.ukr/", "getChainSignatureResponse");
    private final static QName _ExecuteChain_QNAME = new QName("http://chain.api.ws.rtn.idr.astelit.ukr/", "executeChain");
    private final static QName _ExecuteDebugChain_QNAME = new QName("http://chain.api.ws.rtn.idr.astelit.ukr/", "executeDebugChain");
    private final static QName _ExecuteChainResponse_QNAME = new QName("http://chain.api.ws.rtn.idr.astelit.ukr/", "executeChainResponse");
    private final static QName _GetRegisteredChainsNamesResponse_QNAME = new QName("http://chain.api.ws.rtn.idr.astelit.ukr/", "getRegisteredChainsNamesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ukr.astelit.idr.rtn.ws.api.chain
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetRegisteredChainsNamesResponse }
     * 
     */
    public GetRegisteredChainsNamesResponse createGetRegisteredChainsNamesResponse() {
        return new GetRegisteredChainsNamesResponse();
    }

    /**
     * Create an instance of {@link ExecuteDebugChainResponse }
     * 
     */
    public ExecuteDebugChainResponse createExecuteDebugChainResponse() {
        return new ExecuteDebugChainResponse();
    }

    /**
     * Create an instance of {@link GetChainSignatureResponse }
     * 
     */
    public GetChainSignatureResponse createGetChainSignatureResponse() {
        return new GetChainSignatureResponse();
    }

    /**
     * Create an instance of {@link ExecuteDebugChain }
     * 
     */
    public ExecuteDebugChain createExecuteDebugChain() {
        return new ExecuteDebugChain();
    }

    /**
     * Create an instance of {@link GetRegisteredChainsNames }
     * 
     */
    public GetRegisteredChainsNames createGetRegisteredChainsNames() {
        return new GetRegisteredChainsNames();
    }

    /**
     * Create an instance of {@link ExecuteChain }
     * 
     */
    public ExecuteChain createExecuteChain() {
        return new ExecuteChain();
    }

    /**
     * Create an instance of {@link ExecuteChainResponse }
     * 
     */
    public ExecuteChainResponse createExecuteChainResponse() {
        return new ExecuteChainResponse();
    }

    /**
     * Create an instance of {@link GetChainSignature }
     * 
     */
    public GetChainSignature createGetChainSignature() {
        return new GetChainSignature();
    }

    /**
     * Create an instance of {@link ChainResultElement }
     * 
     */
    public ChainResultElement createChainResultElement() {
        return new ChainResultElement();
    }

    /**
     * Create an instance of {@link InputParameter }
     * 
     */
    public InputParameter createInputParameter() {
        return new InputParameter();
    }

    /**
     * Create an instance of {@link ChainRequest }
     * 
     */
    public ChainRequest createChainRequest() {
        return new ChainRequest();
    }

    /**
     * Create an instance of {@link ChainSignatureResult }
     * 
     */
    public ChainSignatureResult createChainSignatureResult() {
        return new ChainSignatureResult();
    }

    /**
     * Create an instance of {@link GeneralResult }
     * 
     */
    public GeneralResult createGeneralResult() {
        return new GeneralResult();
    }

    /**
     * Create an instance of {@link ChainResult }
     * 
     */
    public ChainResult createChainResult() {
        return new ChainResult();
    }

    /**
     * Create an instance of {@link BusinessError }
     * 
     */
    public BusinessError createBusinessError() {
        return new BusinessError();
    }

    /**
     * Create an instance of {@link LogEntry }
     * 
     */
    public LogEntry createLogEntry() {
        return new LogEntry();
    }

    /**
     * Create an instance of {@link ChainsListResult }
     * 
     */
    public ChainsListResult createChainsListResult() {
        return new ChainsListResult();
    }

    /**
     * Create an instance of {@link ChainVariableDTO }
     * 
     */
    public ChainVariableDTO createChainVariableDTO() {
        return new ChainVariableDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetChainSignature }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chain.api.ws.rtn.idr.astelit.ukr/", name = "getChainSignature")
    public JAXBElement<GetChainSignature> createGetChainSignature(GetChainSignature value) {
        return new JAXBElement<GetChainSignature>(_GetChainSignature_QNAME, GetChainSignature.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRegisteredChainsNames }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chain.api.ws.rtn.idr.astelit.ukr/", name = "getRegisteredChainsNames")
    public JAXBElement<GetRegisteredChainsNames> createGetRegisteredChainsNames(GetRegisteredChainsNames value) {
        return new JAXBElement<GetRegisteredChainsNames>(_GetRegisteredChainsNames_QNAME, GetRegisteredChainsNames.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteDebugChainResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chain.api.ws.rtn.idr.astelit.ukr/", name = "executeDebugChainResponse")
    public JAXBElement<ExecuteDebugChainResponse> createExecuteDebugChainResponse(ExecuteDebugChainResponse value) {
        return new JAXBElement<ExecuteDebugChainResponse>(_ExecuteDebugChainResponse_QNAME, ExecuteDebugChainResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetChainSignatureResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chain.api.ws.rtn.idr.astelit.ukr/", name = "getChainSignatureResponse")
    public JAXBElement<GetChainSignatureResponse> createGetChainSignatureResponse(GetChainSignatureResponse value) {
        return new JAXBElement<GetChainSignatureResponse>(_GetChainSignatureResponse_QNAME, GetChainSignatureResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteChain }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chain.api.ws.rtn.idr.astelit.ukr/", name = "executeChain")
    public JAXBElement<ExecuteChain> createExecuteChain(ExecuteChain value) {
        return new JAXBElement<ExecuteChain>(_ExecuteChain_QNAME, ExecuteChain.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteDebugChain }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chain.api.ws.rtn.idr.astelit.ukr/", name = "executeDebugChain")
    public JAXBElement<ExecuteDebugChain> createExecuteDebugChain(ExecuteDebugChain value) {
        return new JAXBElement<ExecuteDebugChain>(_ExecuteDebugChain_QNAME, ExecuteDebugChain.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteChainResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chain.api.ws.rtn.idr.astelit.ukr/", name = "executeChainResponse")
    public JAXBElement<ExecuteChainResponse> createExecuteChainResponse(ExecuteChainResponse value) {
        return new JAXBElement<ExecuteChainResponse>(_ExecuteChainResponse_QNAME, ExecuteChainResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRegisteredChainsNamesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chain.api.ws.rtn.idr.astelit.ukr/", name = "getRegisteredChainsNamesResponse")
    public JAXBElement<GetRegisteredChainsNamesResponse> createGetRegisteredChainsNamesResponse(GetRegisteredChainsNamesResponse value) {
        return new JAXBElement<GetRegisteredChainsNamesResponse>(_GetRegisteredChainsNamesResponse_QNAME, GetRegisteredChainsNamesResponse.class, null, value);
    }

}
