package xyz.cesarnvf.aigfec.repositorio;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Usuariosroles;

@Mapper
public interface UsuariosrolesRepositorio{

    List<Usuariosroles> obtenerPorIdusuario(Long id);

    void adicionar(Usuariosroles dato);

    void borrar(Long idusuario);


}
