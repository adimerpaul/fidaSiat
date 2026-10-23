/**
 * ServicioFacturacionOperacionesLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bo.gob.impuestos.siat.FacturacionOperaciones;

public class ServicioFacturacionOperacionesLocator extends org.apache.axis.client.Service implements bo.gob.impuestos.siat.FacturacionOperaciones.ServicioFacturacionOperaciones {

    public ServicioFacturacionOperacionesLocator() {
    }


    public ServicioFacturacionOperacionesLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServicioFacturacionOperacionesLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServicioFacturacionOperacionesPort
    private java.lang.String ServicioFacturacionOperacionesPort_address = "https://siatrest.impuestos.gob.bo/v2/FacturacionOperaciones";

    public java.lang.String getServicioFacturacionOperacionesPortAddress() {
        return ServicioFacturacionOperacionesPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServicioFacturacionOperacionesPortWSDDServiceName = "ServicioFacturacionOperacionesPort";

    public java.lang.String getServicioFacturacionOperacionesPortWSDDServiceName() {
        return ServicioFacturacionOperacionesPortWSDDServiceName;
    }

    public void setServicioFacturacionOperacionesPortWSDDServiceName(java.lang.String name) {
        ServicioFacturacionOperacionesPortWSDDServiceName = name;
    }

    public bo.gob.impuestos.siat.ServicioFacturacionOperaciones getServicioFacturacionOperacionesPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServicioFacturacionOperacionesPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServicioFacturacionOperacionesPort(endpoint);
    }

    public bo.gob.impuestos.siat.ServicioFacturacionOperaciones getServicioFacturacionOperacionesPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            bo.gob.impuestos.siat.FacturacionOperaciones.ServicioFacturacionOperacionesSoapBindingStub _stub = new bo.gob.impuestos.siat.FacturacionOperaciones.ServicioFacturacionOperacionesSoapBindingStub(portAddress, this);
            _stub.setPortName(getServicioFacturacionOperacionesPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServicioFacturacionOperacionesPortEndpointAddress(java.lang.String address) {
        ServicioFacturacionOperacionesPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (bo.gob.impuestos.siat.ServicioFacturacionOperaciones.class.isAssignableFrom(serviceEndpointInterface)) {
                bo.gob.impuestos.siat.FacturacionOperaciones.ServicioFacturacionOperacionesSoapBindingStub _stub = new bo.gob.impuestos.siat.FacturacionOperaciones.ServicioFacturacionOperacionesSoapBindingStub(new java.net.URL(ServicioFacturacionOperacionesPort_address), this);
                _stub.setPortName(getServicioFacturacionOperacionesPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ServicioFacturacionOperacionesPort".equals(inputPortName)) {
            return getServicioFacturacionOperacionesPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/FacturacionOperaciones", "ServicioFacturacionOperaciones");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/FacturacionOperaciones", "ServicioFacturacionOperacionesPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

if ("ServicioFacturacionOperacionesPort".equals(portName)) {
            setServicioFacturacionOperacionesPortEndpointAddress(address);
        }
        else
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
