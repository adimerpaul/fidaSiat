package xyz.cesarnvf.aigfec.repositorio;

import java.util.List;

public interface GenericosRepositorio<T> {

    List<T> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad);

    Integer obtenerCantidad(String termino);

    List<T> obtenerPorTerminoSinPaginacion(String termino);

    List<T> obtenerTodos();

    T obtenerPorId(Long id);

    void adicionar(T dato);

    void modificar(T dato);

    void borrar(Long id);

}
