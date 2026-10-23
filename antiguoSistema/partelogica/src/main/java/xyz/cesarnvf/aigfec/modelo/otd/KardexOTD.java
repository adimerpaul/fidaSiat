package xyz.cesarnvf.aigfec.modelo.otd;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KardexOTD {

    private Long id_pedido;
    private Long id_kardex;
    private Long id_producto;
    private String producto;
    private BigDecimal cantidad;
    private BigDecimal preciounitario;
    private BigDecimal descuento;
    private Integer prioridad;
}
