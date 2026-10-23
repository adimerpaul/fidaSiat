package xyz.cesarnvf.aigfec.modelo;

import lombok.Data;

@Data
public class Puntosventas {

    private Long idpuntoventa;
    private Long idsucursal;
    private Integer puntoventa;
    private String nombrepuntoventa;
    private Integer numerofactura;
    private Long idusuario;
    private Integer estadosistema;
    private String cafc;
    private Long idcufd;
}
