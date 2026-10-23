/**
 * EventosSignificativosDto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bo.gob.impuestos.siat;

public class EventosSignificativosDto  implements java.io.Serializable {
    private java.lang.Integer codigoEvento;

    private java.lang.Long codigoRecepcionEventoSignificativo;

    private java.lang.String descripcion;

    private java.lang.String fechaFin;

    private java.lang.String fechaInicio;

    public EventosSignificativosDto() {
    }

    public EventosSignificativosDto(
           java.lang.Integer codigoEvento,
           java.lang.Long codigoRecepcionEventoSignificativo,
           java.lang.String descripcion,
           java.lang.String fechaFin,
           java.lang.String fechaInicio) {
           this.codigoEvento = codigoEvento;
           this.codigoRecepcionEventoSignificativo = codigoRecepcionEventoSignificativo;
           this.descripcion = descripcion;
           this.fechaFin = fechaFin;
           this.fechaInicio = fechaInicio;
    }


    /**
     * Gets the codigoEvento value for this EventosSignificativosDto.
     *
     * @return codigoEvento
     */
    public java.lang.Integer getCodigoEvento() {
        return codigoEvento;
    }


    /**
     * Sets the codigoEvento value for this EventosSignificativosDto.
     *
     * @param codigoEvento
     */
    public void setCodigoEvento(java.lang.Integer codigoEvento) {
        this.codigoEvento = codigoEvento;
    }


    /**
     * Gets the codigoRecepcionEventoSignificativo value for this EventosSignificativosDto.
     *
     * @return codigoRecepcionEventoSignificativo
     */
    public java.lang.Long getCodigoRecepcionEventoSignificativo() {
        return codigoRecepcionEventoSignificativo;
    }


    /**
     * Sets the codigoRecepcionEventoSignificativo value for this EventosSignificativosDto.
     *
     * @param codigoRecepcionEventoSignificativo
     */
    public void setCodigoRecepcionEventoSignificativo(java.lang.Long codigoRecepcionEventoSignificativo) {
        this.codigoRecepcionEventoSignificativo = codigoRecepcionEventoSignificativo;
    }


    /**
     * Gets the descripcion value for this EventosSignificativosDto.
     *
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this EventosSignificativosDto.
     *
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the fechaFin value for this EventosSignificativosDto.
     *
     * @return fechaFin
     */
    public java.lang.String getFechaFin() {
        return fechaFin;
    }


    /**
     * Sets the fechaFin value for this EventosSignificativosDto.
     *
     * @param fechaFin
     */
    public void setFechaFin(java.lang.String fechaFin) {
        this.fechaFin = fechaFin;
    }


    /**
     * Gets the fechaInicio value for this EventosSignificativosDto.
     *
     * @return fechaInicio
     */
    public java.lang.String getFechaInicio() {
        return fechaInicio;
    }


    /**
     * Sets the fechaInicio value for this EventosSignificativosDto.
     *
     * @param fechaInicio
     */
    public void setFechaInicio(java.lang.String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EventosSignificativosDto)) return false;
        EventosSignificativosDto other = (EventosSignificativosDto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.codigoEvento==null && other.getCodigoEvento()==null) ||
             (this.codigoEvento!=null &&
              this.codigoEvento.equals(other.getCodigoEvento()))) &&
            ((this.codigoRecepcionEventoSignificativo==null && other.getCodigoRecepcionEventoSignificativo()==null) ||
             (this.codigoRecepcionEventoSignificativo!=null &&
              this.codigoRecepcionEventoSignificativo.equals(other.getCodigoRecepcionEventoSignificativo()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) ||
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.fechaFin==null && other.getFechaFin()==null) ||
             (this.fechaFin!=null &&
              this.fechaFin.equals(other.getFechaFin()))) &&
            ((this.fechaInicio==null && other.getFechaInicio()==null) ||
             (this.fechaInicio!=null &&
              this.fechaInicio.equals(other.getFechaInicio())));
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
        if (getCodigoEvento() != null) {
            _hashCode += getCodigoEvento().hashCode();
        }
        if (getCodigoRecepcionEventoSignificativo() != null) {
            _hashCode += getCodigoRecepcionEventoSignificativo().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EventosSignificativosDto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "eventosSignificativosDto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEvento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEvento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoRecepcionEventoSignificativo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoRecepcionEventoSignificativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaFin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaFin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInicio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaInicio"));
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
