package xyz.cesarnvf.aigfec.servicio;

import java.util.List;

import xyz.cesarnvf.aigfec.modelo.Cufds;

public interface CufdsServicio {

    List<Cufds> obtenerUltimos(Long idpuntoventa);

}
