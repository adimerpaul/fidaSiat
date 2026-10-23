package xyz.cesarnvf.aigfec.servicio;

import java.util.List;

import xyz.cesarnvf.aigfec.modelo.Usuarios;
import xyz.cesarnvf.aigfec.modelo.otd.UsuariosOTD;

public interface UsuariosServicio {

    List<UsuariosOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad);

    Integer obtenerCantidad(String termino);

    UsuariosOTD obtenerPorId(Long id);

    void adicionar(UsuariosOTD dato);

    void modificar(UsuariosOTD dato);

    void inicializarclave(Long id);

    void cambiarclave(UsuariosOTD usuario);

    List<Usuarios> obtenerTodosRolVendedor();

    UsuariosOTD buscarPorIdT(Long id);
}
