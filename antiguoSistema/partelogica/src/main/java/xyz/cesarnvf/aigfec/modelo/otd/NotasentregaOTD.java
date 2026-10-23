package xyz.cesarnvf.aigfec.modelo.otd;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotasentregaOTD {

    private Long id_pedido;
    private String nombres;
    private String nit;
    private String num_cladoc;
    private String cadena1;
    private String cadena2;
    private String cadena3;
    private BigDecimal precio_total;
    private Boolean sw;

}
