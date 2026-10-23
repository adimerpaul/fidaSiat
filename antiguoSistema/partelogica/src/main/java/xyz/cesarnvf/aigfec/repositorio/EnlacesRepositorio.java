package xyz.cesarnvf.aigfec.repositorio;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Enlaces;

@Mapper
public interface EnlacesRepositorio extends GenericosRepositorio<Enlaces>{

    List<Long> obtenerPorIdusuarioPadres(Long idusuario);

    List<Long> obtenerPorIdusuarioHijos(Long idusuario, Long idenlace);

    List<Enlaces> obtenerTodosPrincipales();

}
