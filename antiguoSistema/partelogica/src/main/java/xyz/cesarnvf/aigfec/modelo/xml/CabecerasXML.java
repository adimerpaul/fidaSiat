package xyz.cesarnvf.aigfec.modelo.xml;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import lombok.Getter;
import xyz.cesarnvf.aigfec.modelo.otd.ClientesOTD;
import xyz.cesarnvf.aigfec.modelo.otd.PuntosventasOTD;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cabecera", propOrder = {
        "nitEmisor",
        "razonSocialEmisor",
        "municipio",
        "telefono",
        "numeroFactura",
        "cuf",
        "cufd",
        "codigoSucursal",
        "direccion",
        "codigoPuntoVenta",
        "fechaEmision",
        "nombreRazonSocial",
        "codigoTipoDocumentoIdentidad",
        "numeroDocumento",
        "complemento",
        "codigoCliente",
        "codigoMetodoPago",
        "numeroTarjeta",
        "montoTotal",
        "montoTotalSujetoIva",
        "codigoMoneda",
        "tipoCambio",
        "montoTotalMoneda",
        "montoGiftCard",
        "descuentoAdicional",
        "codigoExcepcion",
        "cafc",
        "leyenda",
        "usuario",
        "codigoDocumentoSector"
})
@XmlRootElement(name = "cabecera")
public class CabecerasXML {

    private CabecerasXML() {}

    private long nitEmisor;

    @XmlElement(required = true)
    private String razonSocialEmisor;

    @XmlElement(required = true)
    private String municipio;

    @XmlElement(required = true, nillable = true)
    private String telefono;

    private long numeroFactura;

    @XmlElement(required = true)
    private String cuf;

    @XmlElement(required = true)
    private String cufd;

    private int codigoSucursal;

    @XmlElement(required = true)
    private String direccion;

    @XmlElement(required = true, type = Integer.class, nillable = true)
    private Integer codigoPuntoVenta;

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    private XMLGregorianCalendar fechaEmision;

    @XmlElement(required = true, nillable = true)
    private String nombreRazonSocial;

    private int codigoTipoDocumentoIdentidad;

    @XmlElement(required = true)
    private String numeroDocumento;

    @XmlElement(required = true, nillable = true)
    private String complemento;

    @XmlElement(required = true)
    private String codigoCliente;

    private int codigoMetodoPago;

    @XmlElement(required = true, type = Long.class, nillable = true)
    private Long numeroTarjeta;

    @XmlElement(required = true)
    private BigDecimal montoTotal;

    @XmlElement(required = true)
    private BigDecimal montoTotalSujetoIva;

    private int codigoMoneda;

    @XmlElement(required = true)
    private BigDecimal tipoCambio;

    @XmlElement(required = true)
    private BigDecimal montoTotalMoneda;

    @XmlElement(required = true, nillable = true)
    protected BigDecimal montoGiftCard;

    @XmlElement(required = true, nillable = true)
    private BigDecimal descuentoAdicional;

    @XmlElement(required = true, type = Integer.class, nillable = true)
    private Integer codigoExcepcion;

    @XmlElement(required = true, nillable = true)
    private String cafc;

    @XmlElement(required = true)
    private String leyenda;

    @XmlElement(required = true)
    private String usuario;

    @XmlElement(required = true)
    private Integer codigoDocumentoSector;

    public void setCuf(String cuf) {
        this.cuf = cuf;
    }

    public void setCufd(String cufd) {
        this.cufd = cufd;
    }

    public static class Builder {

        private CabecerasXML cabeceras;

        public Builder() {
            cabeceras = new CabecerasXML();
        }

        public Builder buildEmpresa(PuntosventasOTD puntoVenta) {
            cabeceras.nitEmisor = puntoVenta.getNit();
            cabeceras.razonSocialEmisor = puntoVenta.getRazonsocial();
            cabeceras.municipio = puntoVenta.getMunicipio();
            cabeceras.telefono = puntoVenta.getTelefono();
            cabeceras.codigoSucursal = puntoVenta.getSucursal();
            cabeceras.direccion = puntoVenta.getDireccion();
            cabeceras.codigoPuntoVenta = puntoVenta.getPuntoventa();
            return this;
        }

        public Builder buildCliente(ClientesOTD cliente) {

            cabeceras.nombreRazonSocial = cliente.getCliente();
            if (cliente.getComplemento() != null) {
                cabeceras.codigoTipoDocumentoIdentidad = cliente.getCodigosin().intValue();
                cabeceras.complemento = cliente.getComplemento();
            } else {
                cabeceras.codigoTipoDocumentoIdentidad = cliente.getCodigosin().intValue();
            }
            cabeceras.numeroDocumento = cliente.getNumerodocumento();
            cabeceras.codigoCliente = cliente.getIdcliente().toString();

            return this;
        }

        public Builder buildPago(BigDecimal total, BigDecimal descuento) {

            cabeceras.codigoMetodoPago = 1;
            cabeceras.numeroTarjeta = null;
            cabeceras.montoTotal = total;
            cabeceras.montoTotalSujetoIva = total;
            cabeceras.codigoMoneda = 1;
            cabeceras.tipoCambio = new BigDecimal("1");
            cabeceras.montoTotalMoneda = total;
            cabeceras.descuentoAdicional = descuento;

            return this;
        }

        public Builder setUsuario(String usuario) {

            cabeceras.usuario = usuario;
            return this;
        }

        public Builder setNumeroFactura(long numeroFactura) {

            cabeceras.numeroFactura = numeroFactura;
            return this;
        }

        public Builder setCuf(String cuf) {

            cabeceras.cuf = cuf;
            return this;
        }

        public Builder setFechaEmision(XMLGregorianCalendar fechaEmision) {
            cabeceras.fechaEmision = fechaEmision;
            return this;
        }

        public Builder setLeyenda(String leyenda) {
            cabeceras.leyenda = leyenda;
            return this;
        }

        public Builder setNitClienteExcepcion(Boolean permitirNitInvalido) {
            cabeceras.codigoExcepcion = permitirNitInvalido ? 1 : 0;
            return this;
        }

        public CabecerasXML build() {

            cabeceras.codigoDocumentoSector = 1;
            return cabeceras;
        }

        public Builder setCafc(String cafc) {
            cabeceras.cafc = cafc;
            return this;
        }
    }
}
