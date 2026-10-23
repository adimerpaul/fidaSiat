package xyz.cesarnvf.aigfec.modelo.otd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientesOTD {

    private Long idcliente;
    private Long iddocumentoidentidadsin;
    private Integer codigosin;
    private String documentoidentidadsin;
    private String cliente;
    private String numerodocumento;
    private String complemento;
    private String correoelectronico;
    private String celular;
    private Integer codigoexcepcion;

}
