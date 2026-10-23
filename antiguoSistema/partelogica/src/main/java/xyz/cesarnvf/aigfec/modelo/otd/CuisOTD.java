package xyz.cesarnvf.aigfec.modelo.otd;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuisOTD {

    private Long idcui;
    private Long idempresa;
    private Long nit;
    private String razonsocial;
    private Long idsucursal;
    private Integer sucursal;
    private String nombresucursal;
    private Long idpuntoventa;
    private Integer puntoventa;
    private String nombrepuntoventa;
    private String cui;
    private LocalDateTime fechavigencia;
    private Boolean estado;
}
