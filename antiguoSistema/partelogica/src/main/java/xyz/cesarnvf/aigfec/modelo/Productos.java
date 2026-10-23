package xyz.cesarnvf.aigfec.modelo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Productos {

    private Long idproducto;
    private Long idproductosin;
    private Long idgrupo;
    private Long idunidadmedida;
    private String producto;
    private Integer cantidadcaja;
    private BigDecimal preciounitario;
    private BigDecimal descuento;
    private String enlaceproducto;
    private Boolean habilitado;
    private Integer codigocorto;

}
