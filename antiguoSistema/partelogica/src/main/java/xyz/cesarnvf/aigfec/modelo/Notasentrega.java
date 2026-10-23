package xyz.cesarnvf.aigfec.modelo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Notasentrega {

    private Long id_pedido;
    private String nombres;
    private String nit;
    private String num_cladoc;
    private String cadena1;
    private String cadena2;
    private String cadena3;
    private BigDecimal precio_total;
}
