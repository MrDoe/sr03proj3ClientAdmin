package sr03projet3.service;

public class DirectoryProxy implements sr03projet3.service.Directory {
  private String _endpoint = null;
  private sr03projet3.service.Directory directory = null;
  
  public DirectoryProxy() {
    _initDirectoryProxy();
  }
  
  public DirectoryProxy(String endpoint) {
    _endpoint = endpoint;
    _initDirectoryProxy();
  }
  
  private void _initDirectoryProxy() {
    try {
      directory = (new sr03projet3.service.DirectoryServiceLocator()).getDirectory();
      if (directory != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)directory)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)directory)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (directory != null)
      ((javax.xml.rpc.Stub)directory)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public sr03projet3.service.Directory getDirectory() {
    if (directory == null)
      _initDirectoryProxy();
    return directory;
  }
  
  public void removeAdvertisement(long id) throws java.rmi.RemoteException{
    if (directory == null)
      _initDirectoryProxy();
    directory.removeAdvertisement(id);
  }
  
  public sr03projet3.beans.Advertisement[] searchAdvertisements(long categoryId, sr03projet3.beans.Advertisement ad) throws java.rmi.RemoteException{
    if (directory == null)
      _initDirectoryProxy();
    return directory.searchAdvertisements(categoryId, ad);
  }
  
  public void updateAdvertisement(sr03projet3.beans.Advertisement ad) throws java.rmi.RemoteException{
    if (directory == null)
      _initDirectoryProxy();
    directory.updateAdvertisement(ad);
  }
  
  public void updateCategory(sr03projet3.beans.Category c) throws java.rmi.RemoteException{
    if (directory == null)
      _initDirectoryProxy();
    directory.updateCategory(c);
  }
  
  public sr03projet3.beans.Category getCategory(long id) throws java.rmi.RemoteException{
    if (directory == null)
      _initDirectoryProxy();
    return directory.getCategory(id);
  }
  
  public sr03projet3.beans.Category[] getCategories() throws java.rmi.RemoteException{
    if (directory == null)
      _initDirectoryProxy();
    return directory.getCategories();
  }
  
  public sr03projet3.beans.Category addCategory(sr03projet3.beans.Category c) throws java.rmi.RemoteException{
    if (directory == null)
      _initDirectoryProxy();
    return directory.addCategory(c);
  }
  
  public sr03projet3.beans.Advertisement addAdvertisement(sr03projet3.beans.Advertisement ad, long categoryId) throws java.rmi.RemoteException{
    if (directory == null)
      _initDirectoryProxy();
    return directory.addAdvertisement(ad, categoryId);
  }
  
  public void removeCategory(long id) throws java.rmi.RemoteException{
    if (directory == null)
      _initDirectoryProxy();
    directory.removeCategory(id);
  }
  
  public sr03projet3.beans.Advertisement getAdvertisement(long id) throws java.rmi.RemoteException{
    if (directory == null)
      _initDirectoryProxy();
    return directory.getAdvertisement(id);
  }
  
  
}