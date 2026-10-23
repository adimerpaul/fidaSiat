package xyz.cesarnvf.aigfec.servicio;

import java.rmi.RemoteException;
import java.util.List;

import xyz.cesarnvf.aigfec.modelo.otd.ClientesOTD;

public interface ClientesServicio {

    List<ClientesOTD> obtenerTodos();

    List<ClientesOTD> obtenerPorTerminoConPaginacion(String termino, Integer pagina, Integer cantidad);

    Integer obtenerCantidad(String termino);

    ClientesOTD obtenerPorId(Long id);

    Long adicionar(ClientesOTD dato) throws NumberFormatException, RemoteException;

    void modificar(ClientesOTD dato) throws NumberFormatException, RemoteException;

    void borrar(Long id);

    ClientesOTD buscarPorDocumentoResgistradoImpuestos(String cadena);
}
