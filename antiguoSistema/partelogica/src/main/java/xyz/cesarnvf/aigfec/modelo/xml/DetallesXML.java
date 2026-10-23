package xyz.cesarnvf.aigfec.modelo.xml;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import xyz.cesarnvf.aigfec.modelo.otd.ProductosOTD;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detalles", propOrder = {
        "actividadEconomica",
        "codigoProductoSin",
        "codigoProducto",
        "descripcion",
        "cantidad",
        "unidadMedida",
        "precioUnitario",
        "montoDescuento",
        "subTotal",
        "numeroSerie",
        "numeroImei"
})
@XmlRootElement(name = "detalles")
public class DetallesXML {

    private DetallesXML() {
    }

    @XmlElement(required = true)
    private String actividadEconomica;

    private int codigoProductoSin;

    @XmlElement(required = true)
    private String codigoProducto;

    @XmlElement(required = true)
    private String descripcion;

    @XmlElement(required = true)
    private BigDecimal cantidad;

    private int unidadMedida;

    @XmlElement(required = true)
    private BigDecimal precioUnitario;

    @XmlElement(required = true, nillable = true)
    private BigDecimal montoDescuento;

    @XmlElement(required = true)
    private BigDecimal subTotal;

    @XmlElement(nillable = true)
    protected String numeroSerie;

    @XmlElement(nillable = true)
    protected String numeroImei;

    public static class Builder {

        private DetallesXML detalles;

        public Builder() {
            this.detalles = new DetallesXML();
        }

        public Builder buildItem(
            ProductosOTD producto,
            String codigoActividad,
            BigDecimal cantidad,
            BigDecimal montoDescuento
        ) {
            this.detalles.actividadEconomica = codigoActividad;
            this.detalles.codigoProductoSin = producto.getCodigosin();
            this.detalles.codigoProducto = producto.getIdproducto().toString();
            this.detalles.descripcion = producto.getProducto();
            this.detalles.unidadMedida = producto.getUnidadmedidacodigosin();
            this.detalles.precioUnitario = producto.getPreciounitario();
            this.detalles.cantidad = cantidad;
            this.detalles.montoDescuento = producto.getDescuento();
            BigDecimal subTotal = (producto.getPreciounitario().multiply(cantidad)).subtract(producto.getDescuento());
            this.detalles.subTotal = subTotal;
            return this;
        }

        public Builder setNumeroSerie(String numeroSerie) {
            this.detalles.numeroSerie = numeroSerie;
            return this;
        }

        public Builder setNumeroImei(String numeroImei) {
            this.detalles.numeroImei = numeroImei;
            return this;
        }

        public DetallesXML build() {
            return this.detalles;
        }
    }
}
