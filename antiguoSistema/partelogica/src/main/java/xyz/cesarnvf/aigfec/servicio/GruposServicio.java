package xyz.cesarnvf.aigfec.servicio;

import java.util.List;

import xyz.cesarnvf.aigfec.modelo.Grupos;

public interface GruposServicio {

    List<Grupos> obtenerTodos();

    List<Grupos> obtenerTodosPorTipo(Integer tipo);

    List<Grupos> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad);

    Integer obtenerCantidad(String termino);

    Grupos obtenerPorId(Long id);

    void adicionar(Grupos dato);

    void modificar(Grupos dato);
}
