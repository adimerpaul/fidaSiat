package xyz.cesarnvf.aigfec.servicio;

import java.util.List;

import xyz.cesarnvf.aigfec.modelo.otd.EnlacesOTD;
import xyz.cesarnvf.aigfec.modelo.otd.MenusOTD;

public interface EnlacesServicio {

    List<MenusOTD> obtenerEnlacePorIdusuario();

    List<EnlacesOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad);

    Integer obtenerCantidad(String termino);

    List<EnlacesOTD> obtenerTodosPrincipales();

    EnlacesOTD obtenerPorId(Long id);

    void adicionar(EnlacesOTD dato);

    void modificar(EnlacesOTD dato);

}
