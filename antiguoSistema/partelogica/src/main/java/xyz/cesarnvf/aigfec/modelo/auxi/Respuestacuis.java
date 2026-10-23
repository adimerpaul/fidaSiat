package xyz.cesarnvf.aigfec.modelo.auxi;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Respuestacuis {

    private String codigo;
    private LocalDateTime fechavigencia;
    private Boolean vigencia;
    private List<Respuestas> mensajes;

}

