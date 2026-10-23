package xyz.cesarnvf.aigfec.modelo;

import lombok.Data;

@Data
public class Clientes {

    private Long idcliente;
    private Long iddocumentoidentidadsin;
    private String cliente;
    private String numerodocumento;
    private String complemento;
    private String correoelectronico;
    private String celular;
    private Integer codigoexcepcion;

}