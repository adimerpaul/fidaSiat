package xyz.cesarnvf.aigfec.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class Bitacoras {

    private Long idbitacora;
    private Long idusuario;
    private String bitacora;
    private LocalDate fecha;
    private LocalTime hora;

}
