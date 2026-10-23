package xyz.cesarnvf.aigfec.modelo.auxi;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Respuestasfechas {

    private String fechahora;
    private List<Respuestas> mensajes;

}
