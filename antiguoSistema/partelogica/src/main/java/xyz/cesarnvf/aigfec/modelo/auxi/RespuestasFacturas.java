package xyz.cesarnvf.aigfec.modelo.auxi;

import java.util.List;

import lombok.Data;

@Data
public class RespuestasFacturas {

    private Integer codigoEstado;
    private String cuf;
    private Long numeroFactura;
    private List<Respuestas> mensajes;
}
