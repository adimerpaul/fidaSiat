package xyz.cesarnvf.aigfec.modelo.auxi;

import java.util.List;

import bo.gob.impuestos.siat.ParametricaLeyendasDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Respuestasleyendas {

    private ParametricaLeyendasDto[] leyendas;
    private List<Respuestas> mensajes;
}
