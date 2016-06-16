/**
 * Category.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sr03projet3.beans;

public class Category  implements java.io.Serializable {
    private sr03projet3.beans.Advertisement[] advertisements;

    private sr03projet3.beans.AdvertisementList advertisementsList;

    private java.lang.Long id;

    private java.lang.String name;

    public Category() {
    }

    public Category(
           sr03projet3.beans.Advertisement[] advertisements,
           sr03projet3.beans.AdvertisementList advertisementsList,
           java.lang.Long id,
           java.lang.String name) {
           this.advertisements = advertisements;
           this.advertisementsList = advertisementsList;
           this.id = id;
           this.name = name;
    }


    /**
     * Gets the advertisements value for this Category.
     * 
     * @return advertisements
     */
    public sr03projet3.beans.Advertisement[] getAdvertisements() {
        return advertisements;
    }


    /**
     * Sets the advertisements value for this Category.
     * 
     * @param advertisements
     */
    public void setAdvertisements(sr03projet3.beans.Advertisement[] advertisements) {
        this.advertisements = advertisements;
    }


    /**
     * Gets the advertisementsList value for this Category.
     * 
     * @return advertisementsList
     */
    public sr03projet3.beans.AdvertisementList getAdvertisementsList() {
        return advertisementsList;
    }


    /**
     * Sets the advertisementsList value for this Category.
     * 
     * @param advertisementsList
     */
    public void setAdvertisementsList(sr03projet3.beans.AdvertisementList advertisementsList) {
        this.advertisementsList = advertisementsList;
    }


    /**
     * Gets the id value for this Category.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this Category.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the name value for this Category.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Category.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Category)) return false;
        Category other = (Category) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.advertisements==null && other.getAdvertisements()==null) || 
             (this.advertisements!=null &&
              java.util.Arrays.equals(this.advertisements, other.getAdvertisements()))) &&
            ((this.advertisementsList==null && other.getAdvertisementsList()==null) || 
             (this.advertisementsList!=null &&
              this.advertisementsList.equals(other.getAdvertisementsList()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAdvertisements() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdvertisements());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdvertisements(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAdvertisementsList() != null) {
            _hashCode += getAdvertisementsList().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Category.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://beans.sr03projet3", "Category"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("advertisements");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans.sr03projet3", "advertisements"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://beans.sr03projet3", "Advertisement"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://service.sr03projet3", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("advertisementsList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans.sr03projet3", "advertisementsList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://beans.sr03projet3", "AdvertisementList"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans.sr03projet3", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans.sr03projet3", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
