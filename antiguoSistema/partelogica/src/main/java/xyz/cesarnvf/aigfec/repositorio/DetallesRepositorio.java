package xyz.cesarnvf.aigfec.repositorio;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Detalles;

@Mapper
public interface DetallesRepositorio extends GenericosRepositorio<Detalles>{

    List<Detalles> obtenerTodos(long idfactura);
}
