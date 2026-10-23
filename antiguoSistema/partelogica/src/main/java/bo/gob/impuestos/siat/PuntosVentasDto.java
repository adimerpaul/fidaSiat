/**
 * PuntosVentasDto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bo.gob.impuestos.siat;

public class PuntosVentasDto  implements java.io.Serializable {
    private java.lang.Integer codigoPuntoVenta;

    private java.lang.String nombrePuntoVenta;

    private java.lang.String tipoPuntoVenta;

    public PuntosVentasDto() {
    }

    public PuntosVentasDto(
           java.lang.Integer codigoPuntoVenta,
           java.lang.String nombrePuntoVenta,
           java.lang.String tipoPuntoVenta) {
           this.codigoPuntoVenta = codigoPuntoVenta;
           this.nombrePuntoVenta = nombrePuntoVenta;
           this.tipoPuntoVenta = tipoPuntoVenta;
    }


    /**
     * Gets the codigoPuntoVenta value for this PuntosVentasDto.
     *
     * @return codigoPuntoVenta
     */
    public java.lang.Integer getCodigoPuntoVenta() {
        return codigoPuntoVenta;
    }


    /**
     * Sets the codigoPuntoVenta value for this PuntosVentasDto.
     *
     * @param codigoPuntoVenta
     */
    public void setCodigoPuntoVenta(java.lang.Integer codigoPuntoVenta) {
        this.codigoPuntoVenta = codigoPuntoVenta;
    }


    /**
     * Gets the nombrePuntoVenta value for this PuntosVentasDto.
     *
     * @return nombrePuntoVenta
     */
    public java.lang.String getNombrePuntoVenta() {
        return nombrePuntoVenta;
    }


    /**
     * Sets the nombrePuntoVenta value for this PuntosVentasDto.
     *
     * @param nombrePuntoVenta
     */
    public void setNombrePuntoVenta(java.lang.String nombrePuntoVenta) {
        this.nombrePuntoVenta = nombrePuntoVenta;
    }


    /**
     * Gets the tipoPuntoVenta value for this PuntosVentasDto.
     *
     * @return tipoPuntoVenta
     */
    public java.lang.String getTipoPuntoVenta() {
        return tipoPuntoVenta;
    }


    /**
     * Sets the tipoPuntoVenta value for this PuntosVentasDto.
     *
     * @param tipoPuntoVenta
     */
    public void setTipoPuntoVenta(java.lang.String tipoPuntoVenta) {
        this.tipoPuntoVenta = tipoPuntoVenta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PuntosVentasDto)) return false;
        PuntosVentasDto other = (PuntosVentasDto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.codigoPuntoVenta==null && other.getCodigoPuntoVenta()==null) ||
             (this.codigoPuntoVenta!=null &&
              this.codigoPuntoVenta.equals(other.getCodigoPuntoVenta()))) &&
            ((this.nombrePuntoVenta==null && other.getNombrePuntoVenta()==null) ||
             (this.nombrePuntoVenta!=null &&
              this.nombrePuntoVenta.equals(other.getNombrePuntoVenta()))) &&
            ((this.tipoPuntoVenta==null && other.getTipoPuntoVenta()==null) ||
             (this.tipoPuntoVenta!=null &&
              this.tipoPuntoVenta.equals(other.getTipoPuntoVenta())));
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
        if (getCodigoPuntoVenta() != null) {
            _hashCode += getCodigoPuntoVenta().hashCode();
        }
        if (getNombrePuntoVenta() != null) {
            _hashCode += getNombrePuntoVenta().hashCode();
        }
        if (getTipoPuntoVenta() != null) {
            _hashCode += getTipoPuntoVenta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PuntosVentasDto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "puntosVentasDto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoPuntoVenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoPuntoVenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombrePuntoVenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombrePuntoVenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPuntoVenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoPuntoVenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
