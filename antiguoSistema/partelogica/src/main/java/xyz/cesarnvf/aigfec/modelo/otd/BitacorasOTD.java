package xyz.cesarnvf.aigfec.modelo.otd;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BitacorasOTD {

    private Long idbitacora;
    private Long idusuario;
    private String usuario;
    private String bitacora;
    private LocalDate fecha;
    private LocalTime hora;

}
