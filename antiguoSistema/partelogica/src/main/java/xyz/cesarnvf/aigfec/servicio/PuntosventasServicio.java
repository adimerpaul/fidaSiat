package xyz.cesarnvf.aigfec.servicio;

import java.util.List;

import xyz.cesarnvf.aigfec.modelo.otd.PuntosventasOTD;

public interface PuntosventasServicio {

    List<PuntosventasOTD> obtenerTodos(Long idsucursal);

    List<PuntosventasOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad, Long idsucursal);

    Integer obtenerCantidad(String termino, Long idsucursal);

    PuntosventasOTD obtenerPorId(Long id);

    void adicionar(PuntosventasOTD dato);

    void modificar(PuntosventasOTD dato);

    void borrar(Long id);

}
