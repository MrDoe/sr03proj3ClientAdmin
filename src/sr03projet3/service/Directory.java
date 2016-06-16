/**
 * Directory.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sr03projet3.service;

public interface Directory extends java.rmi.Remote {
    public void removeAdvertisement(long id) throws java.rmi.RemoteException;
    public sr03projet3.beans.Advertisement[] searchAdvertisements(long categoryId, sr03projet3.beans.Advertisement ad) throws java.rmi.RemoteException;
    public void updateAdvertisement(sr03projet3.beans.Advertisement ad) throws java.rmi.RemoteException;
    public void updateCategory(sr03projet3.beans.Category c) throws java.rmi.RemoteException;
    public sr03projet3.beans.Category getCategory(long id) throws java.rmi.RemoteException;
    public sr03projet3.beans.Category[] getCategories() throws java.rmi.RemoteException;
    public sr03projet3.beans.Category addCategory(sr03projet3.beans.Category c) throws java.rmi.RemoteException;
    public sr03projet3.beans.Advertisement addAdvertisement(sr03projet3.beans.Advertisement ad, long categoryId) throws java.rmi.RemoteException;
    public void removeCategory(long id) throws java.rmi.RemoteException;
    public sr03projet3.beans.Advertisement getAdvertisement(long id) throws java.rmi.RemoteException;
}
