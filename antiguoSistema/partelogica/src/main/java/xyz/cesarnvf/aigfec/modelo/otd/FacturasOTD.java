package xyz.cesarnvf.aigfec.modelo.otd;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturasOTD {

    private Long idfactura;
    private Long idusuario;
    private Long idcliente;
    private Long idpuntoventa;
    private Long iddocumentoidentidadsin;
    private Integer codigosin;
    private String documentoidentidadsin;
    private String cliente;
    private String numerodocumento;
    private String complemento;
    private String correoelectronico;
    private String celular;
    private Long idmetodopagosin;
    private BigDecimal total;
    private BigDecimal descuento;
    private BigDecimal efectivo;
    private BigDecimal cambio;
    private LocalDateTime fechaemision;
    private Integer codigo;
    private String estado;
    private String leyenda;
    private Long numerofactura;
    private String cufd;
    private String cuf;
    private List<DetallesOTD> detalles;

}
