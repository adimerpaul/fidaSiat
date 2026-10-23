package xyz.cesarnvf.aigfec.servicio;

import java.util.List;

import xyz.cesarnvf.aigfec.modelo.Sucursales;

public interface SucursalesServicio {

    List<Sucursales> obtenerTodos(Long idempresa);

    List<Sucursales> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad, Long idempresa);

    Integer obtenerCantidad(String termino, Long idempresa);

    Sucursales obtenerPorId(Long id);

    void adicionar(Sucursales dato);

    void modificar(Sucursales dato);

    void borrar(Long id);

}
