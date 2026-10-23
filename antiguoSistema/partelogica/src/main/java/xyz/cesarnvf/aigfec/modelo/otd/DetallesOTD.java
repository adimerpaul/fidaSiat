package xyz.cesarnvf.aigfec.modelo.otd;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallesOTD {

    private Long iddetalle;
    private Long idfactura;
    private Long idgrupo;
    private Long idproducto;
    private BigDecimal cantidad;
    private BigDecimal preciounitario;
    private BigDecimal descuento;
}
