/**
 * DirectoryServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sr03projet3.service;

public class DirectoryServiceLocator extends org.apache.axis.client.Service implements sr03projet3.service.DirectoryService {

    public DirectoryServiceLocator() {
    }


    public DirectoryServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DirectoryServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Directory
    private java.lang.String Directory_address = "http://localhost:8080/sr03projet3/services/Directory";

    public java.lang.String getDirectoryAddress() {
        return Directory_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DirectoryWSDDServiceName = "Directory";

    public java.lang.String getDirectoryWSDDServiceName() {
        return DirectoryWSDDServiceName;
    }

    public void setDirectoryWSDDServiceName(java.lang.String name) {
        DirectoryWSDDServiceName = name;
    }

    public sr03projet3.service.Directory getDirectory() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Directory_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDirectory(endpoint);
    }

    public sr03projet3.service.Directory getDirectory(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            sr03projet3.service.DirectorySoapBindingStub _stub = new sr03projet3.service.DirectorySoapBindingStub(portAddress, this);
            _stub.setPortName(getDirectoryWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDirectoryEndpointAddress(java.lang.String address) {
        Directory_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (sr03projet3.service.Directory.class.isAssignableFrom(serviceEndpointInterface)) {
                sr03projet3.service.DirectorySoapBindingStub _stub = new sr03projet3.service.DirectorySoapBindingStub(new java.net.URL(Directory_address), this);
                _stub.setPortName(getDirectoryWSDDServiceName());
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
        if ("Directory".equals(inputPortName)) {
            return getDirectory();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.sr03projet3", "DirectoryService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.sr03projet3", "Directory"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Directory".equals(portName)) {
            setDirectoryEndpointAddress(address);
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
