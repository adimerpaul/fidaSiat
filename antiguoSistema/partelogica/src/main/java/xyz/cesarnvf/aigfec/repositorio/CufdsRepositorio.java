package xyz.cesarnvf.aigfec.repositorio;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Cufds;

@Mapper
public interface CufdsRepositorio extends GenericosRepositorio<Cufds> {

    List<Cufds> obtenerUltimos(Long idpuntoventa);

    Cufds obtenerPorIdpuntoventa(Long id);

    Cufds obtenerPorPuntoventa(Integer puntoventa);

    Cufds obtenerVigente(Long idpuntoventa);

}
