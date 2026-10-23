package xyz.cesarnvf.aigfec.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Facturas {

    private Long idfactura;
    private Long idusuario;
    private Long idcliente;
    private Long idpuntoventa;
    private Long idmetodopagosin;
    private Long numerofactura;
    private String cuf;
    private String cufd;
    private BigDecimal total;
    private BigDecimal descuento;
    private BigDecimal efectivo;
    private BigDecimal cambio;
    private LocalDateTime fechaemision;
    private Integer codigo;
    private String estado;
    private String leyenda;
    private Long idpedido;
}
