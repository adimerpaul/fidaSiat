/**
 * SolicitudRecepcionCompras.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bo.gob.impuestos.siat;

public class SolicitudRecepcionCompras  extends bo.gob.impuestos.siat.SolicitudCompras  implements java.io.Serializable {
    private byte[] archivo;

    private int cantidadFacturas;

    private java.util.Calendar fechaEnvio;

    private int gestion;

    private java.lang.String hashArchivo;

    private int periodo;

    public SolicitudRecepcionCompras() {
    }

    public SolicitudRecepcionCompras(
           int codigoAmbiente,
           int codigoPuntoVenta,
           java.lang.String codigoSistema,
           int codigoSucursal,
           java.lang.String cufd,
           java.lang.String cuis,
           long nit,
           byte[] archivo,
           int cantidadFacturas,
           java.util.Calendar fechaEnvio,
           int gestion,
           java.lang.String hashArchivo,
           int periodo) {
        super(
            codigoAmbiente,
            codigoPuntoVenta,
            codigoSistema,
            codigoSucursal,
            cufd,
            cuis,
            nit);
        this.archivo = archivo;
        this.cantidadFacturas = cantidadFacturas;
        this.fechaEnvio = fechaEnvio;
        this.gestion = gestion;
        this.hashArchivo = hashArchivo;
        this.periodo = periodo;
    }


    /**
     * Gets the archivo value for this SolicitudRecepcionCompras.
     * 
     * @return archivo
     */
    public byte[] getArchivo() {
        return archivo;
    }


    /**
     * Sets the archivo value for this SolicitudRecepcionCompras.
     * 
     * @param archivo
     */
    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }


    /**
     * Gets the cantidadFacturas value for this SolicitudRecepcionCompras.
     * 
     * @return cantidadFacturas
     */
    public int getCantidadFacturas() {
        return cantidadFacturas;
    }


    /**
     * Sets the cantidadFacturas value for this SolicitudRecepcionCompras.
     * 
     * @param cantidadFacturas
     */
    public void setCantidadFacturas(int cantidadFacturas) {
        this.cantidadFacturas = cantidadFacturas;
    }


    /**
     * Gets the fechaEnvio value for this SolicitudRecepcionCompras.
     * 
     * @return fechaEnvio
     */
    public java.util.Calendar getFechaEnvio() {
        return fechaEnvio;
    }


    /**
     * Sets the fechaEnvio value for this SolicitudRecepcionCompras.
     * 
     * @param fechaEnvio
     */
    public void setFechaEnvio(java.util.Calendar fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }


    /**
     * Gets the gestion value for this SolicitudRecepcionCompras.
     * 
     * @return gestion
     */
    public int getGestion() {
        return gestion;
    }


    /**
     * Sets the gestion value for this SolicitudRecepcionCompras.
     * 
     * @param gestion
     */
    public void setGestion(int gestion) {
        this.gestion = gestion;
    }


    /**
     * Gets the hashArchivo value for this SolicitudRecepcionCompras.
     * 
     * @return hashArchivo
     */
    public java.lang.String getHashArchivo() {
        return hashArchivo;
    }


    /**
     * Sets the hashArchivo value for this SolicitudRecepcionCompras.
     * 
     * @param hashArchivo
     */
    public void setHashArchivo(java.lang.String hashArchivo) {
        this.hashArchivo = hashArchivo;
    }


    /**
     * Gets the periodo value for this SolicitudRecepcionCompras.
     * 
     * @return periodo
     */
    public int getPeriodo() {
        return periodo;
    }


    /**
     * Sets the periodo value for this SolicitudRecepcionCompras.
     * 
     * @param periodo
     */
    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SolicitudRecepcionCompras)) return false;
        SolicitudRecepcionCompras other = (SolicitudRecepcionCompras) obj;
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
            this.cantidadFacturas == other.getCantidadFacturas() &&
            ((this.fechaEnvio==null && other.getFechaEnvio()==null) || 
             (this.fechaEnvio!=null &&
              this.fechaEnvio.equals(other.getFechaEnvio()))) &&
            this.gestion == other.getGestion() &&
            ((this.hashArchivo==null && other.getHashArchivo()==null) || 
             (this.hashArchivo!=null &&
              this.hashArchivo.equals(other.getHashArchivo()))) &&
            this.periodo == other.getPeriodo();
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
        _hashCode += getCantidadFacturas();
        if (getFechaEnvio() != null) {
            _hashCode += getFechaEnvio().hashCode();
        }
        _hashCode += getGestion();
        if (getHashArchivo() != null) {
            _hashCode += getHashArchivo().hashCode();
        }
        _hashCode += getPeriodo();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SolicitudRecepcionCompras.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudRecepcionCompras"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archivo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "archivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadFacturas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantidadFacturas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaEnvio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaEnvio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gestion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gestion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hashArchivo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hashArchivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "periodo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
