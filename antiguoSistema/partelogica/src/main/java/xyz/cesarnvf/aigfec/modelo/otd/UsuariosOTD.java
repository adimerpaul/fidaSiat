package xyz.cesarnvf.aigfec.modelo.otd;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosOTD {

    private Long idusuario;
    private Long idpersona;
    private String primerapellido;
    private String segundoapellido;
    private String primernombre;
    private String segundonombre;
    private String dip;
    private List<Long> idroles;
    private List<String> roles;
    private String usuario;
    private String clave;
    private Boolean habilitado;

}
