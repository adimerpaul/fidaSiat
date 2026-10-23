package xyz.cesarnvf.aigfec.modelo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Personas {

    private Long idpersona;
    private Long idtipogenero;
    private Long idtipodocumento;
    private Long idtipoextension;
    private Long idtipoestadocivil;
    private Long idpais;
    private String primerapellido;
    private String segundoapellido;
    private String primernombre;
    private String segundonombre;
    private String dip;
    private LocalDate fechaemitida;
    private LocalDate fechaexpiracion;
    private String numerocomplementario;
    private LocalDate fechanacimiento;
    private String direccion;
    private String telefono;
    private String celular;
    private String correo;
    private Integer idestado;
    private String razonsocial;

}

