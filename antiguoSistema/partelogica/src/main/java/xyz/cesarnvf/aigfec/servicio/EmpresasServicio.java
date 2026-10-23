package xyz.cesarnvf.aigfec.servicio;

import java.util.List;

import xyz.cesarnvf.aigfec.modelo.Empresas;

public interface EmpresasServicio {

    List<Empresas> obtenerTodos();

    List<Empresas> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad);

    Integer obtenerCantidad(String termino);

    Empresas obtenerPorId(Long id);

    void adicionar(Empresas dato);

    void modificar(Empresas dato);
}
