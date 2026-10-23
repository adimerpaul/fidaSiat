package xyz.cesarnvf.aigfec.repositorio;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Sucursales;

@Mapper
public interface SucursalesRepositorio extends GenericosRepositorio<Sucursales>{

    List<Sucursales> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad, Long idempresa);

    Integer obtenerCantidad(String termino, Long idempresa);

    List<Sucursales> obtenerTodos(Long idempresa);

}
