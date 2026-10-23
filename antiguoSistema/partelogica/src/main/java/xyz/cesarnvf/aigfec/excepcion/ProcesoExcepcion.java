package xyz.cesarnvf.aigfec.excepcion;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcesoExcepcion extends RuntimeException{

    private List<String> errores;

    public ProcesoExcepcion(String mensajes) {
        super(mensajes);
    }

    public ProcesoExcepcion(String mensajes, List<String> errores) {
        super(mensajes);
        this.errores = errores;
    }
}
