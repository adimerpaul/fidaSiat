package xyz.cesarnvf.aigfec.modelo;

import lombok.Data;

@Data
public class Sucursales {

    private Long idsucursal;
    private Long idempresa;
    private Integer sucursal;
    private String nombresucursal;
    private String municipio;
    private String telefono;
    private String direccion;
}
