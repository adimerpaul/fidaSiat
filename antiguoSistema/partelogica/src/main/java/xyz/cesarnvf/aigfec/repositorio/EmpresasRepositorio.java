package xyz.cesarnvf.aigfec.repositorio;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Empresas;

@Mapper
public interface EmpresasRepositorio extends GenericosRepositorio<Empresas>{

    Empresas obtenerEmpresaActiva();

}
