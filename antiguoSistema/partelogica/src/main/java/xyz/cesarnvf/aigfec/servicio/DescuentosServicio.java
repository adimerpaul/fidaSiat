package xyz.cesarnvf.aigfec.servicio;

import java.util.List;

import xyz.cesarnvf.aigfec.modelo.otd.DescuentosOTD;

public interface DescuentosServicio {

    List<DescuentosOTD> obtenerTodos();

    List<DescuentosOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad);

    Integer obtenerCantidad(String termino);

    DescuentosOTD obtenerPorId(Long id);

    void adicionar(DescuentosOTD dato);

    void modificar(DescuentosOTD dato);

    void borrar(Long id);
}
