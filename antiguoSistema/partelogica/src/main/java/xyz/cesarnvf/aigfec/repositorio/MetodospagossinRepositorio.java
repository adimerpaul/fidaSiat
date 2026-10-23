package xyz.cesarnvf.aigfec.repositorio;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Metodospagossin;

@Mapper
public interface MetodospagossinRepositorio extends GenericosRepositorio<Metodospagossin>{

    Metodospagossin verificarCodigosin(Integer codigosin);
}
