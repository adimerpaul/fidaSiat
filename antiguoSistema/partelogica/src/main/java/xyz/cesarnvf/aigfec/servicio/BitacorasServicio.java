package xyz.cesarnvf.aigfec.servicio;

import java.util.List;

import xyz.cesarnvf.aigfec.modelo.otd.BitacorasOTD;

public interface BitacorasServicio {

    List <BitacorasOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad);

    Integer obtenerCantidad(String termino);

    BitacorasOTD obtenerPorId(Long id);

    void registrar(String bitacora);
}
