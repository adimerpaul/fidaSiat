package xyz.cesarnvf.aigfec.modelo;

import lombok.Data;

@Data
public class Enlaces {

    private Long idenlace;
    private Long idenlacepadre;
    private String enlace;
    private String ruta;
    private String iconoenlace;
    private Integer orden;

}
