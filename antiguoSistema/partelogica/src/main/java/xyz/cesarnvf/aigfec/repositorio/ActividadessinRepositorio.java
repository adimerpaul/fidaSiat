package xyz.cesarnvf.aigfec.repositorio;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Actividadessin;

@Mapper
public interface ActividadessinRepositorio extends GenericosRepositorio<Actividadessin> {

    Actividadessin verificarCodigosin(Integer codigosin);

}
