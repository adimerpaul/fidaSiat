package xyz.cesarnvf.aigfec.repositorio;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Productos;

@Mapper
public interface ProductosRepositorio extends GenericosRepositorio<Productos> {

    List<Productos> obtenerPorIdgrupoConPaginacion(Long idgrupo, Integer pagina, Integer cantidad);

    Integer obtenerCantidadPorIdgrupo(Long idgrupo);

    Productos obtenerPorCodigocorto(Integer codigocroto);

}
