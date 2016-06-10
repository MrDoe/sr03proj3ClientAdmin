/**
 * DirectoryService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sr03projet3.service;

public interface DirectoryService extends javax.xml.rpc.Service {
    public java.lang.String getDirectoryAddress();

    public sr03projet3.service.Directory getDirectory() throws javax.xml.rpc.ServiceException;

    public sr03projet3.service.Directory getDirectory(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
