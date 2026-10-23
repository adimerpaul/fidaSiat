package xyz.cesarnvf.aigfec.servicio;

import java.rmi.RemoteException;
import java.util.List;

import xyz.cesarnvf.aigfec.modelo.auxi.Respuestas;
import xyz.cesarnvf.aigfec.modelo.otd.CuisOTD;

public interface CuisServicio {

    List<CuisOTD> obtenerTodos();

    List<CuisOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad);

    Integer obtenerCantidad(String termino);

    CuisOTD obtenerPorId(Long id);

    void adicionarPuntosventas();

    List<Respuestas> obtenerCui(Long id) throws RemoteException;

    List<Respuestas> adicionarCui(Long id) throws RemoteException;

    List<Respuestas> registrarPuntoventa(Long id) throws RemoteException;

}
