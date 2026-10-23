package xyz.cesarnvf.aigfec.modelo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Detalles {

    private Long iddetalle;
    private Long idfactura;
    private Long idgrupo;
    private Long idproducto;
    private BigDecimal cantidad;
    private BigDecimal preciounitario;
    private BigDecimal descuento;
}
