/**
 * SolicitudPuntoVentaComisionista.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bo.gob.impuestos.siat;

public class SolicitudPuntoVentaComisionista  implements java.io.Serializable {
    private int codigoAmbiente;

    private int codigoModalidad;

    private java.lang.String codigoSistema;

    private int codigoSucursal;

    private java.lang.String cuis;

    private java.lang.String descripcion;

    private java.util.Calendar fechaFin;

    private java.util.Calendar fechaInicio;

    private long nit;

    private long nitComisionista;

    private java.lang.String nombrePuntoVenta;

    private java.lang.String numeroContrato;

    public SolicitudPuntoVentaComisionista() {
    }

    public SolicitudPuntoVentaComisionista(
           int codigoAmbiente,
           int codigoModalidad,
           java.lang.String codigoSistema,
           int codigoSucursal,
           java.lang.String cuis,
           java.lang.String descripcion,
           java.util.Calendar fechaFin,
           java.util.Calendar fechaInicio,
           long nit,
           long nitComisionista,
           java.lang.String nombrePuntoVenta,
           java.lang.String numeroContrato) {
           this.codigoAmbiente = codigoAmbiente;
           this.codigoModalidad = codigoModalidad;
           this.codigoSistema = codigoSistema;
           this.codigoSucursal = codigoSucursal;
           this.cuis = cuis;
           this.descripcion = descripcion;
           this.fechaFin = fechaFin;
           this.fechaInicio = fechaInicio;
           this.nit = nit;
           this.nitComisionista = nitComisionista;
           this.nombrePuntoVenta = nombrePuntoVenta;
           this.numeroContrato = numeroContrato;
    }


    /**
     * Gets the codigoAmbiente value for this SolicitudPuntoVentaComisionista.
     *
     * @return codigoAmbiente
     */
    public int getCodigoAmbiente() {
        return codigoAmbiente;
    }


    /**
     * Sets the codigoAmbiente value for this SolicitudPuntoVentaComisionista.
     *
     * @param codigoAmbiente
     */
    public void setCodigoAmbiente(int codigoAmbiente) {
        this.codigoAmbiente = codigoAmbiente;
    }


    /**
     * Gets the codigoModalidad value for this SolicitudPuntoVentaComisionista.
     *
     * @return codigoModalidad
     */
    public int getCodigoModalidad() {
        return codigoModalidad;
    }


    /**
     * Sets the codigoModalidad value for this SolicitudPuntoVentaComisionista.
     *
     * @param codigoModalidad
     */
    public void setCodigoModalidad(int codigoModalidad) {
        this.codigoModalidad = codigoModalidad;
    }


    /**
     * Gets the codigoSistema value for this SolicitudPuntoVentaComisionista.
     *
     * @return codigoSistema
     */
    public java.lang.String getCodigoSistema() {
        return codigoSistema;
    }


    /**
     * Sets the codigoSistema value for this SolicitudPuntoVentaComisionista.
     *
     * @param codigoSistema
     */
    public void setCodigoSistema(java.lang.String codigoSistema) {
        this.codigoSistema = codigoSistema;
    }


    /**
     * Gets the codigoSucursal value for this SolicitudPuntoVentaComisionista.
     *
     * @return codigoSucursal
     */
    public int getCodigoSucursal() {
        return codigoSucursal;
    }


    /**
     * Sets the codigoSucursal value for this SolicitudPuntoVentaComisionista.
     *
     * @param codigoSucursal
     */
    public void setCodigoSucursal(int codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }


    /**
     * Gets the cuis value for this SolicitudPuntoVentaComisionista.
     *
     * @return cuis
     */
    public java.lang.String getCuis() {
        return cuis;
    }


    /**
     * Sets the cuis value for this SolicitudPuntoVentaComisionista.
     *
     * @param cuis
     */
    public void setCuis(java.lang.String cuis) {
        this.cuis = cuis;
    }


    /**
     * Gets the descripcion value for this SolicitudPuntoVentaComisionista.
     *
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this SolicitudPuntoVentaComisionista.
     *
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the fechaFin value for this SolicitudPuntoVentaComisionista.
     *
     * @return fechaFin
     */
    public java.util.Calendar getFechaFin() {
        return fechaFin;
    }


    /**
     * Sets the fechaFin value for this SolicitudPuntoVentaComisionista.
     *
     * @param fechaFin
     */
    public void setFechaFin(java.util.Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }


    /**
     * Gets the fechaInicio value for this SolicitudPuntoVentaComisionista.
     *
     * @return fechaInicio
     */
    public java.util.Calendar getFechaInicio() {
        return fechaInicio;
    }


    /**
     * Sets the fechaInicio value for this SolicitudPuntoVentaComisionista.
     *
     * @param fechaInicio
     */
    public void setFechaInicio(java.util.Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    /**
     * Gets the nit value for this SolicitudPuntoVentaComisionista.
     *
     * @return nit
     */
    public long getNit() {
        return nit;
    }


    /**
     * Sets the nit value for this SolicitudPuntoVentaComisionista.
     *
     * @param nit
     */
    public void setNit(long nit) {
        this.nit = nit;
    }


    /**
     * Gets the nitComisionista value for this SolicitudPuntoVentaComisionista.
     *
     * @return nitComisionista
     */
    public long getNitComisionista() {
        return nitComisionista;
    }


    /**
     * Sets the nitComisionista value for this SolicitudPuntoVentaComisionista.
     *
     * @param nitComisionista
     */
    public void setNitComisionista(long nitComisionista) {
        this.nitComisionista = nitComisionista;
    }


    /**
     * Gets the nombrePuntoVenta value for this SolicitudPuntoVentaComisionista.
     *
     * @return nombrePuntoVenta
     */
    public java.lang.String getNombrePuntoVenta() {
        return nombrePuntoVenta;
    }


    /**
     * Sets the nombrePuntoVenta value for this SolicitudPuntoVentaComisionista.
     *
     * @param nombrePuntoVenta
     */
    public void setNombrePuntoVenta(java.lang.String nombrePuntoVenta) {
        this.nombrePuntoVenta = nombrePuntoVenta;
    }


    /**
     * Gets the numeroContrato value for this SolicitudPuntoVentaComisionista.
     *
     * @return numeroContrato
     */
    public java.lang.String getNumeroContrato() {
        return numeroContrato;
    }


    /**
     * Sets the numeroContrato value for this SolicitudPuntoVentaComisionista.
     *
     * @param numeroContrato
     */
    public void setNumeroContrato(java.lang.String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SolicitudPuntoVentaComisionista)) return false;
        SolicitudPuntoVentaComisionista other = (SolicitudPuntoVentaComisionista) obj;
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
            ((this.cuis==null && other.getCuis()==null) ||
             (this.cuis!=null &&
              this.cuis.equals(other.getCuis()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) ||
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.fechaFin==null && other.getFechaFin()==null) ||
             (this.fechaFin!=null &&
              this.fechaFin.equals(other.getFechaFin()))) &&
            ((this.fechaInicio==null && other.getFechaInicio()==null) ||
             (this.fechaInicio!=null &&
              this.fechaInicio.equals(other.getFechaInicio()))) &&
            this.nit == other.getNit() &&
            this.nitComisionista == other.getNitComisionista() &&
            ((this.nombrePuntoVenta==null && other.getNombrePuntoVenta()==null) ||
             (this.nombrePuntoVenta!=null &&
              this.nombrePuntoVenta.equals(other.getNombrePuntoVenta()))) &&
            ((this.numeroContrato==null && other.getNumeroContrato()==null) ||
             (this.numeroContrato!=null &&
              this.numeroContrato.equals(other.getNumeroContrato())));
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
        if (getCuis() != null) {
            _hashCode += getCuis().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getFechaFin() != null) {
            _hashCode += getFechaFin().hashCode();
        }
        if (getFechaInicio() != null) {
            _hashCode += getFechaInicio().hashCode();
        }
        _hashCode += new Long(getNit()).hashCode();
        _hashCode += new Long(getNitComisionista()).hashCode();
        if (getNombrePuntoVenta() != null) {
            _hashCode += getNombrePuntoVenta().hashCode();
        }
        if (getNumeroContrato() != null) {
            _hashCode += getNumeroContrato().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SolicitudPuntoVentaComisionista.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudPuntoVentaComisionista"));
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
        elemField.setFieldName("fechaFin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaFin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInicio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaInicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nitComisionista");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nitComisionista"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombrePuntoVenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombrePuntoVenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroContrato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroContrato"));
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
