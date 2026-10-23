package xyz.cesarnvf.aigfec.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Descuentos {

    private Long iddescuento;
    private String descuento;
    private LocalDate fecha;
    private BigDecimal cantidadinicial;
    private BigDecimal cantidadfinal;
    private Float porcentaje;
    private Long idgrupo;
    private Long idproducto;
    private Long idcliente;
    private Integer opcion;

}
