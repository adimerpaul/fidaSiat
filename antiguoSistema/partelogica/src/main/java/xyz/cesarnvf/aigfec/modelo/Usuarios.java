package xyz.cesarnvf.aigfec.modelo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Usuarios {

    private Long idusuario;
    private Long idpersona;
    private String usuario;
    private String clave;
    private Boolean habilitado;
    private LocalDate fecha;

}
