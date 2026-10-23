package xyz.cesarnvf.aigfec.modelo;

import lombok.Data;

@Data
public class Correos {

    private String correo;
    private String tema;
    private String contenido;
    private String rutaDocAdjuntoXML;
    private String nombreArchivoXML;
    private String rutaDocAdjuntoPDF;
    private String nombreArchivoPDF;

}
