/**
 * SolicitudEventoSignificativo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bo.gob.impuestos.siat;

import java.util.Calendar;

public class SolicitudEventoSignificativo  implements java.io.Serializable {
    private int codigoAmbiente;

    private int codigoMotivoEvento;

    private java.lang.Integer codigoPuntoVenta;

    private java.lang.String codigoSistema;

    private int codigoSucursal;

    private java.lang.String cufd;

    private java.lang.String cufdEvento;

    private java.lang.String cuis;

    private java.lang.String descripcion;

    private java.util.Calendar fechaHoraFinEvento;

    private java.util.Calendar fechaHoraInicioEvento;

    private long nit;

    public SolicitudEventoSignificativo() {
    }

    public SolicitudEventoSignificativo(
           int codigoAmbiente,
           int codigoMotivoEvento,
           java.lang.Integer codigoPuntoVenta,
           java.lang.String codigoSistema,
           int codigoSucursal,
           java.lang.String cufd,
           java.lang.String cufdEvento,
           java.lang.String cuis,
           java.lang.String descripcion,
           java.util.Calendar fechaHoraFinEvento,
           java.util.Calendar fechaHoraInicioEvento,
           long nit) {
           this.codigoAmbiente = codigoAmbiente;
           this.codigoMotivoEvento = codigoMotivoEvento;
           this.codigoPuntoVenta = codigoPuntoVenta;
           this.codigoSistema = codigoSistema;
           this.codigoSucursal = codigoSucursal;
           this.cufd = cufd;
           this.cufdEvento = cufdEvento;
           this.cuis = cuis;
           this.descripcion = descripcion;
           this.fechaHoraFinEvento = fechaHoraFinEvento;
           this.fechaHoraInicioEvento = fechaHoraInicioEvento;
           this.nit = nit;
    }


    /**
     * Gets the codigoAmbiente value for this SolicitudEventoSignificativo.
     *
     * @return codigoAmbiente
     */
    public int getCodigoAmbiente() {
        return codigoAmbiente;
    }


    /**
     * Sets the codigoAmbiente value for this SolicitudEventoSignificativo.
     *
     * @param codigoAmbiente
     */
    public void setCodigoAmbiente(int codigoAmbiente) {
        this.codigoAmbiente = codigoAmbiente;
    }


    /**
     * Gets the codigoMotivoEvento value for this SolicitudEventoSignificativo.
     *
     * @return codigoMotivoEvento
     */
    public int getCodigoMotivoEvento() {
        return codigoMotivoEvento;
    }


    /**
     * Sets the codigoMotivoEvento value for this SolicitudEventoSignificativo.
     *
     * @param codigoMotivoEvento
     */
    public void setCodigoMotivoEvento(int codigoMotivoEvento) {
        this.codigoMotivoEvento = codigoMotivoEvento;
    }


    /**
     * Gets the codigoPuntoVenta value for this SolicitudEventoSignificativo.
     *
     * @return codigoPuntoVenta
     */
    public java.lang.Integer getCodigoPuntoVenta() {
        return codigoPuntoVenta;
    }


    /**
     * Sets the codigoPuntoVenta value for this SolicitudEventoSignificativo.
     *
     * @param codigoPuntoVenta
     */
    public void setCodigoPuntoVenta(java.lang.Integer codigoPuntoVenta) {
        this.codigoPuntoVenta = codigoPuntoVenta;
    }


    /**
     * Gets the codigoSistema value for this SolicitudEventoSignificativo.
     *
     * @return codigoSistema
     */
    public java.lang.String getCodigoSistema() {
        return codigoSistema;
    }


    /**
     * Sets the codigoSistema value for this SolicitudEventoSignificativo.
     *
     * @param codigoSistema
     */
    public void setCodigoSistema(java.lang.String codigoSistema) {
        this.codigoSistema = codigoSistema;
    }


    /**
     * Gets the codigoSucursal value for this SolicitudEventoSignificativo.
     *
     * @return codigoSucursal
     */
    public int getCodigoSucursal() {
        return codigoSucursal;
    }


    /**
     * Sets the codigoSucursal value for this SolicitudEventoSignificativo.
     *
     * @param codigoSucursal
     */
    public void setCodigoSucursal(int codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }


    /**
     * Gets the cufd value for this SolicitudEventoSignificativo.
     *
     * @return cufd
     */
    public java.lang.String getCufd() {
        return cufd;
    }


    /**
     * Sets the cufd value for this SolicitudEventoSignificativo.
     *
     * @param cufd
     */
    public void setCufd(java.lang.String cufd) {
        this.cufd = cufd;
    }


    /**
     * Gets the cufdEvento value for this SolicitudEventoSignificativo.
     *
     * @return cufdEvento
     */
    public java.lang.String getCufdEvento() {
        return cufdEvento;
    }


    /**
     * Sets the cufdEvento value for this SolicitudEventoSignificativo.
     *
     * @param cufdEvento
     */
    public void setCufdEvento(java.lang.String cufdEvento) {
        this.cufdEvento = cufdEvento;
    }


    /**
     * Gets the cuis value for this SolicitudEventoSignificativo.
     *
     * @return cuis
     */
    public java.lang.String getCuis() {
        return cuis;
    }


    /**
     * Sets the cuis value for this SolicitudEventoSignificativo.
     *
     * @param cuis
     */
    public void setCuis(java.lang.String cuis) {
        this.cuis = cuis;
    }


    /**
     * Gets the descripcion value for this SolicitudEventoSignificativo.
     *
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this SolicitudEventoSignificativo.
     *
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the fechaHoraFinEvento value for this SolicitudEventoSignificativo.
     *
     * @return fechaHoraFinEvento
     */
    public java.util.Calendar getFechaHoraFinEvento() {
        return fechaHoraFinEvento;
    }


    /**
     * Sets the fechaHoraFinEvento value for this SolicitudEventoSignificativo.
     *
     * @param fechaFin
     */
    public void setFechaHoraFinEvento(Calendar fechaFin) {
        this.fechaHoraFinEvento = fechaFin;
    }


    /**
     * Gets the fechaHoraInicioEvento value for this SolicitudEventoSignificativo.
     *
     * @return fechaHoraInicioEvento
     */
    public java.util.Calendar getFechaHoraInicioEvento() {
        return fechaHoraInicioEvento;
    }


    /**
     * Sets the fechaHoraInicioEvento value for this SolicitudEventoSignificativo.
     *
     * @param fechaInicio
     */
    public void setFechaHoraInicioEvento(Calendar fechaInicio) {
        this.fechaHoraInicioEvento = fechaInicio;
    }


    /**
     * Gets the nit value for this SolicitudEventoSignificativo.
     *
     * @return nit
     */
    public long getNit() {
        return nit;
    }


    /**
     * Sets the nit value for this SolicitudEventoSignificativo.
     *
     * @param nit
     */
    public void setNit(long nit) {
        this.nit = nit;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SolicitudEventoSignificativo)) return false;
        SolicitudEventoSignificativo other = (SolicitudEventoSignificativo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            this.codigoAmbiente == other.getCodigoAmbiente() &&
            this.codigoMotivoEvento == other.getCodigoMotivoEvento() &&
            ((this.codigoPuntoVenta==null && other.getCodigoPuntoVenta()==null) ||
             (this.codigoPuntoVenta!=null &&
              this.codigoPuntoVenta.equals(other.getCodigoPuntoVenta()))) &&
            ((this.codigoSistema==null && other.getCodigoSistema()==null) ||
             (this.codigoSistema!=null &&
              this.codigoSistema.equals(other.getCodigoSistema()))) &&
            this.codigoSucursal == other.getCodigoSucursal() &&
            ((this.cufd==null && other.getCufd()==null) ||
             (this.cufd!=null &&
              this.cufd.equals(other.getCufd()))) &&
            ((this.cufdEvento==null && other.getCufdEvento()==null) ||
             (this.cufdEvento!=null &&
              this.cufdEvento.equals(other.getCufdEvento()))) &&
            ((this.cuis==null && other.getCuis()==null) ||
             (this.cuis!=null &&
              this.cuis.equals(other.getCuis()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) ||
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.fechaHoraFinEvento==null && other.getFechaHoraFinEvento()==null) ||
             (this.fechaHoraFinEvento!=null &&
              this.fechaHoraFinEvento.equals(other.getFechaHoraFinEvento()))) &&
            ((this.fechaHoraInicioEvento==null && other.getFechaHoraInicioEvento()==null) ||
             (this.fechaHoraInicioEvento!=null &&
              this.fechaHoraInicioEvento.equals(other.getFechaHoraInicioEvento()))) &&
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
        _hashCode += getCodigoMotivoEvento();
        if (getCodigoPuntoVenta() != null) {
            _hashCode += getCodigoPuntoVenta().hashCode();
        }
        if (getCodigoSistema() != null) {
            _hashCode += getCodigoSistema().hashCode();
        }
        _hashCode += getCodigoSucursal();
        if (getCufd() != null) {
            _hashCode += getCufd().hashCode();
        }
        if (getCufdEvento() != null) {
            _hashCode += getCufdEvento().hashCode();
        }
        if (getCuis() != null) {
            _hashCode += getCuis().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getFechaHoraFinEvento() != null) {
            _hashCode += getFechaHoraFinEvento().hashCode();
        }
        if (getFechaHoraInicioEvento() != null) {
            _hashCode += getFechaHoraInicioEvento().hashCode();
        }
        _hashCode += new Long(getNit()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SolicitudEventoSignificativo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudEventoSignificativo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoAmbiente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoAmbiente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoMotivoEvento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoMotivoEvento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoPuntoVenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoPuntoVenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
        elemField.setFieldName("cufd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cufd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cufdEvento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cufdEvento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("fechaHoraFinEvento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaHoraFinEvento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaHoraInicioEvento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaHoraInicioEvento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
