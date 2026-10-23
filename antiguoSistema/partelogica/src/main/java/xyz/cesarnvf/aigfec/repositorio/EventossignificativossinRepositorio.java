package xyz.cesarnvf.aigfec.repositorio;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Eventossignificativossin;

@Mapper
public interface EventossignificativossinRepositorio extends GenericosRepositorio<Eventossignificativossin>{

     Eventossignificativossin verificarCodigosin(Integer codigosin);
}
