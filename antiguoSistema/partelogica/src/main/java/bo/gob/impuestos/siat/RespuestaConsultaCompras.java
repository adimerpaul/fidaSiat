/**
 * RespuestaConsultaCompras.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bo.gob.impuestos.siat;

public class RespuestaConsultaCompras  extends bo.gob.impuestos.siat.ModelDto  implements java.io.Serializable {
    private byte[] archivo;

    private bo.gob.impuestos.siat.MensajeRecepcion[] mensajesList;

    private java.lang.Boolean transaccion;

    public RespuestaConsultaCompras() {
    }

    public RespuestaConsultaCompras(
           byte[] archivo,
           bo.gob.impuestos.siat.MensajeRecepcion[] mensajesList,
           java.lang.Boolean transaccion) {
        this.archivo = archivo;
        this.mensajesList = mensajesList;
        this.transaccion = transaccion;
    }


    /**
     * Gets the archivo value for this RespuestaConsultaCompras.
     * 
     * @return archivo
     */
    public byte[] getArchivo() {
        return archivo;
    }


    /**
     * Sets the archivo value for this RespuestaConsultaCompras.
     * 
     * @param archivo
     */
    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }


    /**
     * Gets the mensajesList value for this RespuestaConsultaCompras.
     * 
     * @return mensajesList
     */
    public bo.gob.impuestos.siat.MensajeRecepcion[] getMensajesList() {
        return mensajesList;
    }


    /**
     * Sets the mensajesList value for this RespuestaConsultaCompras.
     * 
     * @param mensajesList
     */
    public void setMensajesList(bo.gob.impuestos.siat.MensajeRecepcion[] mensajesList) {
        this.mensajesList = mensajesList;
    }

    public bo.gob.impuestos.siat.MensajeRecepcion getMensajesList(int i) {
        return this.mensajesList[i];
    }

    public void setMensajesList(int i, bo.gob.impuestos.siat.MensajeRecepcion _value) {
        this.mensajesList[i] = _value;
    }


    /**
     * Gets the transaccion value for this RespuestaConsultaCompras.
     * 
     * @return transaccion
     */
    public java.lang.Boolean getTransaccion() {
        return transaccion;
    }


    /**
     * Sets the transaccion value for this RespuestaConsultaCompras.
     * 
     * @param transaccion
     */
    public void setTransaccion(java.lang.Boolean transaccion) {
        this.transaccion = transaccion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaConsultaCompras)) return false;
        RespuestaConsultaCompras other = (RespuestaConsultaCompras) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.archivo==null && other.getArchivo()==null) || 
             (this.archivo!=null &&
              java.util.Arrays.equals(this.archivo, other.getArchivo()))) &&
            ((this.mensajesList==null && other.getMensajesList()==null) || 
             (this.mensajesList!=null &&
              java.util.Arrays.equals(this.mensajesList, other.getMensajesList()))) &&
            ((this.transaccion==null && other.getTransaccion()==null) || 
             (this.transaccion!=null &&
              this.transaccion.equals(other.getTransaccion())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getArchivo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArchivo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArchivo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMensajesList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMensajesList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMensajesList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTransaccion() != null) {
            _hashCode += getTransaccion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespuestaConsultaCompras.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "respuestaConsultaCompras"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archivo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "archivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensajesList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensajesList"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "mensajeRecepcion"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transaccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transaccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
