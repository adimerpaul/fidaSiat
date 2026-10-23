package xyz.cesarnvf.aigfec.modelo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Empresas {

    private Long idempresa;
    private Long nit;
    private String razonsocial;
    private String codigosistema;
    private String token;
    private LocalDateTime fechahora;
    private Integer ambiente;
    private Integer modalidad;
    private String ruta;
    private String cafc;
    private Boolean estado;

}
