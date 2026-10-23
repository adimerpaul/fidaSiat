package xyz.cesarnvf.aigfec.repositorio;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Cuis;

@Mapper
public interface CuisRepositorio extends GenericosRepositorio<Cuis>{

    Cuis obtenerPorIdpuntoventa(Long id);

    Cuis obtenerPorPuntoventa(Long idpuntoventa);

    Cuis obtenerVigente(Long idpuntoventa);

}
