package xyz.cesarnvf.aigfec.modelo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Cufds {

    private Long idcufd;
    private Long idpuntoventa;
    private String cufd;
    private String cufdcontrol;
    private LocalDateTime fechainicio;
    private LocalDateTime fechavigencia;
    private Boolean estado;
}
