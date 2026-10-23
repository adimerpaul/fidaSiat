package xyz.cesarnvf.aigfec.repositorio;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Leyendassin;

@Mapper
public interface LeyendassinRepositorio extends GenericosRepositorio<Leyendassin> {

    Leyendassin verificarCodigosin(Integer codigosin, String leyendasin);

    Leyendassin obtenerLeyendaAleatoriamente(Integer codigoactividad);

}
