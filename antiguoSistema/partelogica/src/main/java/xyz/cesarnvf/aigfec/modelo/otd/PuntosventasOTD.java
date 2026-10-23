package xyz.cesarnvf.aigfec.modelo.otd;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PuntosventasOTD {

    private Long idpuntoventa;
    private Long idempresa;
    private Long nit;
    private String razonsocial;
    private Integer sucursal;
    private String municipio;
    private String direccion;
    private String telefono;
    private Long idsucursal;
    private Integer puntoventa;
    private String nombrepuntoventa;
    private Integer numerofactura;
    private Long idusuario;
    private String usuario;
    private String cui;
    private LocalDateTime cuifechavigencia;
    private String cufd;
    private String cufdcodigo;
    private LocalDateTime cufdfechainicio;
    private LocalDateTime cufdfechavigencia;
    private List<Long> iddescuentos;
    private List<String> descuentos;
    private Integer estadosistema;
    private String cafc;
    private Long idcufd;

}
