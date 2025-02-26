
package by.com.lifetech.billingapi.wsdl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.0
 * Generated source version: 3.0
 * 
 */
@WebServiceClient(name = "ChainAPI", targetNamespace = "http://chain.api.ws.rtn.idr.astelit.ukr/", wsdlLocation = "classpath:wsdl/chain-service?wsdl")
public class ChainAPI_Service
    extends Service
{

    private final static URL CHAINAPI_WSDL_LOCATION;
    private final static WebServiceException CHAINAPI_EXCEPTION;
    private final static QName CHAINAPI_QNAME = new QName("http://chain.api.ws.rtn.idr.astelit.ukr/", "ChainAPI");
    static Logger logger = LoggerFactory.getLogger(ChainAPI_Service.class);
    


    static {
    	
    	InputStream input = ChainAPI_Service.class.getClassLoader().getResourceAsStream("application.properties");
    	Properties prop = new Properties();
    	//load a properties file from class path, inside static method
        try {
			prop.load(input);
		} catch (IOException e) {
            logger.error(e.getMessage());
		}
    	
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL(prop.getProperty("chain.om.wsdl"));
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CHAINAPI_WSDL_LOCATION = url;
        CHAINAPI_EXCEPTION = e;
    }

    public ChainAPI_Service() {
        super(__getWsdlLocation(), CHAINAPI_QNAME);
    }

    public ChainAPI_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), CHAINAPI_QNAME, features);
    }

    public ChainAPI_Service(URL wsdlLocation) {
        super(wsdlLocation, CHAINAPI_QNAME);
    }

    public ChainAPI_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CHAINAPI_QNAME, features);
    }

    public ChainAPI_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ChainAPI_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ChainAPI
     */
    @WebEndpoint(name = "ChainAPIPort")
    public ChainAPI getChainAPIPort() {
        return super.getPort(new QName("http://chain.api.ws.rtn.idr.astelit.ukr/", "ChainAPIPort"), ChainAPI.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ChainAPI
     */
    @WebEndpoint(name = "ChainAPIPort")
    public ChainAPI getChainAPIPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://chain.api.ws.rtn.idr.astelit.ukr/", "ChainAPIPort"), ChainAPI.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CHAINAPI_EXCEPTION!= null) {
            throw CHAINAPI_EXCEPTION;
        }
        return CHAINAPI_WSDL_LOCATION;
    }


}
