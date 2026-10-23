package xyz.cesarnvf.aigfec.repositorio;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Grupos;

@Mapper
public interface GruposRepositorio extends GenericosRepositorio<Grupos>{

    List<Grupos> obtenerTodosPorTipo(Integer tipo);

}
