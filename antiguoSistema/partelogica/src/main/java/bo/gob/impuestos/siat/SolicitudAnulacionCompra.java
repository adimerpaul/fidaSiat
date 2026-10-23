/**
 * SolicitudAnulacionCompra.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bo.gob.impuestos.siat;

public class SolicitudAnulacionCompra  extends bo.gob.impuestos.siat.SolicitudCompras  implements java.io.Serializable {
    private java.lang.String codAutorizacion;

    private long nitProveedor;

    private java.lang.String nroDuiDim;

    private long nroFactura;

    public SolicitudAnulacionCompra() {
    }

    public SolicitudAnulacionCompra(
           int codigoAmbiente,
           int codigoPuntoVenta,
           java.lang.String codigoSistema,
           int codigoSucursal,
           java.lang.String cufd,
           java.lang.String cuis,
           long nit,
           java.lang.String codAutorizacion,
           long nitProveedor,
           java.lang.String nroDuiDim,
           long nroFactura) {
        super(
            codigoAmbiente,
            codigoPuntoVenta,
            codigoSistema,
            codigoSucursal,
            cufd,
            cuis,
            nit);
        this.codAutorizacion = codAutorizacion;
        this.nitProveedor = nitProveedor;
        this.nroDuiDim = nroDuiDim;
        this.nroFactura = nroFactura;
    }


    /**
     * Gets the codAutorizacion value for this SolicitudAnulacionCompra.
     * 
     * @return codAutorizacion
     */
    public java.lang.String getCodAutorizacion() {
        return codAutorizacion;
    }


    /**
     * Sets the codAutorizacion value for this SolicitudAnulacionCompra.
     * 
     * @param codAutorizacion
     */
    public void setCodAutorizacion(java.lang.String codAutorizacion) {
        this.codAutorizacion = codAutorizacion;
    }


    /**
     * Gets the nitProveedor value for this SolicitudAnulacionCompra.
     * 
     * @return nitProveedor
     */
    public long getNitProveedor() {
        return nitProveedor;
    }


    /**
     * Sets the nitProveedor value for this SolicitudAnulacionCompra.
     * 
     * @param nitProveedor
     */
    public void setNitProveedor(long nitProveedor) {
        this.nitProveedor = nitProveedor;
    }


    /**
     * Gets the nroDuiDim value for this SolicitudAnulacionCompra.
     * 
     * @return nroDuiDim
     */
    public java.lang.String getNroDuiDim() {
        return nroDuiDim;
    }


    /**
     * Sets the nroDuiDim value for this SolicitudAnulacionCompra.
     * 
     * @param nroDuiDim
     */
    public void setNroDuiDim(java.lang.String nroDuiDim) {
        this.nroDuiDim = nroDuiDim;
    }


    /**
     * Gets the nroFactura value for this SolicitudAnulacionCompra.
     * 
     * @return nroFactura
     */
    public long getNroFactura() {
        return nroFactura;
    }


    /**
     * Sets the nroFactura value for this SolicitudAnulacionCompra.
     * 
     * @param nroFactura
     */
    public void setNroFactura(long nroFactura) {
        this.nroFactura = nroFactura;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SolicitudAnulacionCompra)) return false;
        SolicitudAnulacionCompra other = (SolicitudAnulacionCompra) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codAutorizacion==null && other.getCodAutorizacion()==null) || 
             (this.codAutorizacion!=null &&
              this.codAutorizacion.equals(other.getCodAutorizacion()))) &&
            this.nitProveedor == other.getNitProveedor() &&
            ((this.nroDuiDim==null && other.getNroDuiDim()==null) || 
             (this.nroDuiDim!=null &&
              this.nroDuiDim.equals(other.getNroDuiDim()))) &&
            this.nroFactura == other.getNroFactura();
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
        if (getCodAutorizacion() != null) {
            _hashCode += getCodAutorizacion().hashCode();
        }
        _hashCode += new Long(getNitProveedor()).hashCode();
        if (getNroDuiDim() != null) {
            _hashCode += getNroDuiDim().hashCode();
        }
        _hashCode += new Long(getNroFactura()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SolicitudAnulacionCompra.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://siat.impuestos.gob.bo/", "solicitudAnulacionCompra"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codAutorizacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codAutorizacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nitProveedor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nitProveedor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nroDuiDim");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nroDuiDim"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nroFactura");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nroFactura"));
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
