package xyz.cesarnvf.aigfec.modelo.otd;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnlacesOTD {

    private Long idenlace;
    private Long idenlacepadre;
    private String enlacepadre;
    private String enlace;
    private String ruta;
    private String iconoenlace;
    private Integer orden;
    private List<Long> idroles;
    private List<String> roles;

}
