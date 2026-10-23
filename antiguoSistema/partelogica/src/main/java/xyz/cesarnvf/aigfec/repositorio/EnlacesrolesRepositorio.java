package xyz.cesarnvf.aigfec.repositorio;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Enlacesroles;

@Mapper
public interface EnlacesrolesRepositorio extends GenericosRepositorio<Enlacesroles>{

    List<Enlacesroles> obtenerPorIdenlace(Long id);

    void borrar(Long idenlace);

}
