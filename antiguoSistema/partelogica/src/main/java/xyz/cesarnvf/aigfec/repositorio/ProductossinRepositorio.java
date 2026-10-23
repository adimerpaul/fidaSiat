package xyz.cesarnvf.aigfec.repositorio;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Productossin;

@Mapper
public interface ProductossinRepositorio extends GenericosRepositorio<Productossin>{

    Productossin verificarCodigosin(Integer codigosin, Integer codigoactividadsin);

}
