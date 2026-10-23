package xyz.cesarnvf.aigfec.repositorio;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Unidadesmedidas;

@Mapper
public interface UnidadesmedidasRepositorio extends GenericosRepositorio<Unidadesmedidas>{

    Unidadesmedidas verificarCodigosin(Integer codigosin);

}
