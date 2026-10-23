package xyz.cesarnvf.aigfec.modelo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Kardex {

    private Long id_pedido;
    private Long id_kardex;
    private Long id_producto;
    private BigDecimal cantidad;
    private BigDecimal preciounitario;
    private BigDecimal descuento;
    private Integer prioridad;
}

