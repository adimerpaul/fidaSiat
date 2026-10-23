package xyz.cesarnvf.aigfec.modelo.auxi;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosCUF {

    private long nitEmisor;
    private LocalDateTime fechaHora;
    private int sucursal;
    private int modalidad;
    private int tipoEmision;
    private int tipoFacturaDocumentoAjuste;
    private int tipoDocumentoSector;
    private long numeroFactura;
    private int puntoVenta;

}
