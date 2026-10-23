package xyz.cesarnvf.aigfec.repositorio;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import xyz.cesarnvf.aigfec.modelo.Usuarios;
import xyz.cesarnvf.aigfec.modelo.otd.UsuariosOTD;

@Mapper
public interface UsuariosRepositorio extends GenericosRepositorio<Usuarios>{

    Usuarios obtenerPorUsuario(String usuario);

    void inicializarclave(Long id);

    void cambiarclave(Usuarios usuario);

    List<Usuarios> obtenerTodosRolVendedor();

    UsuariosOTD buscarPorIdT(Long id);

}
