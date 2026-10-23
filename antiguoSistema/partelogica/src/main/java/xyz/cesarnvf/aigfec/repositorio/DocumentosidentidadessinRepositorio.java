package xyz.cesarnvf.aigfec.repositorio;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Documentosidentidadessin;

@Mapper
public interface DocumentosidentidadessinRepositorio extends GenericosRepositorio<Documentosidentidadessin>{

     Documentosidentidadessin verificarCodigosin(Integer codigosin);

}
