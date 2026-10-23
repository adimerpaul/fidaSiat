package xyz.cesarnvf.aigfec.modelo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Cuis {

    private Long idcui;
    private Long idpuntoventa;
    private String cui;
    private LocalDateTime fechavigencia;
    private Boolean estado;

}
