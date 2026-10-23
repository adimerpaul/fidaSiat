package xyz.cesarnvf.aigfec.modelo.otd;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductosOTD {

    private Long idproducto;
    private Long idproductosin;
    private Integer codigosin;
    private Integer codigoactividad;
    private Long idgrupo;
    private String grupo;
    private Long idunidadmedida;
    private Integer unidadmedidacodigosin;
    private String unidadmedida;
    private String producto;
    private Integer cantidadcaja;
    private BigDecimal preciounitario;
    private String enlaceproducto;
    private Boolean habilitado;
    private Integer codigocorto;
    private BigDecimal descuento;

}
