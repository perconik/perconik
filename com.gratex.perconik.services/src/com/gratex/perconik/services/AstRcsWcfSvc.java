
package com.gratex.perconik.services;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "AstRcsWcfSvc", targetNamespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", wsdlLocation = "file:/D:/workspace/perconik/perconik/com.gratex.perconik.services/AstRcsWcfSvc.svc.wsdl")
public class AstRcsWcfSvc
    extends Service
{

    private final static URL ASTRCSWCFSVC_WSDL_LOCATION;
    private final static WebServiceException ASTRCSWCFSVC_EXCEPTION;
    private final static QName ASTRCSWCFSVC_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "AstRcsWcfSvc");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/D:/workspace/perconik/perconik/com.gratex.perconik.services/AstRcsWcfSvc.svc.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ASTRCSWCFSVC_WSDL_LOCATION = url;
        ASTRCSWCFSVC_EXCEPTION = e;
    }

    public AstRcsWcfSvc() {
        super(__getWsdlLocation(), ASTRCSWCFSVC_QNAME);
    }

    public AstRcsWcfSvc(WebServiceFeature... features) {
        super(__getWsdlLocation(), ASTRCSWCFSVC_QNAME, features);
    }

    public AstRcsWcfSvc(URL wsdlLocation) {
        super(wsdlLocation, ASTRCSWCFSVC_QNAME);
    }

    public AstRcsWcfSvc(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ASTRCSWCFSVC_QNAME, features);
    }

    public AstRcsWcfSvc(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AstRcsWcfSvc(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IAstRcsWcfSvc
     */
    @WebEndpoint(name = "BasicHttpBinding_IAstRcsWcfSvc")
    public IAstRcsWcfSvc getBasicHttpBindingIAstRcsWcfSvc() {
        return super.getPort(new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "BasicHttpBinding_IAstRcsWcfSvc"), IAstRcsWcfSvc.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IAstRcsWcfSvc
     */
    @WebEndpoint(name = "BasicHttpBinding_IAstRcsWcfSvc")
    public IAstRcsWcfSvc getBasicHttpBindingIAstRcsWcfSvc(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "BasicHttpBinding_IAstRcsWcfSvc"), IAstRcsWcfSvc.class, features);
    }

    /**
     * 
     * @return
     *     returns IAstRcsWcfSvc
     */
    @WebEndpoint(name = "BasicHttpBinding_IAstRcsWcfSvc1")
    public IAstRcsWcfSvc getBasicHttpBindingIAstRcsWcfSvc1() {
        return super.getPort(new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "BasicHttpBinding_IAstRcsWcfSvc1"), IAstRcsWcfSvc.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IAstRcsWcfSvc
     */
    @WebEndpoint(name = "BasicHttpBinding_IAstRcsWcfSvc1")
    public IAstRcsWcfSvc getBasicHttpBindingIAstRcsWcfSvc1(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "BasicHttpBinding_IAstRcsWcfSvc1"), IAstRcsWcfSvc.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ASTRCSWCFSVC_EXCEPTION!= null) {
            throw ASTRCSWCFSVC_EXCEPTION;
        }
        return ASTRCSWCFSVC_WSDL_LOCATION;
    }

}
