/**
 * SolicitudRegistroPuntoVenta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bo.gob.impuestos.siat;

public class SolicitudRegistroPuntoVenta  implements java.io.Serializable {
    private int codigoAmbiente;

    private int codigoModalidad;

    private java.lang.String codigoSistema;

    private int codigoSucursal;

    private int codigoTipoPuntoVenta;

    private java.lang.String cuis;

    private java.lang.String descripcion;

    private long nit;

    private java.lang.String nombrePuntoVenta;

    public SolicitudRegistroPuntoVenta() {
    }

    public SolicitudRegistroPuntoVenta(
           int codigoAmbiente,
           int codigoModalidad,
           java.lang.String codigoSistema,
           int codigoSucursal,
           int codigoTipoPuntoVenta,
           java.lang.String cuis,
           java.lang.String descripcion,
           long nit,
           java.lang.String nombrePuntoVenta) {
           this.codigoAmbiente = codigoAmbiente;
           this.codigoModalidad = codigoModalidad;
           this.codigoSistema = codigoSistema;
           this.codigoSucursal = codigoSucursal;
           this.codigoTipoPuntoVenta = codigoTipoPuntoVenta;
           this.cuis = cuis;
           this.descripcion = descripcion;
           this.nit = nit;
           this.nombrePuntoVenta = nombrePuntoVenta;
    }


    /**
     * Gets the codigoAmbiente value for this SolicitudRegistroPuntoVenta.
     *
     * @return codigoAmbiente
     */
    public int getCodigoAmbiente() {
        return codigoAmbiente;
    }


    /**
     * Sets the codigoAmbiente value for this SolicitudRegistroPuntoVenta.
     *
     * @param codigoAmbiente
     */
    public void setCodigoAmbiente(int codigoAmbiente) {
        this.codigoAmbiente = codigoAmbiente;
    }


    /**
     * Gets the codigoModalidad value for this SolicitudRegistroPuntoVenta.
     *
     * @return codigoModalidad
     */
    public int getCodigoModalidad() {
        return codigoModalidad;
    }


    /**
     * Sets the codigoModalidad value for this SolicitudRegistroPuntoVenta.
     *
     * @param codigoModalidad
     */
    public void setCodigoModalidad(int codigoModalidad) {
        this.codigoModalidad = codigoModalidad;
    }


    /**
     * Gets the codigoSistema value for this SolicitudRegistroPuntoVenta.
     *
     * @return codigoSistema
     */
    public java.lang.String getCodigoSistema() {
        return codigoSistema;
    }


    /**
     * Sets the codigoSistema value for this SolicitudRegistroPuntoVenta.
     *
     * @param codigoSistema
     */
    public void setCodigoSistema(java.lang.String codigoSistema) {
        this.codigoSistema = codigoSistema;
    }


    /**
     * Gets the codigoSucursal value for this SolicitudRegistroPuntoVenta.
     *
     * @return codigoSucursal
     */
    public int getCodigoSucursal() {
        return codigoSucursal;
    }


    /**
     * Sets the codigoSucursal value for this SolicitudRegistroPuntoVenta.
     *
     * @param codigoSucursal
     */
    public void setCodigoSucursal(int codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }


    /**
     * Gets the codigoTipoPuntoVenta value for this SolicitudRegistroPuntoVenta.
     *
     * @return codigoTipoPuntoVenta
     */
    public int getCodigoTipoPuntoVenta() {
        return codigoTipoPuntoVenta;
    }


    /**
     * Sets the codigoTipoPuntoVenta value for this SolicitudRegistroPuntoVenta.
     *
     * @param codigoTipoPuntoVenta
     */
    public void setCodigoTipoPuntoVenta(int codigoTipoPuntoVenta) {
        this.codigoTipoPuntoVenta = codigoTipoPuntoVenta;
    }


    /**
     * Gets the cuis value for this SolicitudRegistroPuntoVenta.
     *
     * @return cuis
     */
    public java.lang.String getCuis() {
        return cuis;
    }


    /**
     * Sets the cuis value for this SolicitudRegistroPuntoVenta.
     *
     * @param cuis
     */
    public void setCuis(java.lang.String cuis) {
        this.cuis = cuis;
    }


    /**
     * Gets the descripcion value for this SolicitudRegistroPuntoVenta.
     *
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this SolicitudRegistroPuntoVenta.
     *
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the nit value for this SolicitudRegistroPuntoVenta.
     *
     * @return nit
     */
    public long getNit() {
        return nit;
    }


    /**
     * Sets the nit value for this SolicitudRegistroPuntoVenta.
     *
     * @param nit
     */
    public void setNit(long nit) {
        this.nit = nit;
    }


    /**
     * Gets the nombrePuntoVenta value for this SolicitudRegistroPuntoVenta.
     *
     * @return nombrePuntoVenta
     */
    public java.lang.String getNombrePuntoVenta() {
        return nombrePuntoVenta;
    }


    /**
     * Sets the nombrePuntoVenta value for this SolicitudRegistroPuntoVenta.
     *
     * @param nombrePuntoVenta
     */
    public void setNombrePuntoVenta(java.lang.String nombrePuntoVenta) {
        this.nombrePuntoVenta = nombrePuntoVenta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SolicitudRegistroPuntoVenta)) return false;
        SolicitudRegistroPuntoVenta other = (SolicitudRegistroPuntoVenta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            this.codigoAmbiente == other.getCodigoAmbiente() &&
            this.codigoModalidad == other.getCodigoModalidad() &&
            ((this.codigoSistema==null && other.getCodigoSistema()==null) ||
             (this.codigoSistema!=null &&
              this.codigoSistema.equals(other.getCodigoSistema()))) &&
            this.codigoSucursal == other.getCodigoSucursal() &&
            this.codigoTipoPuntoVenta == other.getCodigoTipoPuntoVenta() &&
            ((this.cuis==null && other.getCuis()==null) ||
             (this.cuis!=null &&
              this.cuis.equals(other.getCuis()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) ||
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            this.nit == other.getNit() &&
            ((this.nombrePuntoVenta==null && other.getNombrePuntoVenta()==null) ||
             (this.nombrePuntoVenta!=null &&
              this.nombrePuntoVenta.equals(other.getNombrePuntoVenta())));
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
        _hashCode += getCodigoModalidad();
        if (getCodigoSistema() != null) {
            _hashCode += getCodigoSistema().hashCode();
        }
        _hashCode += getCodigoSucursal();
        _hashCode += getCodigoTipoPuntoVenta();
        if (getCuis() != null) {
            _hashCode += getCuis().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        _hashCode += new Long(getNit()).hashCode();
        if (getNombrePuntoVenta() != null) {
            _hashCode += getNombrePuntoVenta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SolicitudRegistroPuntoVenta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudRegistroPuntoVenta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoAmbiente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoAmbiente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoModalidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoModalidad"));
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
        elemField.setFieldName("codigoTipoPuntoVenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoTipoPuntoVenta"));
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
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombrePuntoVenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombrePuntoVenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
