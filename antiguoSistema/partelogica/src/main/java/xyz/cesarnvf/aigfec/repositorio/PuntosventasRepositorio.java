package xyz.cesarnvf.aigfec.repositorio;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Puntosventas;

@Mapper
public interface PuntosventasRepositorio extends GenericosRepositorio<Puntosventas>{

    List<Puntosventas> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad, Long idsucursal);

    Integer obtenerCantidad(String termino, Long idsucursal);

    List<Puntosventas> obtenerTodos(Long idsucursal);

    Puntosventas obtenerPorPuntoventa(Integer puntoventa);

    Long obtenerPuntoventaAsignado(Long idusuario);

    Long obtenerNumerofactura(Long idpuntoventa);

    Long obtenerPorIdpuntoventa0(Long idsucursal);

}
