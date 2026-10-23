package xyz.cesarnvf.aigfec.modelo.auxi;

import java.util.List;

import bo.gob.impuestos.siat.ParametricasDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Respuestastiposhabitacion {

    private ParametricasDto[] tiposhabitacion;
    private List<Respuestas> mensajes;
}
