/**
 * SolicitudConsultaPuntoVenta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bo.gob.impuestos.siat;

public class SolicitudConsultaPuntoVenta  implements java.io.Serializable {
    private int codigoAmbiente;

    private java.lang.String codigoSistema;

    private int codigoSucursal;

    private java.lang.String cuis;

    private long nit;

    public SolicitudConsultaPuntoVenta() {
    }

    public SolicitudConsultaPuntoVenta(
           int codigoAmbiente,
           java.lang.String codigoSistema,
           int codigoSucursal,
           java.lang.String cuis,
           long nit) {
           this.codigoAmbiente = codigoAmbiente;
           this.codigoSistema = codigoSistema;
           this.codigoSucursal = codigoSucursal;
           this.cuis = cuis;
           this.nit = nit;
    }


    /**
     * Gets the codigoAmbiente value for this SolicitudConsultaPuntoVenta.
     *
     * @return codigoAmbiente
     */
    public int getCodigoAmbiente() {
        return codigoAmbiente;
    }


    /**
     * Sets the codigoAmbiente value for this SolicitudConsultaPuntoVenta.
     *
     * @param codigoAmbiente
     */
    public void setCodigoAmbiente(int codigoAmbiente) {
        this.codigoAmbiente = codigoAmbiente;
    }


    /**
     * Gets the codigoSistema value for this SolicitudConsultaPuntoVenta.
     *
     * @return codigoSistema
     */
    public java.lang.String getCodigoSistema() {
        return codigoSistema;
    }


    /**
     * Sets the codigoSistema value for this SolicitudConsultaPuntoVenta.
     *
     * @param codigoSistema
     */
    public void setCodigoSistema(java.lang.String codigoSistema) {
        this.codigoSistema = codigoSistema;
    }


    /**
     * Gets the codigoSucursal value for this SolicitudConsultaPuntoVenta.
     *
     * @return codigoSucursal
     */
    public int getCodigoSucursal() {
        return codigoSucursal;
    }


    /**
     * Sets the codigoSucursal value for this SolicitudConsultaPuntoVenta.
     *
     * @param codigoSucursal
     */
    public void setCodigoSucursal(int codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }


    /**
     * Gets the cuis value for this SolicitudConsultaPuntoVenta.
     *
     * @return cuis
     */
    public java.lang.String getCuis() {
        return cuis;
    }


    /**
     * Sets the cuis value for this SolicitudConsultaPuntoVenta.
     *
     * @param cuis
     */
    public void setCuis(java.lang.String cuis) {
        this.cuis = cuis;
    }


    /**
     * Gets the nit value for this SolicitudConsultaPuntoVenta.
     *
     * @return nit
     */
    public long getNit() {
        return nit;
    }


    /**
     * Sets the nit value for this SolicitudConsultaPuntoVenta.
     *
     * @param nit
     */
    public void setNit(long nit) {
        this.nit = nit;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SolicitudConsultaPuntoVenta)) return false;
        SolicitudConsultaPuntoVenta other = (SolicitudConsultaPuntoVenta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            this.codigoAmbiente == other.getCodigoAmbiente() &&
            ((this.codigoSistema==null && other.getCodigoSistema()==null) ||
             (this.codigoSistema!=null &&
              this.codigoSistema.equals(other.getCodigoSistema()))) &&
            this.codigoSucursal == other.getCodigoSucursal() &&
            ((this.cuis==null && other.getCuis()==null) ||
             (this.cuis!=null &&
              this.cuis.equals(other.getCuis()))) &&
            this.nit == other.getNit();
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
        _hashCode += getCodigoAmbiente();
        if (getCodigoSistema() != null) {
            _hashCode += getCodigoSistema().hashCode();
        }
        _hashCode += getCodigoSucursal();
        if (getCuis() != null) {
            _hashCode += getCuis().hashCode();
        }
        _hashCode += new Long(getNit()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SolicitudConsultaPuntoVenta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudConsultaPuntoVenta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoAmbiente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoAmbiente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSistema");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoSistema"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSucursal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoSucursal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cuis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cuis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
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
