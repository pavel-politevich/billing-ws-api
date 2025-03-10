
package by.com.lifetech.billingapi.wsdl.om;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.xml.namespace.QName;

import by.com.lifetech.billingapi.wsdl.ChainAPI_Service;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "OrderManagement", targetNamespace = "astelit.ukr:om", wsdlLocation = "classpath:wsdl/om_profile.wsdl")
public class OrderManagement
    extends Service
{

    private final static URL ORDERMANAGEMENT_WSDL_LOCATION;
    private final static WebServiceException ORDERMANAGEMENT_EXCEPTION;
    private final static QName ORDERMANAGEMENT_QNAME = new QName("astelit.ukr:om", "OrderManagement");
    static Logger logger = LoggerFactory.getLogger(OrderManagement.class);

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
            url = new URL(prop.getProperty("ws.om.wsdl"));
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ORDERMANAGEMENT_WSDL_LOCATION = url;
        ORDERMANAGEMENT_EXCEPTION = e;
    }

    public OrderManagement() {
        super(__getWsdlLocation(), ORDERMANAGEMENT_QNAME);
    }

    public OrderManagement(WebServiceFeature... features) {
        super(__getWsdlLocation(), ORDERMANAGEMENT_QNAME, features);
    }

    public OrderManagement(URL wsdlLocation) {
        super(wsdlLocation, ORDERMANAGEMENT_QNAME);
    }

    public OrderManagement(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ORDERMANAGEMENT_QNAME, features);
    }

    public OrderManagement(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OrderManagement(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns OrderManagementService
     */
    @WebEndpoint(name = "OrderManagementPort")
    public OrderManagementService getOrderManagementPort() {
        return super.getPort(new QName("astelit.ukr:om", "OrderManagementPort"), OrderManagementService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OrderManagementService
     */
    @WebEndpoint(name = "OrderManagementPort")
    public OrderManagementService getOrderManagementPort(WebServiceFeature... features) {
        return super.getPort(new QName("astelit.ukr:om", "OrderManagementPort"), OrderManagementService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ORDERMANAGEMENT_EXCEPTION!= null) {
            throw ORDERMANAGEMENT_EXCEPTION;
        }
        return ORDERMANAGEMENT_WSDL_LOCATION;
    }

}
