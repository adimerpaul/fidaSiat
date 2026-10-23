package xyz.cesarnvf.aigfec.modelo.otd;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescuentosOTD {
    private Long iddescuento;
    private String descuento;
    private LocalDate fecha;
    private BigDecimal cantidadinicial;
    private BigDecimal cantidadfinal;
    private Float porcentaje;
    private Long idgrupo;
    private String grupo;
    private Long idproducto;
    private String producto;
    private Long idcliente;
    private String cliente;
    private String numerodocumento;
    private Integer opcion;

}
