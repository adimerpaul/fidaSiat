package xyz.cesarnvf.aigfec.modelo.otd;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenusOTD {

    private String enlace;
    private String imagen;
    private String ruta;
    private List<SubmenusOTD> enlaces;

}
