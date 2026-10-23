package xyz.cesarnvf.aigfec.repositorio;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Personas;

@Mapper
public interface PersonasRepositorio extends GenericosRepositorio<Personas>{

    Personas obtenerPorNombreCompletoODip(String buscar);

    Personas buscarPersona(String termino);

}
