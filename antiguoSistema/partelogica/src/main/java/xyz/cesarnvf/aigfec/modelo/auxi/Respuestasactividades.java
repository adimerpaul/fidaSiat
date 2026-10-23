package xyz.cesarnvf.aigfec.modelo.auxi;

import java.util.List;

import bo.gob.impuestos.siat.ActividadesDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Respuestasactividades {

    private ActividadesDto[] actividades;
    private List<Respuestas> mensajes;

}
